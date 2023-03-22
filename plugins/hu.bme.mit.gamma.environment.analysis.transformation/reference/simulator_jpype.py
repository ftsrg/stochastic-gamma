import pyro
import torch
from pyro.infer import SVI, Trace_ELBO
from pyro.optim import Adam
import pyro.distributions as dist
import torch.distributions.constraints as constraints
from pyro.distributions.distribution import Distribution

import math
from math import exp
import numpy as np

from py4j.java_gateway import JavaGateway, CallbackServerParameters
import matplotlib.pyplot as plt
import matplotlib

from influxdb import InfluxDBClient

import pyro.contrib.gp as gp

import time
import os
import datetime
import traceback
from jpype import JImplements, JOverride
from jpype import *
import copy

print('python script is called')

print('creating java Py4J gateway')

os.system("""javac $(find . -name "*.java") -cp /usr/share/java/py4j0.10.8.1.jar""")
# os.system("""cd ./bin && java -cp /usr/share/java/py4j0.10.8.1.jar:lib/*:. javaenv.AnalyzerGateway &""")

time.sleep(3)

print('creating python Py4J gateway')

startJVM(getDefaultJVMPath(), '-ea',
         '-Djava.class.path=/home/simon5521/Projects/runtime-EclipseXtext/hu.bme.mit.gamma.stochastic.casestudy.iotcamera_r16c2/bin'
         )

DetModelEntryPoint = JClass('javaenv.DetModelEntryPoint')

entry_point = DetModelEntryPoint()
sctmodel = entry_point

simTime = 5.0
simNumber = 50

print('connected to gateway')

DEBUG = False

events = list()


class Dataset:

    def __init__(
            self,
            dbname,
            ip,
            port,
            query=None,
            script=None,
    ):
        if query is not None:
            client = InfluxDBClient(ip, int(port), database=dbname)
            result = client.query(query)
            points = result.get_points()
            self.points = points
        elif script is not None:
            exec(script)


# stochastic model classes

vars=[]
def resetVars():
	global vars
	for var in vars:
		var.event_cntr = 10000 - 1
		var.meta_cntr = - 1

class ContinuousRandomVariable:

    def __init__(
            self,
            dist,
            name,
            N=10000,
    ):
        global vars
        vars.append(self)
        self.dist = dist
        self.name = name
        self.event_cntr = N - 1
        self.meta_cntr = -1
        self.N = N

    def calc(self, event=0, time=0):
        self.event_cntr = self.event_cntr + 1
        if self.N > 0:
            if self.event_cntr == self.N:
                self.event_cntr = 0
                self.meta_cntr = self.meta_cntr + 1
                self.samples = pyro.sample(self.name + '_sample_'
                                           + str(self.meta_cntr),
                                           self.dist.expand([self.N]))
            return self.samples[self.event_cntr].item()
        else:
            return pyro.sample(self.name + '_sample_'
                               + str(self.event_cntr), self.dist).item()


class DiscreteRandomVariable:

    def __init__(self, dist, name):
        self.dist = dist
        self.name = name
        self.event_cntr = 0

    def calc(self, event=0, time=0):
        self.event_cntr = self.event_cntr + 1
        return pyro.sample(self.name + '_sample_'
                           + str(self.event_cntr), self.dist).item() \
               - 1.


class RandomVariable:

    def __init__(
            self,
            dist,
            name,
            N=10000,
    ):
        self.dist = dist
        self.name = name
        self.event_cntr = N - 1
        self.meta_cntr = -1
        self.N = N

    def calc(self, event=0, time=0):
        self.event_cntr = self.event_cntr + 1
        if self.N > 0:
            if self.event_cntr == self.N:
                self.event_cntr = 0
                self.meta_cntr = self.meta_cntr + 1

                self.samples = pyro.sample(self.name + '_sample_'
                                           + str(self.meta_cntr),
                                           self.dist.expand([self.N]))
            return self.samples[self.event_cntr].item()
        else:
            return pyro.sample(self.name + '_sample_'
                               + str(self.event_cntr), self.dist).item()


class GaussProcess:

    def __init__(
            self,
            dataset,
            kernel,
            lr,
            name,
    ):
        self.name = name
        self.event_cntr = 0
        points = dataset.points
        x = []
        t = []
        y = []
        i = 0
        t0 = 0
        for p in points:
            if i == 0:
                t0 = datetime.datetime.strptime(p.pop('time'),
                                                '%Y-%m-%dT%H:%M:%S.%fZ')
                t.append(t0)
            else:
                t.append(datetime.datetime.strptime(p.pop('time'),
                                                    '%Y-%m-%dT%H:%M:%S.%fZ'))
            t[i] = t[i] - t0
            x.append(t[i].total_seconds())
            yi = list(p.values())
            if len(yi) == 1:
                y.append(yi[0])
            else:
                y.append(yi)
            i = i + 1

        x = torch.tensor(x)
        y = torch.tensor(y)
        X = x

        # initialize the inducing inputs

        Xu = torch.arange(1.) / 6.0

        # kernel = gp.kernels.Sum(gp.kernels.Periodic(input_dim=1), gp.kernels.Brownian(input_dim=1))
        # we increase the jitter for better numerical stability

        sgpr = gp.models.SparseGPRegression(X=X, y=y, kernel=kernel,
                                            Xu=Xu, jitter=1.0e-5)

        # the way we setup inference is similar to above

        optimizer = torch.optim.Adam(sgpr.parameters(), lr=lr)
        loss_fn = pyro.infer.Trace_ELBO().differentiable_loss
        losses = []
        num_steps = 2000
        for i in range(num_steps):
            optimizer.zero_grad()
            loss = loss_fn(sgpr.model, sgpr.guide)
            if i % 20 == 0:
                print('Step: ', i, ' Loss: ', loss)
            loss.backward()
            optimizer.step()
            losses.append(loss.item())
        self.gp = sgpr

    def calc(self, event, time):
        self.event_cntr = self.event_cntr + 1
        (mu, sig) = self.gp.forward(torch.tensor([time]),
                                    full_cov=False, noiseless=False)
        return pyro.sample(self.name + '_sample_GP_'
                           + str(self.event_cntr),
                           pyro.distributions.Normal(mu, sig)).item()


class Event:

    def __init__(self, eventSource, eventTime):
        self.eventSource = eventSource
        self.eventTime = eventTime

    def __init__(
            self,
            eventSource,
            eventTime,
            eventCall,
    ):
        self.eventSource = eventSource
        self.eventTime = eventTime
        self.eventCall = eventCall


class FittedExponentialRandomVariable:

    def model(self, data):
        rate = pyro.param('alpha_q', torch.tensor(1.),
                          constraint=constraints.positive)
        for i in range(len(data)):
            # observe datapoint i using the bernoulli likelihood

            pyro.sample('obs_{}'.format(i), dist.Exponential(rate),
                        obs=torch.tensor(data[i]))

    def guide(self, data):
        pass

    def __init__(
            self,
            name,
            source,
            lr,
    ):
        self.name = name
        self.event_cntr = 0

        # preprocess data

        data = [float(p['data']) for p in list(source.points)]
        md = min(data)
        data = [d - md for d in data]

        # setup the optimizer

        adam_params = {'lr': lr, 'betas': (0.99, 0.999)}
        optimizer = Adam(adam_params)
        n_steps = 2000

        # setup the inference algorithm

        svi = SVI(self.model, self.guide, optimizer, loss=Trace_ELBO())

        losses = list()
        ploss = 100000000.0

        # do gradient steps

        for step in range(n_steps):
            loss = svi.step(data)
            losses.append(loss)
            if step % 10 == 0:
                print('loss=', loss)
            if ploss - loss < 0.001:
                break
            ploss = loss

        # grab the learned variational parameters

        m = pyro.param('alpha_q').item()
        print(m)

        xx = list(np.arange(0, 4 / m, 0.1 / m))
        yy = []
        d = dist.Exponential(m)
        self.dist = d

        for x in xx:
            y = \
                float(exp(dist.Exponential(m).log_prob(torch.tensor(x))))
            yy.append(y)
        (fig1, a1) = plt.subplots()
        (fig2, a2) = plt.subplots()

        a1.plot(losses)
        a1.set_ylabel('loss')
        a1.set_xlabel('step')
        a1.set_title('model fitting process')
        a2.hist(data, bins=10)
        a2.plot(xx, yy)
        a2.set_ylabel('frequency')
        a2.set_xlabel('delay (s)')
        a2.set_title('model fitting results')

    def calc(self, event=0, time=0):
        self.event_cntr = self.event_cntr + 1
        return pyro.sample(self.name + '_sample_'
                           + str(self.event_cntr), self.dist).item()


class FittedNormalRandomVariable:

    def model(self, data):
        alpha_q = pyro.param('alpha_q', torch.tensor(1.),
                             constraint=constraints.positive)
        beta_q = pyro.param('beta_q', torch.tensor(1.),
                            constraint=constraints.positive)
        for i in range(len(data)):
            # observe datapoint i using the bernoulli likelihood

            pyro.sample('obs_{}'.format(i), dist.Normal(alpha_q,
                                                        beta_q), obs=torch.tensor(data[i]))

    def guide(self, data):
        pass

    def __init__(
            self,
            name,
            source,
            lr,
    ):
        self.name = name
        self.event_cntr = 0
        data = [float(p['data']) for p in list(source.points)]

        # setup the optimizer

        adam_params = {'lr': lr, 'betas': (0.99, 0.999)}
        optimizer = Adam(adam_params)
        n_steps = 2000

        # setup the inference algorithm

        svi = SVI(self.model, self.guide, optimizer, loss=Trace_ELBO())

        losses = list()
        ploss = 100000000.0

        # do gradient steps

        for step in range(n_steps):
            loss = svi.step(data)
            losses.append(loss)
            if step % 10 == 0:
                print('loss=', loss)
            if ploss - loss < 0.001:
                break
            ploss = loss

        # grab the learned variational parameters

        m = pyro.param('alpha_q').item()
        s = pyro.param('beta_q').item()
        print(m)
        print(s)

        xx = list(np.arange(m - 2 * s, m + 2 * s, s / 10))
        yy = []
        d = dist.Normal(loc=torch.tensor(m), scale=torch.tensor(s))
        self.dist = d

        for x in xx:
            y = float(exp(dist.Normal(loc=torch.tensor(m),
                                      scale=torch.tensor(s)).log_prob(torch.tensor(x))))
            yy.append(y)
        (fig1, a1) = plt.subplots()
        (fig2, a2) = plt.subplots()

        a1.plot(losses)
        a1.set_ylabel('loss')
        a1.set_xlabel('step')
        a1.set_title('model fitting process')
        a2.hist(data, bins=10)
        a2.plot(xx, yy)
        a2.set_ylabel('frequency')
        a2.set_xlabel('delay (s)')
        a2.set_title('model fitting results')

    def calc(self, event=0, time=0):
        self.event_cntr = self.event_cntr + 1
        return pyro.sample(self.name + '_sample_'
                           + str(self.event_cntr), self.dist).item()


# environment component classes

events=[]

class PeriodicEventSource:

    def __init__(
            self,
            name,
            calls,
            rules,
            portevents,
            detmodel,
    ):
        self.name = name
        self.calls = calls
        self.rules = rules
        self.portevents = portevents
        self.detmodel = detmodel

    def generateEvents(self):
        global events
        self.events = []
        ports = list(self.calls.keys())

        # iterating through self.ports

        for port in ports:
            pevents = self.portevents[port]

            # iterating through events

            for pevent in pevents:
                call = self.calls[port][pevent]
                rule = self.rules[port][pevent]
                simulationtime = 0.0
                while simulationtime < simTime:
                    simulationtime = simulationtime + rule.calc(port
                                                                + '.' + pevent, simulationtime)

                    # iterating through port connections

                    events.append(Event(self, simulationtime,
                                             call))

    def getEvents(self):
        eventcopy = self.events.copy()
        self.events.clear()
        return eventcopy


class EventSource:

    def __init__(
            self,
            name,
            calls,
            rules,
            portevents,
            detmodel,
    ):
        self.calls = calls
        self.rules = rules
        self.portevents = portevents
        self.detmodel = detmodel

    def generateEvents(self):
        self.events = []
        ports = list(self.calls.keys())

        # iterating through ports

        for port in ports:
            pevents = self.portevents[port]

            # iterating through events

            for pevent in pevents:
                rule = self.rules[port][pevent]
                call = self.calls[port][pevent]
                time = rule.calc(port + '.' + pevent, 0.0)
                if time >= 0:
                    # iterating through port connections

                    self.events.append(Event(self, time, call))

    def getEvents(self):
        eventcopy = self.events.copy()
        self.events.clear()
        return eventcopy


if3 = \
    JClass('hu.bme.mit.gamma.stochastic.casestudy.iotcamera_r16c2.interfaces.EventStreamInterface$Listener$Provided'
           )


@JImplements(if3)
class DelayEventStream:

    def __init__(
            self,
            name,
            inport,
            calls,
            rules,
            detmodel,
            actual_time,
    ):
        if len(calls)==0:
            return
        self.name =name
        callitem = calls.popitem()  # only one out port
        self.calls = callitem[1]
        self.port = callitem[0]
        self.rules = rules.popitem()[1]  # only one out port
        self.detmodel = detmodel
        self.event_cntr = 0
        self.events = []
        inport.registerListener(self)
        self.actual_time = actual_time

    def generateEvents(self):
        self.events = []

    def getEvents(self):
        eventcopy = self.events.copy()
        self.events.clear()
        return eventcopy

    # definition of the interface functions

    @JOverride
    def raiseNewEvent(self):
        global events
        #print("call-----------------------------")
        time = self.rules['NewEvent'].calc(self.port + '.' + 'NewEvent'
                                           , self.actual_time[0])
        self.event_cntr = self.event_cntr + 1
        failureTime = time + self.actual_time[0]
        for callitem in self.calls:
            callEvent = lambda: callitem.raiseNewEvent()
            events.append(Event(self, failureTime, callEvent))




if2 = \
    JClass('hu.bme.mit.gamma.stochastic.casestudy.iotcamera_r16c2.interfaces.ImageStreamInterface$Listener$Provided'
           )


@JImplements(if2)
class SwitchImageStream:

    def __init__(
            self,
            name,
            inport,
            calls,
            portarray,
            categorical,
            detmodel,
            actual_time,
    ):
        if len(calls)==0:
            return
        self.name = name
        self.calls = calls
        self.portarray = portarray
        self.categorical = categorical
        self.detmodel = detmodel
        self.event_cntr = 0
        self.events = []
        inport.registerListener(self)
        self.actual_time = actual_time

    def generateEvents(self):
        self.events = []

    def getEvents(self):
        eventcopy = self.events.copy()
        self.events.clear()
        return eventcopy

    # definition of the interface functions

    @JOverride
    def raiseNewData(self, blurred, car):
        global events
        #print("call-----------------------------")
        port = self.portarray[self.categorical.calc()]
        eventcalls = self.calls[port]  # ["NewData"]
        self.event_cntr = self.event_cntr + 1
        for call in eventcalls:
            callEvent = lambda: call.raiseNewData(blurred, car)
            events.append(Event(self, self.actual_time[0],
                                     callEvent))




if1 = \
    JClass('hu.bme.mit.gamma.stochastic.casestudy.iotcamera_r16c2.interfaces.ImageStreamInterface$Listener$Provided'
           )


@JImplements(if1)
class SampleImageStream:

    def __init__(
            self,
            name,
            inport,
            calls,
            rules,
            detmodel,
            actual_time,
    ):
        if len(calls)==0:
            return
        self.name = name
        callitem = calls.popitem()  # only one out port
        self.calls = callitem[1]
        self.port = callitem[0]
        self.rules = rules.popitem()[1]  # only one out port
        self.detmodel = detmodel
        self.event_cntr = 0
        self.events = []
        inport.registerListener(self)
        self.actual_time = actual_time

    def getEvents(self):
        eventcopy = self.events.copy()
        self.events.clear()
        return eventcopy

    def generateEvents(self):
        self.events = []

    # definition of the interface functions

    @JOverride
    def raiseNewData(self, blurred, car):
        global events
        #print("call-----------------------------")
        blurred = self.rules['NewData'].calc(self.port + '.' + 'NewData'
                                             , self.actual_time[0])

        # hu.bme.mit.gamma.expression.model.impl.DecimalTypeDefinitionImpl@3e90f445

        self.event_cntr = self.event_cntr + 1
        for call in self.calls:
            callEvent = lambda: call.raiseNewData(blurred, car)
            events.append(Event(self, self.actual_time[0],
                                     callEvent))



# environment instances

def generateComponents(actualTime):
    components = list()

    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource340'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad1CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource412')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource341'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad1CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource413')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic342'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad1RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic414')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource343'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad2CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource415')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource344'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad2CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource416')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic345'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad2RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic417')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource346'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad3CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource418')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource347'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad3CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource419')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic348'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad3RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic420')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource349'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad4CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource421')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource350'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad4CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource422')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic351'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad4RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic423')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource352'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad5CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource424')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource353'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad5CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource425')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic354'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad5RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic426')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource355'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad6CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource427')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource356'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad6CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource428')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic357'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad6RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic429')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource358'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad7CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource430')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource359'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad7CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource431')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic360'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad7RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic432')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource361'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad8CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource433')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource362'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad8CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource434')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic363'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad8RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic435')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource364'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad9CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource436')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource365'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad9CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource437')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic366'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad9RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic438')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource367'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad10CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource439')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource368'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad10CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource440')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic369'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad10RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic441')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource370'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad11CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource442')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource371'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad11CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource443')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic372'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad11RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic444')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource373'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad12CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource445')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource374'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad12CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource446')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic375'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad12RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic447')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource376'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad13CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource448')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource377'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad13CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource449')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic378'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad13RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic450')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource379'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad14CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource451')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource380'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad14CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource452')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic381'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad14RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic453')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource382'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad15CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource454')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource383'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad15CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource455')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic384'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad15RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic456')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource385'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad16CameraService1().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource457')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTimerSource386'
                                          , calls={'TimerOut': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad16CameraService2().getTimerStc().getTimerIn().raiseNewEvent()}},
                                          rules={'TimerOut': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Normal(torch.tensor(0.2),
                                                                     torch.tensor(0.05)),
                                           'ContRandomVarriableTimerSource458')}},
                                          portevents={'TimerOut': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@757b2524]
    #
    components.append(PeriodicEventSource(name='PeriodicEventSourceTraffic387'
                                          , calls={'Cars': {'NewEvent': lambda: \
            sctmodel.getSystem1().getRoad16RoadTraffic().getComp().getIn().raiseNewEvent()}},
                                          rules={'Cars': {'NewEvent': ContinuousRandomVariable
                                          (pyro.distributions.Exponential(torch.tensor(2.5)),
                                           'ContRandomVarriableTraffic459')}},
                                          portevents={'Cars': ['NewEvent']},
                                          detmodel=sctmodel))  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@7ab2f345]
    #

    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay388',
        inport=sctmodel.getSystem1().getRoad1RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad1RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad1RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay460')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay389',
        inport=sctmodel.getSystem1().getRoad2RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad2RoadTraffic().getCarLeaves(), sctmodel.getSystem1().getRoad2RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay461')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay390',
        inport=sctmodel.getSystem1().getRoad3RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad3RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad3RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay462')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay391',
        inport=sctmodel.getSystem1().getRoad4RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad4RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad4RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay463')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay392',
        inport=sctmodel.getSystem1().getRoad5RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad5RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad5RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay464')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay393',
        inport=sctmodel.getSystem1().getRoad6RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad6RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad6RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay465')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay394',
        inport=sctmodel.getSystem1().getRoad7RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad7RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad7RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay466')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay395',
        inport=sctmodel.getSystem1().getRoad8RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad8RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad8RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay467')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay396',
        inport=sctmodel.getSystem1().getRoad9RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad9RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad9RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay468')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay397',
        inport=sctmodel.getSystem1().getRoad10RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad10RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad10RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay469')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay398',
        inport=sctmodel.getSystem1().getRoad11RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad11RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad11RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay470')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay399',
        inport=sctmodel.getSystem1().getRoad12RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad12RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad12RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay471')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay400',
        inport=sctmodel.getSystem1().getRoad13RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad13RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad13RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay472')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay401',
        inport=sctmodel.getSystem1().getRoad14RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad14RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad14RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay473')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay402',
        inport=sctmodel.getSystem1().getRoad15RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad15RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad15RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay474')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(DelayEventStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@39b3908e]
        #
        name='DelayCarDelay403',
        inport=sctmodel.getSystem1().getRoad16RoadTraffic().getComp().getOut(),
        calls={'CarOut': [sctmodel.getSystem1().getRoad16RoadTraffic().getCarLeaves(),
                          sctmodel.getSystem1().getRoad16RoadTraffic().getComp().getAck()]},
        rules={'CarOut': {'NewEvent': ContinuousRandomVariable(pyro.distributions.Normal(torch.tensor(0.3),
                                                                                         torch.tensor(0.1)),
                                                               'ContRandomVarriableCarDelay475')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage404',
        inport=sctmodel.getSystem1().getRoad1CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad1CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad1CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage476'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage405',
        inport=sctmodel.getSystem1().getRoad1CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad1CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad1CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage477'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage406',
        inport=sctmodel.getSystem1().getRoad2CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad2CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad2CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage478'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage407',
        inport=sctmodel.getSystem1().getRoad2CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad2CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad2CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage479'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage408',
        inport=sctmodel.getSystem1().getRoad3CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad3CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad3CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage480'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage409',
        inport=sctmodel.getSystem1().getRoad3CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad3CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad3CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage481'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage410',
        inport=sctmodel.getSystem1().getRoad4CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad4CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad4CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage482'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage411',
        inport=sctmodel.getSystem1().getRoad4CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad4CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad4CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage483'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage412',
        inport=sctmodel.getSystem1().getRoad5CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad5CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad5CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage484'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage413',
        inport=sctmodel.getSystem1().getRoad5CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad5CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad5CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage485'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage414',
        inport=sctmodel.getSystem1().getRoad6CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad6CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad6CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage486'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage415',
        inport=sctmodel.getSystem1().getRoad6CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad6CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad6CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage487'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage416',
        inport=sctmodel.getSystem1().getRoad7CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad7CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad7CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage488'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage417',
        inport=sctmodel.getSystem1().getRoad7CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad7CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad7CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage489'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage418',
        inport=sctmodel.getSystem1().getRoad8CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad8CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad8CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage490'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage419',
        inport=sctmodel.getSystem1().getRoad8CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad8CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad8CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage491'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage420',
        inport=sctmodel.getSystem1().getRoad9CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad9CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad9CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage492'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage421',
        inport=sctmodel.getSystem1().getRoad9CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad9CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad9CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage493'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage422',
        inport=sctmodel.getSystem1().getRoad10CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad10CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad10CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage494'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage423',
        inport=sctmodel.getSystem1().getRoad10CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad10CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad10CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage495'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage424',
        inport=sctmodel.getSystem1().getRoad11CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad11CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad11CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage496'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage425',
        inport=sctmodel.getSystem1().getRoad11CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad11CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad11CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage497'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage426',
        inport=sctmodel.getSystem1().getRoad12CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad12CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad12CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage498'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage427',
        inport=sctmodel.getSystem1().getRoad12CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad12CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad12CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage499'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage428',
        inport=sctmodel.getSystem1().getRoad13CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad13CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad13CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage500'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage429',
        inport=sctmodel.getSystem1().getRoad13CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad13CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad13CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage501'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage430',
        inport=sctmodel.getSystem1().getRoad14CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad14CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad14CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage502'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage431',
        inport=sctmodel.getSystem1().getRoad14CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad14CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad14CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage503'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage432',
        inport=sctmodel.getSystem1().getRoad15CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad15CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad15CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage504'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage433',
        inport=sctmodel.getSystem1().getRoad15CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad15CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad15CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage505'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage434',
        inport=sctmodel.getSystem1().getRoad16CameraService1().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad16CameraService1().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad16CameraService1().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage506'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SwitchImageStream(
        name='SwitchLostPackage435',
        inport=sctmodel.getSystem1().getRoad16CameraService2().getComp().getOut(),
        calls={'ImageOut1': [sctmodel.getSystem1().getRoad16CameraService2().getPictures()],
               'ImageOut2': [sctmodel.getSystem1().getRoad16CameraService2().getPictures()]},
        portarray=['ImageOut1', 'ImageOut2'],
        categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([0.9,
                                                                                     0.1])), name='LostPackage507'),
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred436',
        inport=sctmodel.getSystem1().getRoad1CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad1CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred508')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred437',
        inport=sctmodel.getSystem1().getRoad1CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad1CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred509')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred438',
        inport=sctmodel.getSystem1().getRoad2CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad2CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred510')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred439',
        inport=sctmodel.getSystem1().getRoad2CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad2CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred511')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred440',
        inport=sctmodel.getSystem1().getRoad3CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad3CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred512')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred441',
        inport=sctmodel.getSystem1().getRoad3CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad3CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred513')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred442',
        inport=sctmodel.getSystem1().getRoad4CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad4CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred514')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred443',
        inport=sctmodel.getSystem1().getRoad4CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad4CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred515')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred444',
        inport=sctmodel.getSystem1().getRoad5CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad5CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred516')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred445',
        inport=sctmodel.getSystem1().getRoad5CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad5CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred517')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred446',
        inport=sctmodel.getSystem1().getRoad6CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad6CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred518')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred447',
        inport=sctmodel.getSystem1().getRoad6CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad6CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred519')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred448',
        inport=sctmodel.getSystem1().getRoad7CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad7CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred520')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred449',
        inport=sctmodel.getSystem1().getRoad7CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad7CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred521')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred450',
        inport=sctmodel.getSystem1().getRoad8CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad8CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred522')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred451',
        inport=sctmodel.getSystem1().getRoad8CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad8CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred523')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred452',
        inport=sctmodel.getSystem1().getRoad9CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad9CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred524')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred453',
        inport=sctmodel.getSystem1().getRoad9CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad9CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred525')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred454',
        inport=sctmodel.getSystem1().getRoad10CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad10CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred526')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred455',
        inport=sctmodel.getSystem1().getRoad10CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad10CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred527')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred456',
        inport=sctmodel.getSystem1().getRoad11CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad11CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred528')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred457',
        inport=sctmodel.getSystem1().getRoad11CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad11CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred529')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred458',
        inport=sctmodel.getSystem1().getRoad12CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad12CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred530')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred459',
        inport=sctmodel.getSystem1().getRoad12CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad12CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred531')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred460',
        inport=sctmodel.getSystem1().getRoad13CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad13CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred532')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred461',
        inport=sctmodel.getSystem1().getRoad13CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad13CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred533')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred462',
        inport=sctmodel.getSystem1().getRoad14CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad14CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred534')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred463',
        inport=sctmodel.getSystem1().getRoad14CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad14CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred535')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred464',
        inport=sctmodel.getSystem1().getRoad15CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad15CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred536')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred465',
        inport=sctmodel.getSystem1().getRoad15CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad15CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred537')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred466',
        inport=sctmodel.getSystem1().getRoad16CameraService1().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad16CameraService1().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred538')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))
    components.append(SampleImageStream(  # [hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl@5fe1a1e5]
        #
        name='SampleBlurred467',
        inport=sctmodel.getSystem1().getRoad16CameraService2().getCameraStc().getPicture(),
        calls={'ImageOut': [sctmodel.getSystem1().getRoad16CameraService2().getComp().getIn()]},
        rules={'ImageOut': {'NewData': ContinuousRandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.1)),
                                                                'DiscRandomVarriableBlurred539')}},
        detmodel=sctmodel,
        actual_time=actualTime,
    ))

    return components

actualTime=[0.0]
comp=generateComponents(actualTime)

def getComponents():
	resetVars()
	return copy.copy(comp)

def collectEvents(events, components):
    for component in components:
        events.extend(component.getEvents())
    #filter(lambda f: f.eventTime >= 0.0, events)
    #events.sort(key=lambda f: f.eventTime)



def generateEvents(events, components):
    for component in components:
        component.generateEvents()

def popEvent(events):
    mintime=10000000000.0
    min_i=0
    for i in range (len(events)):
        if events[i].eventTime<mintime:
        	min_i=i
        	mintime=events[min_i].eventTime
    event=events[min_i]
    events.remove(event)
    return event





def simulateUntilTime():
    global actualTime, events
    #t0=time.time()
    actualTime[0] = 0.0 
    events = list()
    entry_point.reset()
    components = getComponents()
    generateEvents(events, components)
    #collectEvents(events, components)
    #t1=time.time()
    #print("init time : ",t1-t0)
    while len(events) > 0 and actualTime[0] < simTime:
        #t0=time.time()
        #collectEvents(events, components)
        #t1=time.time()
        #filter(lambda f: f.eventTime >= 0.0, events)
        event = popEvent(events)
        actualTime[0] = event.eventTime
        #print("sort time : ",t1-t0)

        if DEBUG:
            print(event.eventSource.name + ' at time: ' \
                  + str(actualTime[0]))
        #t0=time.time()
        event.eventCall()
        #t1=time.time()
        #print("exec 1 time : ",t1-t0)
        
        t0=time.time()
        sctmodel.getSystem1().runCycle()
        sctmodel.getSystem1().runCycle()
        #t1=time.time()
        #print("exec 2 time : ",t1-t0)

    return entry_point.getFreq()


def simulate():
    return simulateUntilTime()


print('testing the simulator')

if DEBUG:
    try:
        for i in range(1):
            print(simulate())

        print('start simulator')

        prior_data = list()
        for i in range(simNumber):
            if i % 5 == 0:
                print('Simulation step: ', i)
            prior_data.append(simulate())

        print('visualize histogram')
        (fig, a) = plt.subplots()
        a.set_title('simulation results')
        a.hist(prior_data)
        plt.show()

        print('simulation has been finished')
    except(Exception, err):

        print('Exception occured during testing the simulation: ')
        print(err)
        traceback.print_exc()
    finally:
        print('shuting down the Py4J gateway')
        shutdownJVM()
        print('exit')
