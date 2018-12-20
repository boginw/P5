## Detecting Red Circles
One of the useful algorithms identified in the OpenCV API was Hough Circle Transform.
This section will explain how this project uses the Hough Circle Transform algorithm to handle the activity of detecting red circles within a picture. 

### The Overview

![Step overview of circle detection](report/assets/pictures/CircleDetection.png){#fig:step_circle_detection width=50%}

On [@fig:step_circle_detection] the steps of detecting a speed sign are shown in a very high abstraction level. Every step will be explained in the following sections.


### Isolating Red Channel
As every speed sign in Denmark has a reflective red circle surrounding the number, it is advantageous for this project to isolate the red channel of the input image. 

The implemented technique is to convert the RGB input image to YCbCr and then isolate the Cr component. Doing so will "convert" every pixel from three channels to a single channel, where the value of each converted pixel reflects the amount of red color in its corresponding original pixel. Such a single channeled image would in practice be represented as a grayscale image, as seen in [@fig:ycbcrRedChannel].

The YCbCr color space is a three-component space; Luminance (denoted as Y), Chrominance toward blue (denoted as Cb) and Chrominance toward red (denoted as Cr)[@ITU-T-T-871].

To further understand the difference in isolating the red channel in RGB and YCbCr [@fig:redChannelExtracting] can be examined.

<div id="fig:redChannelExtracting">
![All channels from RGB image](report/assets/pictures/rgb_full_example.jpg){#fig:fullRGBImage width=33%}
\enspace
![Red channel extracted from RGB image](report/assets/pictures/rgb_example.png){#fig:rgbRedChannel width=33%}
\enspace
![Red channel extracted from YCbCr image](report/assets/pictures/ycbcr_example.png){#fig:ycbcrRedChannel width=33%}

 Shows the difference between extracting red channel from RGB and YCbCr
</div>

The conversion is done by utilizing a part of the OpenCV API.

### Removing Noise and Apply the Threshold
As shown in [@fig:step_circle_detection], after isolating the red channel, there still might be elements of red that are not part of a speed sign. Therefore a threshold is applied, meaning that every pixel is examined and compared to a predefined threshold. Pixels above the threshold are assigned a value of 255, pixels below would be assigned a value of 0. Doing so allows for limiting the search algorithm to only calculate probabilistic candidates for speed signs and not every somewhat red object in the picture. Following this threshold-step, a blur is applied to the image to remove any remaining noise, and to ensure as smooth a circle as possible for the detector to look for.

### Searching for circles {#sec:searchingForCircles}
The last step is to detect the circle, which is done using the `HoughCircle` function found in the OpenCV API. This function will detect multiple circles in the picture and return the center points and radii of every detected circle. One thing to note is the results are assumed to be perfect circles, which might not reflect the actual pixels containing the speed sign, i.e. if the camera was not pointing in an angle perpendicular to the speed sign, the pixels representing the speed sign might be occupying an ellipse. This issue will be elaborated on in section [@sec:elliCirc].

The algorithm works similarly to Hough line transform. However, instead of using a two-dimensional accumulator plane to vote on a linear equation ($y=ax+b$), one would need an accumulator volume with three dimensions (a, b, and radius) to vote on a circle equation ($(x-a)^2+(y-b)^2=r^2$), which is a much more costly operation. Therefore the OpenCV library avoids using the accumulator volume, by utilizing Hough gradient method.[@LearnOpenCV] 

The Hough gradient method works by first performing a Canny edge detection on the image, which identifies relevant edge pixels. Then, for all non-zero pixels, a line, denoted by a local gradient, is incremented in the accumulator. The local gradient used to denote this line is calculated using the Sobel $x$- and $y$-derivatives. [@LearnOpenCV]
The concept is visualized on [@fig:points_on_gradient_slope]. 

![Illustrative example of how the Hough gradient method increments all point on the slope](report/assets/pictures/gradient_of_circle.pdf){#fig:points_on_gradient_slope}

After denoting a line, the location of that edge pixel is stored. All the candidates for center points are then selected by comparing their accumulator plane value to a threshold value. Due to this method possibly yielding multiple center points for the same circle, restricting to only the biggest in a given neighborhood allows for less duplicated circles amongst the results. [@LearnOpenCV]

The newly formed list of candidates is then sorted from highest to lowest accumulator value. The circle radius for each candidate is then calculated by firstly looking at all the edge pixels and calculating the Euclidean distance from edge pixel to center candidate. Then, starting with the smallest distance between edge-pixel and center candidate, going up to the largest distance, a radius is selected to best fit the following criterion: having the largest possible amount of edge pixels, at the lowest possible radius length. [@LearnOpenCV]
