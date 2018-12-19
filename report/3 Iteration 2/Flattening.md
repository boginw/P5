### Flattening {#sec:flattening}
As mentioned in [@sec:skip_prep], being able to normalize the input to a neural network yields better, more consistent results. An identified ellipse might be of multiple different shapes and orientations, and therefore not guaranteed consistent. This section will describe how such an elliptical shape will be normalized into a circle.

#### The Conceptual Idea
It is assumed that an ellipse already has been identified on an image, and we know the pixel-coordinates of its center. On [@fig:EllipseFlattening1] an example ellipse has been drawn, along with some grid lines to help visualize how each transformation affects the image.

![A detected ellipse](report/assets/pictures/Ellipse1.png){#fig:EllipseFlattening1 width=35%}

In order to achieve a transformation from an ellipse to a circle, the _perspective transformation_ from OpenCV is used [@opencv_geo_transform], which relies on knowing the coordinates of 4 points on an input, and four corresponding coordinates for where those points should end up on the output. To identify the points to be used as input, the ellipse is first rotated until its focal points are aligned with either the x- or y-axis, as depicted in [@fig:EllipseFlattening2], where the four points have been identified (top-most, right-most, bottom-most and left-most point on the ellipse).

![Rotating plus identifying boundaries](report/assets/pictures/Ellipse2_1.png){#fig:EllipseFlattening2 width=35%}

Determining where these four points should be ending up, when the ellipse has been transformed into a circle, is a rather trivial matter of choosing new coordinates that are equally far apart from the center of the ellipse, while still being the top-, left-, bottom-, and rightmost points. The chosen points for the output, as well as the now transformed ellipse, can be seen on [@fig:EllipseFlattening3].

![Warping the image, based on identified points](report/assets/pictures/Ellipse3_1.png){#fig:EllipseFlattening3 width=35%}

The last step before the flattening-process is completed, is to undo the rotation done in step one, simply by rotating the same amount of degrees as initially, but in the opposite direction. The final result is shown on [@fig:EllipseFlattening4].

![Image rotated back to original rotation](report/assets/pictures/Ellipse4.png){#fig:EllipseFlattening4 width=35%}

Given a successful traversal of the described steps, the data for the NN should now be a controlled circle instead of an unpredictable ellipse, and both the training and recognizing should have a more standardized input for each picture being processed.   
