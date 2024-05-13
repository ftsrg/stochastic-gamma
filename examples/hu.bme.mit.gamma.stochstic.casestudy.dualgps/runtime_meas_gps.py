import os
import csv
import subprocess
import time

filename = "measurements/gps_runtime_meas_1.csv"

repeat_num=1

scripts=[
    "simulator-gen/reliability_2_simulator.py",
    "simulator-gen/reliability_4_simulator.py",
    "simulator-gen/reliability_8_simulator.py",
    "simulator-gen/reliability_16_simulator.py",
    "simulator-gen/reliability_32_simulator.py"
    ]

runtimes=[]

for script in scripts:
    for i in range(repeat_num):
        print("---------------------------------------------")
        print (f"Running '{script}' ")
        print("---------------------------------------------")
        t0=time.time()
        #subprocess.run(["python",script])
        os.system("python "+script)
        t1=time.time()
        dt=(t1-t0)
        runtimes.append(dt)
        print("---------------------------------------------")
        print (f"Script run in {dt:.2f} s")
        print("---------------------------------------------")
        print("")
    

header=["script","runtime"]

print("Saving runtimes")

with open(filename, 'w', newline='\n') as csvfile:
    csvwriter = csv.writer(csvfile, delimiter=',')
    csvwriter.writerow(header)
    for i in range(len(runtimes)):
        csvwriter.writerow([scripts[i],runtimes[i]])