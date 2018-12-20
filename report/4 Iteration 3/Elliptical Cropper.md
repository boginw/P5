## Elliptical Cropper {#sec:ellipticalCropper}

When the Hough Circle Detector has detected a circle, there is no guarantee that what is detected as a circle is not, in fact, an ellipse on the original picture (illustrated on [@fig:circElliAngle]). In case the detected circle actually is an ellipse, and circular cropping is used, the cropping will either cut out essential data from the result or leave in irrelevant data. In order to lower the signal to noise ratio (i.e., the ratio between relevant and irrelevant data), an elliptical cropper is needed. As this project uses OpenCV, the `fitEllipse` function will be used, which implements the Direct Least Square Fitting of Ellipses [@fitzgibbon_direct_1996] [@opencv_fitellipse].

This section will describe why fitting of ellipses is more powerful than fitting of circles, and how the Direct Least Square Fitting of Ellipses works.

### Ellipses and Circles {#sec:elliCirc}

The general equation for a circle with the center in $(0,0)$ can be seen on [@eq:circleEq], where $r$ is the radius of the circle.

$$
x^2 + y^2 = r^2
$$ {#eq:circleEq}

The general equation for an *ellipse* with the center in $(0,0)$ can be seen on [@eq:ellipseEq], where $a$ and $b$ are horizontal- and vertical radius. Whichever radius is larger is the major axis of the ellipse and the other the minor axis, except when $a=b$, in which case it is a circle.

$$
\frac{x^2}{a^2}+\frac{y^2}{b^2} = 1
$$ {#eq:ellipseEq}

The reason ellipse fitting is more powerful than circle fitting is that [@eq:circleEq] can be rewritten to be the same as [@eq:ellipseEq] by dividing both sides in [@eq:circleEq] with $r^2$. Doing so results in an equation in the same format as [@eq:ellipseEq] where $a=r$ and $b=r$, thereby showing that any circle can be described as an ellipse, i.e., using an ellipse fitter allows for fitting both ellipses and circles.  

<div id="fig:circElliAngle">
![Perpendicular view of speed sign - appears circular](report/assets/pictures/CameraPerpendicular.pdf){#fig:circElliAngleA width=40%}
\enspace
![View of a speed sign at an angle - appears elliptical](report/assets/pictures/CameraAngle.pdf){#fig:circElliAngleB width=40%}

Shows a circular speed sign which appears as an ellipse when viewed at an angle
</div>

### General Conic

In cartesian geometry, the ellipse is defined as a quadric, that is, a set of points $(X, Y)$ that satisfy the equation seen on [@eq:genCone] provided $B^2-4AC<0$ [@young_precalculus_2010].

$$
Ax^{2}+Bxy+Cy^{2}+Dx+Ey+F = 0
$$ {#eq:genCone}

This equation can be rewritten into the standard ellipse equation, by setting $A=\frac{1}{a^2}$, $C=\frac{1}{b^2}$, $F=1$, and the rest of the coefficients to $0$. The [@eq:genCone] can be used to identify any conic given the conic type's constraint. The problem of fitting an ellipse can be considered as a Least Square Fitting problem, where the optimal solution has $A=\frac{1}{a^2}$, $C=\frac{1}{b^2}$, $F=1$, and the rest of the coefficients to $0$.

### Direct Least Square Fitting of Ellipses

Creating a function from [@eq:genCone] as seen in [@eq:genConeFunc] where $\vec{A}=[A \enspace B \enspace C \enspace D \enspace E \enspace F]^T$ and $\vec{X}=[x^2 \enspace xy \enspace y^2 \enspace x \enspace y \enspace 1]^T$ creates an algebraic distance, from point $(x,y)$ to the actual conic $F(\vec{A},\vec{X})=0$.

$$
F(\vec{A},\vec{X}) = \vec{A}\cdot\vec{X} = Ax^{2}+Bxy+Cy^{2}+Dx+Ey+F
$$ {#eq:genConeFunc}

Fitting the general conic can be solved by minimizing the sum of the squared algebraic distances from the ellipse to the $N$ data points $\vec{X}_i$ as seen in [@eq:lfsSum] [@fitzgibbon_direct_1996].

$$
\Delta(\vec{A}) = \sum_{i=1}^{\text{N}} F(\vec{A}, \vec{X}_i)^2
$$ {#eq:lfsSum}

<!-- 

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
 -->
