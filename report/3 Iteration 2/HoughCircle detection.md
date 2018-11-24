## Detecting red circles
This section will explain how circles are detected in the system, and it will try to justify why that approach where chosen, also it will try to highlight any issues or shortcomings of the approach. The section will also show some calculations on the complexity of the algorithms used; this is to ensure that the implementation will be able to perform on the designated system.

### The overview
Detecting red circles in an image might sound like a trivial task to do, but there are some steps necessary to be able to detect reliably. These steps will be explained below.

![Step overview of circle detection](report/assets/pictures/CircleDetection.png){#fig:step_circle_detection}

On figure {@fig:step_circle_detection} the steps of detecting a speed sign is showed, in a very high abstraction level. Below every step will be explained in detail since the abstraction level used in the illustration can be misleading.


#### Isolating red channel
The reason one would like to isolate the red channel of the input image is that in Denmark every speed sign has to have a reflective red circle around the number. There exists a number of different techniques for achieving this. The implemented technique is to convert the RGB input image to YCbCr (also known as YUV) and then isolate the Cr component, which will result in a grayscale image where the value of each pixel denotes the amount of red in the pixel, meaning that the closer a pixel value is to 255 (in 8-bit grayscale values) the redder that pixel is in the original RGB input image. Isolating the red component is possible because the YCbCr color space is defined by a transformation from the RGB color space. Hence it is always possible to transform an RGB signal into a YCbCr signal and vice versa. The YCbCr color space is a three-component space; Luminance (denoted as Y), Chrominance toward blue (denoted as Cb) and Chrominance toward red (denoted as Cr)[@ITU-T-T-871].

Isolating the Cr component allows for a more effective search later on, since only potential circles that are actually red will be present in the data.

We do this by utilizing the OpenCV framework, it has a build in converter from RGB to YCrCb, which is a linear transformation, thereby having time and space complexity of $O(n)$, the function used is:[@opencv_image_transformation]

> $$ Y = 0.299 R + 0.587 G + 0.114 B $$
> $$ Cb = (B - Y) + delta $$
> $$ Cr = (R - Y) + delta $$
> $$ delta = \begin{cases}
        128 & \quad \text{for 8-bit images}\\
        32768 & \quad \text{for 16-bit images}\\
        0.5 & \quad \text{for floating-point images}
    \end{cases}
$$

#### Removing noise and apply the threshold
As shown in figure {@fig:step_circle_detection} after isolating the red channel, there still might be elements of red that are not quite red enough to realistically be a speed sign. Therefore a threshold is applied, meaning that every pixel is examined and if the value of said pixel is higher than a predefined threshold that pixel is assigned a value of 255, and if the pixels value is lower than the threshold it would be assigned a value of 0. Doing so allows for limiting the search algorithm to only process realistic candidates for speed sign and not every somewhat red circle. After this a blur is applied to the image, this is to remove any remaining noise and to ensure as smooth a circle as possible for the detector to look for.

#### Searching for circles
The last step is to detect the circle, for this, the OpenCV HoughCircle method is used, it will detect multiple circles in the picture and return the center points and radius of each one. One thing to note is that this will only allow for coordinates for a perfect circle, which means that if the camera had a small angle to the speed sign, then the result of HoughCircle would not be a perfect fit on the actually detected circle. More about that in section [@sec:elliCirc].

### HoughCircle
Here I will explain the actual algorithm used in OpenCV, how it uses CannyEdgeDetecting behind the scenes and so on. There will be some math here and some illustration to look at. I hope I'm able to go into somewhat a deep level, but I'm not sure how much material and information I can get from the OpenCV community.