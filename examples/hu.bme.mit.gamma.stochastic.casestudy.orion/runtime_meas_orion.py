import os
import csv
import subprocess
import time

filename = "measurements/orion_runtime_meas_2.csv"

scripts=[
    "simulator-gen/orion_performance_2_simulator.py",
    "simulator-gen/orion_performance_4_simulator.py",
    "simulator-gen/orion_performance_8_simulator.py",
    "simulator-gen/orion_performance_16_simulator.py",
    "simulator-gen/orion_performance_32_simulator.py"
    ]

runtimes=[]

for script in scripts:
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