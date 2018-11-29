## Why is this being written?
In chapters **_Elliptical Cropper_** and **_Detecting Red Circles_** it is mentioned that the portion of the picture presumed to contain a speed-sign might be elliptical, which we need to transform into a perfect circle.

## What will be explained?
This section will explain how we plan to do a subroutine of the normalizing-step, by transforming/skewing the picture until the ellipse becomes a perfect circle. The transformation will be done by assuming an ellipse-describing equation has been given: `(x^2 / a^2) + (y^2 / b^2) = r^2` where `x`, `y` and `r` describes a regular circle, and `a` and `b` describes how far a circle has been "stretched" in the x- and y-axis in order to produce the described ellipse as an implicit plot. The goal, getting `a` and `b` to be the same value, and thereby end up describing a perfect circle (`x^2 + y^2 = r^2`), will be achieved via matrix multiplication. There will be no mathematical proof as to why this works, but an illustration/figure will be provided.

## What are your sources?
[https://en.wikipedia.org/wiki/Ellipse#Equation]

## How will you prepare the reader for the next section?
The section will be summarized, and it will be stated that it is now possible to go from any ellipse to a perfect circle, and the NN will now have less variables to consider when trying to recognize the number written on the sign.
