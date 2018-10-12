## Pixy CMUCAM5 Image Sensor Lego
Is a vision as a sensor, which means it is a camera with a microcontroller onboard. The microcontroller then uses a blob algorithm to detect objects based of colors. The result of the microcontroller is then either communicated via SPI, I2C, UART, USB or analog/digital output interfaces. The output is simply a data object that contains the (x,y)-coordinates of the recognized object, the size of the boundary box sorrounding the object, and which color label ie. which object it is.[^Pixy_Lego_wiki]

This means that even tho the Pixy CMUCAM5 has a lot of capabilities that would be benificial for the group, such as 50 fps object recognition, we can not use it in our project. Due to the fact that we actually need to recognize the 'value' of a speed sign, and since there is no color coded differences between the speed signs, couple with the fact that the Pixy dont actually transmit any pixel data it would be impossible to detect the speed signs using the Pixy camera. 

### Alternative solutions
Since the Pixy CMUCAM5 was the only vision sensor/ camera that the university could supply for the Ev3 platform, the group started to look for alternative solutions. It quickly became clear to the group that since the Ev3 is a linux based system, and it has a usb 1.1 port it proberly would be possible to use a ordinary webcam, with a little tinkering. It turns out that this in fact was the case, Gabriel Ferrer a Professor of Computer Science at Hendrix College in Conway, Arkansas showcased a elegant solution for this on his blog back in 2014[^Webcam_with_Lego_Mindstorms_EV3]. Ferrer did it using leJos, which is a tiny Java Virtual Machine that runs on the Ev3 platform. Ferrer was able to connect a webcam to the Ev3 by carefully configure the leJos and writing a driver for the camera which allowed for the Java code to interface with it. But since leJOS EV3 0.9.1 release from 2015[^lejos_091_release] the ablility to interface with webcams are natively support in the platform, along side another potential usefull tool which is OpenCV.

## leJos and webcam


[^Webcam_with_Lego_Mindstorms_EV3]: Webcam with Lego Mindstorms EV3, part 1 (kernel): http://gjf2a.blogspot.com/2014/08/webcam-with-lego-mindstorms-ev3-part-1.html
[^lejos_091_release]: The long wait….:https://lejosnews.wordpress.com/2015/11/16/the-long-wait/
