import cProfile
import pyro
from serviceavailability_16_simulator import *
import traceback

#inference=pyro.infer.Importance(model=simulate, num_samples=50)

#cProfile.run('inference.run()')

cProfile.run('data=[simulate() for i in range(50)]')