## Elliptical Cropper {#sec:ellipticalCropper}

When the Hough Circle Detector has detected a circle, there is no guarantee that what is detected is actually a circle, the result might just be an ellipse. If what is detected is actually an ellipse, and a circular cropping is used, then the cropping will cut important data from the result and leave irrelevant data in. In order to lower the signal to noise ratio, a elliptical cropper is therefore needed. As this project uses OpenCV the `fitEllipse` function will be used, which implements the Direct Least Square Fitting of Ellipses [@fitzgibbon_direct_1996] [@opencv_fitellipse].

This section will describe why fitting of ellipses are more powerful than circles and how the Direct Least Square Fitting of Ellipses works.

### Ellipses and Circles {#sec:elliCirc}

The general equation for a circle with the origin on $(0,0)$ can be seen on [@eq:circleEq], where $r$ is the radius of the circle.

$$
x^2 + y^2 = r^2
$$ {#eq:circleEq}

The general equation for an ellipse with the origin $(0,0)$ can be seen on [@eq:ellipseEq], where $a$ and $b$ are horizontal and vertical radius. Whichever is the larger is the major axis and the other the minor axis, except when $a=b$, in which case it is a circle.

$$
\frac{x^2}{a^2}+\frac{y^2}{b^2} = 1
$$ {#eq:ellipseEq}

The reason ellipse fitting is more powerful than circle fitting is that [@eq:circleEq] can be rewritten to be the same as [@eq:ellipseEq]. By dividing both sides in [@eq:circleEq] with $r^2$ results in the same equation as [@eq:ellipseEq] where $a=r$ and $b=r$. Using an ellipse fitter allows for fitting both ellipses and circles. 

<div id="fig:circElliAngle">
![Perpendicular view of Speed sign](report/assets/pictures/CameraPerpendicular.pdf){#fig:circElliAngleA width=40%}
\enspace
![View of a speed sign at an angle](report/assets/pictures/CameraAngle.pdf){#fig:circElliAngleB width=40%}

Shows a circle which becomes an ellipse when viewed at an angle
</div>

As can be seen on [@fig:circElliAngle], speed signs, when viewed at an angle, become an ellipse. Without the angle ([@fig:circElliAngleA]) a circle fitter would work fine, but as soon as the angle is not perfect ([@fig:circElliAngleB]) an ellipse fitter works better.

### General Conic

In cartesian geometry, the ellipse is defined as a quadric, that is, a set of points $(X, Y)$ that satisfy the equation seen on [@eq:genCone] provided $b^2-4ac<0$ [@young_precalculus_2010].

$$
ax^{2}+bxy+cy^{2}+dx+ey+f = 0
$$ {#eq:genCone}

This equation can be rewritten into the standard ellipse equation, by setting $a=\frac{1}{a^2}$, $c=\frac{1}{b^2}$, $f=1$, and the rest of the coeffients to $0$. The [@eq:genCone] can be used to identify any conic given the conic type's constraint. The problem of fitting an ellipse can be considered as a Least Square Fitting problem, where the optimal solution has $a=\frac{1}{a^2}$, $c=\frac{1}{b^2}$, $f=1$, and the rest of the coeffients to $0$.

### Direct Least Square Fitting of Ellipses

Creating a function from [@eq:genCone] as seen in [@eq:genConeFunc] where $a=[a \enspace b \enspace c \enspace d \enspace e \enspace f]^T$ and $x=[x^2 \enspace xy \enspace y^2 \enspace x \enspace y \enspace 1]$ creates an algebraic distance, from point $(x,y)$ to the actual conic $F(a,x)$.

$$
F(a,x) = a\cdot x = ax^{2}+bxy+cy^{2}+dx+ey+f = 0
$$ {#eq:genConeFunc}

Fitting the general conic can be solved by minimizing the sum of the squared algebraic distances of the curve to the $N$ data points $x_i$ as seen in [@eq:lfsSum] [@fitzgibbon_direct_1996].

$$
\Delta(a) = \sum_{i=1}^{\text{N}} F(a, x_i)^2
$$ {#eq:lfsSum}

If the result should be an ellipse, then the constraint $4ac-b^2=1$ must be satisfied. This constraint is a quadratic constraint which can be described as $a^TCa=1$ which can be expressed in matrix form as seen in [@eq:lfsC] [@fitzgibbon_direct_1996].

$$
a^T \begin{bmatrix}
    0 &  0 & 2 & 0 & 0 & 0 \\
    0 & -1 & 0 & 0 & 0 & 0 \\
    2 &  0 & 0 & 0 & 0 & 0 \\
    0 &  0 & 0 & 0 & 0 & 0 \\
    0 &  0 & 0 & 0 & 0 & 0 \\
    0 &  0 & 0 & 0 & 0 & 0 \\
\end{bmatrix} a = 1
$$ {#eq:lfsC}

Rewriting [@eq:lfsSum] provides [@eq:lfsSum2] where $D$ is $[x_1 \enspace x_2 \enspace ...\enspace x_n]^T$ and $S$ is a $6 \times 6$ scatter matrix $\sum D_i^T D_i$.

$$
\Delta(a, x) = \sum_{i=1}^N a^T D_{i}^{T} D_i a = a^T Sa
$$ {#eq:lfsSum2}

The aim then is to find an $a$ that minimizes $\Delta(a, x)$ but still results in an ellipse. Observing $F(a,x) = 0$ we find that it is independent of scaling in $a$, therefore we can replace this condition with $a^T Ca=\phi$ for some positive number $\phi$. With everything above, we can construct a constrained optimization problem as seen in [@eq:lfsProblem][@foreest_fitting], where the vector $a$ which solves the problem is also the ellipse.

$$
\underset{a}{\operatorname {arg\,min}} \{\Delta(a,x) | a^T Ca = \phi\}
$$ {#eq:lfsProblem}

As $Sa = \lambda Ca$ [@fitzgibbon_direct_1996] can be rewritten as a generalized eigenvalue problem as seen in [@eq:lfsEig] [@foreest_fitting], this problem is easy for a computer to solve. The solution is then the largest eigenvector, which also is our $a$.

$$
\frac{1}{\lambda}a = S^{-1}Ca
$$ {#eq:lfsEig}
