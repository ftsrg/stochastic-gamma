import pandas as pd
from plotly.subplots import make_subplots
import re
import plotly.graph_objects as go
import math
import numpy as np
import plotly.express as px

import os


df1 = pd.read_csv('measurements/orion_runtime_meas_sim_10e_r5.csv')
df1=df1.groupby("script",as_index=False).median()
df1["method"]="sim."
df1["components"]=df1["script"].map(lambda str1 :int(re.search("\d+", str1).group()))

df2 = pd.read_csv('measurements/orion_runtime_meas_is_10e_r5.csv')
df2=df2.groupby("script",as_index=False).median()
df2["method"]="IS"
df2["components"]=df2["script"].map(lambda str1 :int(re.search("\d+", str1).group()))

df=df1.append(df2)

fig = px.scatter(
    df, 
    x="components", 
    y="runtime", 
    trendline="ols",
    color="method",
    title="Orion Case study",
    width=400, height=350,
    labels={
                     "components": "number of endpoints",
                     "runtime": "runtime of analysis [s]"
                 }
    )

fig.update_layout(legend=dict(
    yanchor="top",
    y=0.98,
    xanchor="left",
    x=0.02
))
fig.update_layout(title_y=0.77,title_x=0.20)
fig.update_xaxes(title_standoff = 0)
fig.update_yaxes(title_standoff = 0)

if not os.path.exists("images"):
    os.mkdir("images")
fig.write_image("images/orion_scaling_meas_r5.svg")
fig.write_image("images/orion_scaling_meas_r5.pdf")

print("visualization is finished")


