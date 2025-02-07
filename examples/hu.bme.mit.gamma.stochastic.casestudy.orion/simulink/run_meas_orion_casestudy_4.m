clear
K=13;
N=30;
runtimes=zeros(1,K);
data=ones(1,K);



acc_mode="off" % "normal", "classic", "rapid", "off"
model_name="orion_casestudy_4"
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

    delay_array_1   = [0,zeros(1,N)];
    delay_array_2   = [0,zeros(1,N)];
    delay_array_3   = [0,zeros(1,N)];
    delay_array_4   = [0,zeros(1,N)];
    delay_array_5   = [0,zeros(1,N)];
    delay_array_6   = [0,zeros(1,N)];
    delay_array_7   = [0,zeros(1,N)];
    delay_array_8   = [0,zeros(1,N)];
    delay_array_9   = [0,zeros(1,N)];
    delay_array_10  = [0,zeros(1,N)];
    delay_array_11   = [0,zeros(1,N)];
    delay_array_12   = [0,zeros(1,N)];
    delay_array_13   = [0,zeros(1,N)];
    delay_array_14   = [0,zeros(1,N)];
    delay_array_15   = [0,zeros(1,N)];
    delay_array_16   = [0,zeros(1,N)];
    delay_array_17   = [0,zeros(1,N)];
    delay_array_18   = [0,zeros(1,N)];
    delay_array_19   = [0,zeros(1,N)];
    delay_array_20   = [0,zeros(1,N)];
    delay_array_31   = [0,zeros(1,N)];
    delay_array_32   = [0,zeros(1,N)];
    delay_array_33   = [0,zeros(1,N)];
    delay_array_34   = [0,zeros(1,N)];
    delay_array_35   = [0,zeros(1,N)];
    delay_array_36   = [0,zeros(1,N)];
    delay_array_37   = [0,zeros(1,N)];
    delay_array_38   = [0,zeros(1,N)];
    delay_array_39   = [0,zeros(1,N)];
    delay_array_40   = [0,zeros(1,N)];
    delay_array_41   = [0,zeros(1,N)];
    delay_array_42   = [0,zeros(1,N)];
    delay_array_43   = [0,zeros(1,N)];
    delay_array_44   = [0,zeros(1,N)];
    delay_array_45   = [0,zeros(1,N)];
    delay_array_46   = [0,zeros(1,N)];
    delay_array_47   = [0,zeros(1,N)];
    delay_array_48   = [0,zeros(1,N)];
    delay_array_49   = [0,zeros(1,N)];
    delay_array_50   = [0,zeros(1,N)];

    delay_array_M1  = [0,normrnd(1000,100,1,N)];
    delay_array_S1  = [0,normrnd(1000,100,1,N)];
    delay_array_M2  = [0,normrnd(1000,100,1,N)];
    delay_array_S2  = [0,normrnd(1000,100,1,N)];
    delay_array_M3  = [0,normrnd(1000,100,1,N)];
    delay_array_S3  = [0,normrnd(1000,100,1,N)];
    delay_array_M4  = [0,normrnd(1000,100,1,N)];
    delay_array_S4  = [0,normrnd(1000,100,1,N)];
    
    switch_array_1  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_2  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_3  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_4  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_5  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_6  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_7  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_8  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_9  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_10 = [0,binornd(1,0.1*ones(1,N))];
    switch_array_11  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_12  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_13  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_14  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_15  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_16  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_17  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_18  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_19  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_20  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_31  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_32  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_33  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_34  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_35  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_36  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_37  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_38  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_39  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_40  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_41  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_42  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_43  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_44  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_45  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_46  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_47  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_48  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_49  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_50  = [0,binornd(1,0.1*ones(1,N))];

    results=sim(model_name);
    t=(results.get("conn_time").Data(end,end));
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
fprintf("Average connection time : ");
disp(mean(data));
fprintf("Median simulation time : ");
disp(median(runtimes));