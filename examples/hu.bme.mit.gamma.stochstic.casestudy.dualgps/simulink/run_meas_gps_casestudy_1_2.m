clear
K=13;
runtimes=zeros(1,K);
data=ones(1,K);
TK=60*60*1e9;
FR_sensor=10.0;
FR_voter=20.0;
failtime_1=[0.0,0.0];
failtime_2=[0.0,0.0];
acc_mode="off" % "normal", "classic", "rapid", "off"
model_name="gps_casestudy_1_2_v3"
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
    failtime_1=[0,-(1.0/FR_sensor)*log(1-rand());];
    failtime_2=[0,-(1.0/FR_sensor)*log(1-rand());];
    failtime_3=[0,-(1.0/FR_voter )*log(1-rand());];
    results=sim(model_name);
    t=(results.get("op_time").Data(end,end));
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
fprintf("Average failure time : ");
disp(mean(data));
fprintf("Median simulation time : ");
disp(median(runtimes));