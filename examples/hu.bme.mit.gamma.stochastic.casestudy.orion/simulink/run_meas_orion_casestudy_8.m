clear
K=13;
N=30;
runtimes=zeros(1,K);
data=ones(1,K);



acc_mode="off" % "normal", "classic", "rapid", "off"
model_name="orion_casestudy_8"
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
    delay_array_51   = [0,zeros(1,N)];
    delay_array_52   = [0,zeros(1,N)];
    delay_array_53   = [0,zeros(1,N)];
    delay_array_54   = [0,zeros(1,N)];
    delay_array_55   = [0,zeros(1,N)];
    delay_array_56   = [0,zeros(1,N)];
    delay_array_57   = [0,zeros(1,N)];
    delay_array_58   = [0,zeros(1,N)];
    delay_array_59   = [0,zeros(1,N)];
    delay_array_60   = [0,zeros(1,N)];
    delay_array_61   = [0,zeros(1,N)];
    delay_array_62   = [0,zeros(1,N)];
    delay_array_63   = [0,zeros(1,N)];
    delay_array_64   = [0,zeros(1,N)];
    delay_array_65   = [0,zeros(1,N)];
    delay_array_66   = [0,zeros(1,N)];
    delay_array_67   = [0,zeros(1,N)];
    delay_array_68   = [0,zeros(1,N)];
    delay_array_69   = [0,zeros(1,N)];
    delay_array_70   = [0,zeros(1,N)];
    delay_array_71   = [0,zeros(1,N)];
    delay_array_72   = [0,zeros(1,N)];
    delay_array_73   = [0,zeros(1,N)];
    delay_array_74   = [0,zeros(1,N)];
    delay_array_75   = [0,zeros(1,N)];
    delay_array_76   = [0,zeros(1,N)];
    delay_array_77   = [0,zeros(1,N)];
    delay_array_78   = [0,zeros(1,N)];
    delay_array_79   = [0,zeros(1,N)];
    delay_array_80   = [0,zeros(1,N)];
    delay_array_81   = [0,zeros(1,N)];
    delay_array_82   = [0,zeros(1,N)];
    delay_array_83   = [0,zeros(1,N)];
    delay_array_84   = [0,zeros(1,N)];
    delay_array_85   = [0,zeros(1,N)];
    delay_array_86   = [0,zeros(1,N)];
    delay_array_87   = [0,zeros(1,N)];
    delay_array_88   = [0,zeros(1,N)];
    delay_array_89   = [0,zeros(1,N)];
    delay_array_90   = [0,zeros(1,N)];

    delay_array_M1  = [0,normrnd(1000,100,1,N)];
    delay_array_S1  = [0,normrnd(1000,100,1,N)];
    delay_array_M2  = [0,normrnd(1000,100,1,N)];
    delay_array_S2  = [0,normrnd(1000,100,1,N)];
    delay_array_M3  = [0,normrnd(1000,100,1,N)];
    delay_array_S3  = [0,normrnd(1000,100,1,N)];
    delay_array_M4  = [0,normrnd(1000,100,1,N)];
    delay_array_S4  = [0,normrnd(1000,100,1,N)];
    delay_array_M5  = [0,normrnd(1000,100,1,N)];
    delay_array_S5  = [0,normrnd(1000,100,1,N)];
    delay_array_M6  = [0,normrnd(1000,100,1,N)];
    delay_array_S6  = [0,normrnd(1000,100,1,N)];
    delay_array_M7  = [0,normrnd(1000,100,1,N)];
    delay_array_S7  = [0,normrnd(1000,100,1,N)];
    delay_array_M8  = [0,normrnd(1000,100,1,N)];
    delay_array_S8  = [0,normrnd(1000,100,1,N)];
    
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
    switch_array_51  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_52  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_53  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_54  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_55  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_56  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_57  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_58  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_59  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_60  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_61  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_62  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_63  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_64  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_65  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_66  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_67  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_68  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_69  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_70  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_71  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_72  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_73  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_74  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_75  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_76  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_77  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_78  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_79  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_80  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_81  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_82  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_83  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_84  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_85  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_86  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_87  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_88  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_89  = [0,binornd(1,0.1*ones(1,N))];
    switch_array_90  = [0,binornd(1,0.1*ones(1,N))];

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