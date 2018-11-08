## Pixy CMUCAM5 Image Sensor Lego
The Pixy CMUCAM5 Image Sensor is a vision sensor which means it is a camera with a microcontroller onboard. The microcontroller uses a blob algorithm to detect objects based on color. The result from the microcontroller is then either communicated via SPI, I2C, UART, USB, or analog/digital output interfaces. The output is the (x,y)-coordinates of the recognized object, the size of the boundary box surrounding the object, and which color label it is.[^Pixy_Lego_wiki]

The Pixy CMUCAM5 allows for high-speed (50 fps) object recognition, but the output is not usable in the project, as the camera outputs the position of the object, but never the picture.  
To determine the number on the speed sign, the original picture is needed in order to recognize the digits. Therefore, the Pixy CMUCAM5 is not suitable to solve the problem. 

### Alternative solutions
The Pixy CMUCAM5 was the only camera available to the project as standard video equipment for the EV3 platform, so alternative equipment not natively compatible with the EV3 had to be used.
The EV3 has a USB 1.1 port and runs a Linux based system. This would allow for the use of an ordinary webcam, but the EV3 does not support this natively.
In 2014 Gabriel Ferrer made the EV3 support an ordinary webcam by configuring an OS named leJOS with a Java Virtual Machine[^Webcam_with_Lego_Mindstorms_EV3].
LeJOS later evolved to natively support an ordinary webcam alongside other relevant tools, such as OpenCV.[^lejos_091_release]

## leJOS and webcam
Since leJOS specifies that not all webcams are supported, the group collected a set of webcams to test them and locate one that would work. Two tests were performed. The first was whether or not the leJOS webcam API would support the camera. 
The second tested if the camera was supported by the OpenCV webcam API, as this library might be needed.

In total, three cameras was tested.
Of these three, the first is under suspicion of being faulty.
The second camera was unable to interface with the leJOS API. This might be because the webcam also includes a microphone which interferes with the API.
The third camera was successful in both the leJOS and OpenCV API tests.

After identifying a working camera some demo programs were written to get a sense of the capabilities of using a webcam on the EV3. This test also concluded if there were any performance differences between the two APIs.

### Demo programs
The first demo program test was about having the EV3 grab frames from the camera and display on the Monochrome LCD on the EV3. Conducting this activity, the group realized that there are some important aspects of performance to consider when choosing which image format the camera data should be converted to. The leJOS community claims that JPEG is cost inefficient whereas YUYV is cost efficient for the EV3. Both JPEG and YUYV image formats were assessed in this demo program, and there was a not insignificant difference in response time on the video feed in favor of YUYV. However, these claims should be tested more in-depth when working with the actual model for MI since the claims from the leJOS community are that YUYV is preferable over everything else when it comes to Computer Vision.

The second demo program that was to have the EV3 live stream the video feed over HTTP via a bluetooth connection.
This was done to get an understanding of how converting the camera data to JPEG affected the system while using the bluetooth protocol to communicate.
The test resulted in a high latency, which is almost unusable when using the leJOS API together with JPEG. Switching to OpenCV API and utilizing their Mat-datatype for representing the image significantly decreased the latency to a point where it became somewhat usable.
Because of the noticeable differences between the leJOS API and the OpenCV API, the group should conduct some experiments with OpenCV to recognize whether or not there is any performance to be gained by using that over the leJOS API.

Finally, the group did a quick and dirty test to count the FPS using the leJOS API to grab frames. That yielded a result between 14-15 FPS, which should be enough. But that test should be revisited when the MI model's complexity are accounted for and tested on both the leJOS API and OpenCV API.


[^Webcam_with_Lego_Mindstorms_EV3]: Webcam with Lego Mindstorms EV3, part 1 (kernel): http://gjf2a.blogspot.com/2014/08/webcam-with-lego-mindstorms-ev3-part-1.html
[^lejos_091_release]: The long wait….:https://lejosnews.wordpress.com/2015/11/16/the-long-wait/
