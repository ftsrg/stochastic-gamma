import sys
sys.path.append('simulator-gen/.')
import pandas as pd
from plotly.subplots import make_subplots

import plotly.graph_objects as go
import math
import numpy as np

import os

df = pd.read_csv('measurements/orion_cond_conv_meas.csv')

df["error"]=df["ss"]*2.0/np.sqrt(df["ess"])
df["label"]=df["ess"].apply(lambda x: f'({x:.0f})')
df["lower_bound"]=(df["wf_mean"]-2.0*df["wf_ss"]/np.sqrt(df["num_sample"]))/(df["w_mean"]+2.0*df["w_ss"]/np.sqrt(df["num_sample"]))
df["upper_bound"]=(df["wf_mean"]+2.0*df["wf_ss"]/np.sqrt(df["num_sample"]))/(df["w_mean"]-2.0*df["w_ss"]/np.sqrt(df["num_sample"]))
df=df[(df['num_sample']>=200)]


fig = make_subplots(
    rows=1, cols=2,
    subplot_titles=("Orion simulation analysis", "Orion importance analysis"), 
    horizontal_spacing = 0.07)
fig.add_trace(go.Scatter(
        x=df["num_sample"],
        y=df["est_mean"],
        text =df["label"],
        mode="markers+text",
        showlegend = False,
        error_y=dict(
            type='data',
            symmetric=True,
            array=df["error"]*2.0)
            #array=df["upper_bound"] - df["est_mean"],
            #arrayminus=df["est_mean"]-df["lower_bound"])
        ),
    row=1, col=2)

df_sim = pd.read_csv('measurements/orion_sim_conv_meas.csv')
df_sim=df_sim[(df_sim['num_sample']>=1000)]
df_sim["label"]=df_sim["num_sample"].apply(lambda x: f'({x})')

fig.add_trace(go.Scatter(
        x=df_sim["num_sample"],
        y=df_sim["mu_est"],
        text =df_sim["label"],
        mode="markers+text",
        showlegend = False,
        error_y=dict(
            type='data',
            symmetric=False,
            array=df_sim["ci_high"] - df_sim["mu_est"],
            arrayminus=df_sim["mu_est"]-df_sim["ci_low"])
        ),
    row=1, col=1)


fig.update_traces(textposition='top center')
fig.update_xaxes(type='log')
fig.update_layout(width=1100,height=500)
fig.update_annotations(font_size=25)

fig['layout']['xaxis']['title']=dict(text="simulation number", font=dict(size=22),standoff=1)
fig['layout']['xaxis2']['title']=dict(text="simulation number", font=dict(size=22),standoff=1)
fig['layout']['yaxis']['title']=dict(text="analysis result", font=dict(size=22),standoff=1)
fig['layout']['yaxis2']['title']=dict(text="analysis result", font=dict(size=22),standoff=1)

print("Export image")

if not os.path.exists("images"):
    os.mkdir("images")
fig.write_image("images/orion_conv_meas.svg")
fig.write_image("images/orion_conv_meas.pdf")

print("visualization is finished")