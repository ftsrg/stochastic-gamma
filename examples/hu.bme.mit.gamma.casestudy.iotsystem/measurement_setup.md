# Measurement setup

$p=e^x$

$$z=\begin{bmatrix}
    x_1\\
    x_2\\
    \dotsb\\
    x_n
\end{bmatrix}$$

$$f(z)=x_i$$

$$E[p_{failure}]\approx\hat p _{failure}$$


$$E[p_{failure}]\approx\frac{1}{L}\sum_{l=1}^{L}\frac{p(z_l)}{q(z_l)}f(z_l)=\frac{1}{L}\sum_{l=1}^{L} u_l$$

$$u_l=\frac{p(z_l)}{q(z_l)}f(z_l)$$

$$E[p_{failure}]\approx\sum_{l=1}^{L}\frac{\frac{p(z_l)}{q(z_l)}}{\sum_{j=1}^{L}\frac{p(z_j)}{q(z_j)}}f(z_l)=\frac{1}{\sum_{j=1}^{L}\frac{p(z_j)}{q(z_j)}}\sum_{l=1}^{L}\frac{p(z_l)}{q(z_l)}f(z_l)$$


### Calculation method 2

$$E[p_{failure}] \approx \frac{1}{\sum_{j=1}^{L}\frac{p(z_j)}{q(z_j)}}\sum_{l=1}^{L}\frac{p(z_l)}{q(z_l)}f(z_l) = \frac{1}{\sum_{j=1}^{L}r_l}\sum_{l=1}^{L}r_l\cdot f_l$$

where

$$Dom(r_l) \in \{r_0,r_1\}$$
$$p_r=P(r_l=r_1); 1-p_r=P(r_l=r_0)$$
$$f_l=f(z_l)$$
$$f_l^{r_1}=f(z_l) | (r_l=r_1)$$

thus

$$E[p_{failure}] \approx \frac{1}{\hat p_rr_1+(1- \hat p_r)r_2}\left(r_1\sum_{l=1}^{L_{r_1}}f_l^{r_1} + r_2\sum_{l=1}^{L_{r_2}}f_l^{r_2}\right)$$

$$E[p_{failure}] \approx \frac{1}{\hat p_rr_1+(1- \hat p_r)r_2}\left(r_1\hat p_r \hat \mu_{f_l^{r_1}}+r_2\hat (1- \hat p_r) \hat \mu_{f_l^{r_2}}\right)$$

$$E[p_{failure}] \approx \frac{1}{\hat p_rr_1+(1- \hat p_r)r_2}\left(r_1\hat p_r \hat \mu_{f_l^{r_1}}+r_2\hat (1- \hat p_r) \hat \mu_{f_l^{r_2}}\right)$$

$$E[p_{failure}] \approx \frac{1}{\hat p_rr_1+(1- \hat p_r)r_2}  r_1\hat p_r \hat \mu_{f_l^{r_1}}+ \frac{1}{\hat p_rr_1+(1- \hat p_r)r_2}r_2\hat (1- \hat p_r) \hat \mu_{f_l^{r_2}} $$

$$E[p_{failure}] \approx \frac{1}{1 +\frac{(1- \hat p_r)r_2}{\hat p_rr_1}}   \hat \mu_{f_l^{r_1}} + \frac{1}{\frac{\hat p_rr_1}{r_2\hat (1- \hat p_r) }+1}\hat \mu_{f_l^{r_2}} $$

$$E[p_{failure}] \approx \frac{1}{1 +\frac{r_2}{\hat p_rr_1}-\frac{r_2}{r_1}}   \hat \mu_{f_l^{r_1}} + \frac{1}{\frac{\hat p_rr_1}{r_2\hat (1- \hat p_r) }+1}\hat \mu_{f_l^{r_2}} $$

### Calculation method 1


$$E[p_{failure}]\approx\frac{\sum_{l=1}^{L}\frac{p(z_l)}{q(z_l)}f(z_l)}{\sum_{j=1}^{L}\frac{p(z_j)}{q(z_j)}}=\frac{\frac{\sum_{l=1}^{L}\frac{p(z_l)}{q(z_l)}f(z_l)}{L}}{\frac{\sum_{j=1}^{L}\frac{p(z_j)}{q(z_j)}}{L}}$$


$$E[p_{failure}] \in \left[ lower_p, upper_p  \right]=\left[ \frac{lower_{nom.}}{upper_{denom.}}, \frac{upper_{nom.}}{lower_{denom.}}  \right] $$

For large $L$ and for large $ESS$:

$$\hat p _{failure} \sim \mathcal{N}(p_{failure},Var[u])$$

The confidence interval of $\hat p_{failure}$ can be calculated:

$$I_{conf}=\left[ \hat p_{failure}-t_{n-1,99.5\%}\cdot ss^* , \hat p_{failure}+t_{n-1,99.5\%}\cdot ss^*\right]$$


PX=NX/n

var(NX)=np(1-p)

var(PX)=p(1-p)/n

scatter(PX)=sqrt(p(1-p)/n)  
