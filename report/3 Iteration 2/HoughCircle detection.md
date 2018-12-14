## Detecting Red Circles
This section will explain how circles are detected in the system, and it will try to justify why that approach was chosen, as well as try to highlight any issues or shortcomings of the approach. The section will also show some calculations on the complexity of the algorithms used; this is to ensure that the implementation will be able to perform on the designated system.

### The Overview
Detecting red circles in an image is a task much easier to specify than to solve. Some steps are necessary in order to be able to detect red circles reliably. These steps will be explained below.

![Step overview of circle detection](report/assets/pictures/CircleDetection.png){#fig:step_circle_detection}

On [@fig:step_circle_detection] the steps of detecting a speed sign are shown in a very high abstraction level. Every step will be explained in detail below, since the abstraction level used in the illustration can be misleading.


### Isolating Red Channel
The reason one would like to isolate the red channel of the input image is that, in Denmark, every speed sign has a reflective red circle surrounding the number. There exist many different techniques for achieving this. The implemented technique is to convert the RGB input image to YCbCr (also known as YUV), and then isolate the Cr component. Doing so will result in a grayscale image where the value of each pixel denotes the amount of red in the pixel, i.e. the closer a pixel value is to 255 (in 8-bit grayscale values) the redder that pixel was in the original RGB input image. Isolating the red component is possible because the YCbCr color space is defined by a transformation from the RGB color space - hence it is always possible to transform an RGB signal into a YCbCr signal and vice versa. The YCbCr color space is a three-component space; Luminance (denoted as Y), Chrominance toward blue (denoted as Cb) and Chrominance toward red (denoted as Cr)[@ITU-T-T-871]. To futher understand the difference in isolating the red channel in RGB and YCbCr [@fig:redChannelExtracting] can be examined.

<div id="fig:redChannelExtracting">
![All channels from RGB image](report/assets/pictures/rgb_full_example.jpg){#fig:fullRGBImage width=33%}
\enspace
![Red channel extracted from RGB image](report/assets/pictures/rgb_example.png){#fig:rgbRedChannel width=33%}
\enspace
![Red channel extracted from YCbCr image](report/assets/pictures/ycbcr_example.png){#fig:ycbcrRedChannel width=33%}

 Shows the difference between extracting red channel from RGB and YCbCr
</div>

Isolating the Cr component allows for a more effective search later on, since only potential circles that are red will be present in the data.

We do this by utilizing the OpenCV framework; it has a build in converter from RGB to YCrCb, which is a linear transformation, thereby having time and space complexity of $O(n)$, the function used is:[@opencv_image_transformation]

$$ Y = 0.299 R + 0.587 G + 0.114 B $$
$$ Cb = (B - Y) + delta $$
$$ Cr = (R - Y) + delta $$
$$ delta = \begin{cases}
        128 & \quad \text{for 8-bit images}\\
        32768 & \quad \text{for 16-bit images}\\
        0.5 & \quad \text{for floating-point images}
    \end{cases}
$$

### Removing Noise and Apply the Threshold
As shown in [@fig:step_circle_detection] after isolating the red channel, there still might be elements of red that are not quite red enough to realistically be a speed sign. Therefore a threshold is applied, meaning that every pixel is examined and if the value of said pixel is higher than a predefined threshold, that pixel is assigned a value of 255. If the pixel's value is lower than the threshold it would be assigned a value of 0. Doing so allows for limiting the search algorithm to only calculate realistic candidates for speed signs and not every somewhat red circle. Following this threshold-step, a blur is applied to the image. This is to remove any remaining noise and to ensure as smooth a circle as possible for the detector to look for.

### Searching for circles
The last step is to detect the circle, which is done using the OpenCV HoughCircle method. This method will detect multiple circles in the picture and return the center points and radii of every detected circle. One thing to note is that this results in bounding boxes of perfect circles, which means that if the camera was not pointing in an angle perpendicular to the speed sign, the result of HoughCircle will not be a perfect fit on the detected circle. This issue will be elaborated on in section [@sec:elliCirc].

The HoughCircle algorithm is a Hough circle Transform algorithm, which works similarly to Hough line transform. However, instead of having an accumulator plane, one would need an accumulator volume with three dimensions: x, y, and the radius. Doing so would result in a significant increase in time- and memory-complexity.  Therefore the OpenCV library avoids this by using a method called Hough gradient method [@LearnOpenCV]. 

![Illustrative example of how the Hough gradient method increments all point on the slope](report/assets/pictures/gradient_of_circle.pdf){#fig:points_on_gradient_slope}

The Hough gradient method works by first performing a Canny edge detection on the image; then, for all non-zero pixels, the local gradient is calculated using the Sobel x- and y-derivatives. The slope of the local gradient denotes a line on which every point within a specified upper and lower bound is incremented in the accumulator; the concept is visualized on [@fig:points_on_gradient_slope]. Simultaneously, the location of all the non-zero pixels are noted. All the candidates for center points are then selected by them exceeding a given threshold, and being larger than all of their neighbors, due to this method yielding multiple center points for the same circle, therefore restricting to only the biggest in the neighboorhood allows for more precise results. The list of candidates is then sorted from highest to lowest on their accumulator value. The radius is then calculating by yet again looking at all the non-zero pixels, and calculating the Euclidean distance from every non-zero pixel to the center candidate. Starting from the smallest distance, to the center candidate, to the maximum radius, a radius is selected that best fit the non-zero pixels. If a center has sufficient support form the non-zero pixels on the detected edge and is not too close to a center already identified, it is allowed. Otherwise, it should get removed. The reason one looks at the distance between the center candidates to decide which to keep, is a logical step. If two circles are identified, but the center of $circle_2$ lies within the area of $circle_1$ then the chance is that $circle_2$ is merely a subsection of $circle_1$, since the candidates with most support from the edges are sorted first [@LearnOpenCV].
