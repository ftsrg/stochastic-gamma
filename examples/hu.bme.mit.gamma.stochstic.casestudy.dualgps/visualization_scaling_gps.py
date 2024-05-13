import pandas as pd
from plotly.subplots import make_subplots
import re
import plotly.graph_objects as go
import math
import numpy as np
import plotly.express as px

import os


df1 = pd.read_csv('measurements/gps_runtime_meas_1.csv')
df1["method"]="sim."
df1["components"]=df1["script"].map(lambda str1 :int(re.search("\d+", str1).group()))

df2 = pd.read_csv('measurements/gps_runtime_meas_is_10e.csv')
df2["method"]="IS"
df2["components"]=df2["script"].map(lambda str1 :int(re.search("\d+", str1).group()))

df=df1.append(df2)

fig = px.scatter(
    df, 
    x="components", 
    y="runtime", 
    color="method",
    trendline="ols",
    #title="GPS Simulation",
    width=400, height=400,
    labels={
                     "components": "number of sensors",
                     "runtime": "runtime of analysis [s]"
                 }
    )

fig.update_layout(legend=dict(
    yanchor="top",
    y=0.95,
    xanchor="left",
    x=0.05
))


if not os.path.exists("images"):
    os.mkdir("images")
fig.write_image("images/gps_scaling_meas.svg")
fig.write_image("images/gps_scaling_meas.pdf")

print("visualization is finished")

