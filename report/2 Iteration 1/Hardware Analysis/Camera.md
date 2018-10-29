## Pixy CMUCAM5 Image Sensor Lego
This is a vision sensor which means it is a camera with a microcontroller onboard. The microcontroller uses a blob algorithm to detect objects based on color. The result of the microcontroller is then either communicated via SPI, I2C, UART, USB or analog/digital output interfaces. The output is simply a data object that contains the (x,y)-coordinates of the recognized object, the size of the boundary box surrounding the object, and which color label ie. which object it is.[^Pixy_Lego_wiki]

The Pixy CMUCAM5 allows for high-speed (50 fps) object recognition, but the output is not usable in the project, as the camera outputs the position of the object, but never the picture.
To determine the number on the speed sign, the original picture is needed in order to recognize the digits. Therefore, the Pixy CMUCAM5 is not suitable to solve the problem. 

### Alternative solutions
The Pixy CMUCAM5 was the only camera available to the project as standard video equipment for the Ev3 platform, so alternative equipment not natively compatible with the Ev3 had to be used.
The Ev3 has a USB 1.1 port and runs a Linux based system. This would allow for the use of an ordinary webcam, but the Ev3 does not support this natively.
In 2014 Gabriel Ferrer made the Ev3 support an ordinary webcam by configuring a Java Virtual Machine named leJOS[^Webcam_with_Lego_Mindstorms_EV3].
LeJOS later evolved to natively support an ordinary webcam alongside other relevant tools, such as OpenCV.[^lejos_091_release]

## leJos and webcam
Since leJos specifies that not all webcams are supported, the group collected a set of webcams to test them and locate one that would work. Two tests were performed. The first was whether or not the camera would be supported by the leJos webcam API. 
The second tested if the camera was supported by the OpenCV webcam API, as this library is needed in the project.

The first two cameras were unsuccessful, the first one was suspected to fail because of the camera being broken. The second one failed since the leJos API could not interface with it, which most likely were due to the fact, that the camera was a webcam combined with a microphone which might have interfered with the leJos API. But the third camera we tested was successful in both the leJos and OpenCV API's. 

After identifying a working camera some demo programs were written to get a sense of the capabilities of using a webcam on the EV3. And to test whether or not there were any potential performance benefits in choosing one API over the other. 

### Demo programs
The first demo program conducted was to have the EV3 grab frames from the camera and display on the Monochrome LCD on the EV3. In doing this activity the group realized that there are some important performance considerations to consider when choosing which image format the camera data should be converted to. The leJos community claimed that JPEG is very cost inefficient for the EV3, whereas YUYV very cost efficient for the EV3. The group tried both in this demo program, and there was a small barely noticeable difference in response time on the video feed in favor of YUYV. But this claims should be tested in more depth when working with the actual model for MI since the claims of the leJos community are that YUYV is preferable over everything else when it comes to Computer Vision.

The second demo program conducted was to have the EV3 live stream the video feed over HTTP via a Bluetooth connection. This was to see how converting the camera data to JPEG affected the system while using the Bluetooth protocol to communicate. The test showed a high latency, almost unusable when using the leJos API and JPEG. But switching to OpenCV API and utilizing their Mat-datatype for representing the image significantly decreased the latency, to a point that it became somewhat usable. Because of the noticeable differences between leJos API and OpenCV API, the group should conduct some experiments with OpenCV to recognize whether or not there is any performance to be gain by using that over leJos API.

Finally, the group did a quick and dirty test to count the FPS from using the leJos API to grab frames and nothing else. That yielded a result between 14-15 fps, which in theory should be enough. But that test should be revisited when the MI models complexity is accounted for and tested on both the leJos API and OpenCV API.


[^Webcam_with_Lego_Mindstorms_EV3]: Webcam with Lego Mindstorms EV3, part 1 (kernel): http://gjf2a.blogspot.com/2014/08/webcam-with-lego-mindstorms-ev3-part-1.html
[^lejos_091_release]: The long wait….:https://lejosnews.wordpress.com/2015/11/16/the-long-wait/
