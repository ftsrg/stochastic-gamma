clear
N=30;
K=13;
runtimes=zeros(1,K);
data=ones(1,K);
TK=60*60*1e9;
FR_sensor=10.0;
FR_voter=20.0;
failtime_1=[0.0,0.0];
failtime_2=[0.0,0.0];
acc_mode="off" % "normal", "classic", "rapid", "off"
model_name="iot_casestudy_1_16"
load_system(model_name)
set_param(model_name,'AccelVerboseBuild','on');
if acc_mode=="rapid"
    set_param(model_name,"FastRestart","off");
    set_param(model_name,'SimulationMode','rapid-accelerator');
end
if acc_mode=="classic"
    set_param(model_name,"FastRestart","off");
    set_param(0,'GlobalUseClassicAccelMode','on');
end
if acc_mode=="normal"
    set_param(model_name,"FastRestart","off");
    set_param(model_name,'SimulationMode','accelerator');
    set_param(model_name,"FastRestart","on");
end
if acc_mode=="off"
    set_param(model_name,"FastRestart","off");
    set_param(model_name,'SimulationMode','normal');
    set_param(model_name,"FastRestart","on");
end
for i=1:K 
    t1 = datetime('now');

    t_arrive=-(1.0/3.0)*log(1-rand())*1000
    t_visible=normrnd(500,100)

    arrive_time=[0,t_arrive];
    visibility_time=[0,t_visible];

    networkloss_array_1=[binornd(1,0.1*ones(1,N))];
    networkloss_array_2=[binornd(1,0.1*ones(1,N))];
    networkloss_array_3=[binornd(1,0.1*ones(1,N))];
    networkloss_array_4=[binornd(1,0.1*ones(1,N))];
    networkloss_array_5=[binornd(1,0.1*ones(1,N))];
    networkloss_array_6=[binornd(1,0.1*ones(1,N))];
    networkloss_array_7=[binornd(1,0.1*ones(1,N))];
    networkloss_array_8=[binornd(1,0.1*ones(1,N))];
    networkloss_array_9=[binornd(1,0.1*ones(1,N))];
    networkloss_array_10=[binornd(1,0.1*ones(1,N))];
    networkloss_array_11=[binornd(1,0.1*ones(1,N))];
    networkloss_array_12=[binornd(1,0.1*ones(1,N))];
    networkloss_array_13=[binornd(1,0.1*ones(1,N))];
    networkloss_array_14=[binornd(1,0.1*ones(1,N))];
    networkloss_array_15=[binornd(1,0.1*ones(1,N))];
    networkloss_array_16=[binornd(1,0.1*ones(1,N))];

    blurryness_array_1=[binornd(1,0.5*ones(1,N))];
    blurryness_array_2=[binornd(1,0.5*ones(1,N))];
    blurryness_array_3=[binornd(1,0.5*ones(1,N))];
    blurryness_array_4=[binornd(1,0.5*ones(1,N))];
    blurryness_array_5=[binornd(1,0.5*ones(1,N))];
    blurryness_array_6=[binornd(1,0.5*ones(1,N))];
    blurryness_array_7=[binornd(1,0.5*ones(1,N))];
    blurryness_array_8=[binornd(1,0.5*ones(1,N))];
    blurryness_array_9=[binornd(1,0.5*ones(1,N))];
    blurryness_array_10=[binornd(1,0.5*ones(1,N))];
    blurryness_array_11=[binornd(1,0.5*ones(1,N))];
    blurryness_array_12=[binornd(1,0.5*ones(1,N))];
    blurryness_array_13=[binornd(1,0.5*ones(1,N))];
    blurryness_array_14=[binornd(1,0.5*ones(1,N))];
    blurryness_array_15=[binornd(1,0.5*ones(1,N))];
    blurryness_array_16=[binornd(1,0.5*ones(1,N))];

    timer_array_1=[normrnd(200,20,1,N)];
    timer_array_2=[normrnd(200,20,1,N)];
    timer_array_3=[normrnd(200,20,1,N)];
    timer_array_4=[normrnd(200,20,1,N)];
    timer_array_5=[normrnd(200,20,1,N)];
    timer_array_6=[normrnd(200,20,1,N)];
    timer_array_7=[normrnd(200,20,1,N)];
    timer_array_8=[normrnd(200,20,1,N)];
    timer_array_9=[normrnd(200,20,1,N)];
    timer_array_10=[normrnd(200,20,1,N)];
    timer_array_11=[normrnd(200,20,1,N)];
    timer_array_12=[normrnd(200,20,1,N)];
    timer_array_13=[normrnd(200,20,1,N)];
    timer_array_14=[normrnd(200,20,1,N)];
    timer_array_15=[normrnd(200,20,1,N)];
    timer_array_16=[normrnd(200,20,1,N)];
    
    results=sim(model_name);
    t=(results.get("faults").Data(end,end));
    data(i)=t;
    t2 = datetime('now');
    dt=t2-t1;
    dt.Format = dt.Format + ".SSSSSS";
    fprintf("Results:");
    disp(t);
    fprintf("Elapsed time:");
    disp(dt);
    if i>2
        runtimes(i)=seconds(dt);
    end
end
fprintf("Estimated failure probability : ");
disp(mean(data));
fprintf("Median simulation time : ");
disp(median(runtimes));