import sys
sys.path.append('simulator-gen/.')
import os
import csv
import subprocess
import time

import logging

# set up logging to file - see previous section for more details
logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
                    datefmt='%m-%d %H:%M',
                    filename='./measurements/is_meas_10e_r5_backup.log',
                    filemode='w')
# define a Handler which writes INFO messages or higher to the sys.stderr
console = logging.StreamHandler()
console.setLevel(logging.INFO)
# set a format which is simpler for console use
formatter = logging.Formatter('%(name)-12s: %(levelname)-8s %(message)s')
# tell the handler to use this format
console.setFormatter(formatter)
# add the handler to the root logger
logging.getLogger('').addHandler(console)

# Now, we can log to the root logger, or any other logger. First the root...
logging.info('Start measurements')

r_num=5

filename = "measurements/orion_runtime_meas_is_10e_r5.csv"

scripts=[
    "simulator-gen/orion_performance_cond_2_simulator.py",
    "simulator-gen/orion_performance_cond_4_simulator.py",
    "simulator-gen/orion_performance_cond_8_simulator.py",
    "simulator-gen/orion_performance_cond_16_simulator.py",
    "simulator-gen/orion_performance_cond_32_simulator.py"
    ]



runtimes=[]

for script in scripts:
    for r in range(r_num):
        print("---------------------------------------------")
        print (f"Running '{script}' ")
        print("---------------------------------------------")
        t0=time.time()
        #subprocess.run(["python",script])
        os.system("python "+script)
        t1=time.time()
        dt=(t1-t0)
        runtimes.append([script,dt])
        logging.info(f'measurement result: ["{script}", "{dt}"]')
        print("---------------------------------------------")
        print (f"Script run in {dt:.2f} s")
        print("---------------------------------------------")
    

header=["script","runtime"]

print("Saving runtimes")

with open(filename, 'w', newline='\n') as csvfile:
    csvwriter = csv.writer(csvfile, delimiter=',')
    csvwriter.writerow(header)
    for i in range(len(runtimes)):
        csvwriter.writerow(runtimes[i])