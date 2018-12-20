# Overview Of The Project
Every essential part of the system is introduced at this point, and this chapter will show how the different parts operate together in the final system. The system itself is divided into two subsystems, the car system and the Speed Sign Recognition system (SSR system). This chapter will reflect that by explaining those subsystems individually and end with describing the communication between them.

## The Car System
The car is a relatively simple system. It consists of three components which each has their own specific responsibility. Firstly there is a component whose responsibility is to handle the communication with the SSR system. The internal communication between the components is dealt with by an observer pattern, meaning that the parts depending on the messages from the SSR system are added as observers to the communication component. In this project, the word `observer` and `listener` will be used interchangeably. How the communication component work is shown in [@sec:communication], the rest of this section will focus on the other two components of the car system.

### Motor Control Component
The motor control component has, as the name implies, the responsibility for controlling the motors. Since the leJOS operating system supplies all necessary boilerplate code to interact with the EV3 brick and the EV3 Motors, the only job of this component is to set the intended speed, which is defined as degrees per second. The maximum sustainable speed is 100 x the battery voltage; this means that depending on the battery voltage the car might not be able to reach its maximum speed. Because of that, the group decided to make the speed-levels relative to the maximum available speed, given the remaining voltage on the battery. Making the speeds relative is possible since leJOS supply an interface to get the maximum amount of degrees per second at any point in time. Taking a naive approach, and consider the car's maximum amount of degrees per second as equal to the highest speed-level by the SSR system.  In [@eq:calculateSpeed] it is shown how to set the speed relative to the amount of voltage on the battery, given the naive approach.

$$ Motor_{speed} = \Bigl\lceil \frac{Speed_{max} * Speed_{recognized} }{Speed_{recognitionMax}} \Bigr\rceil $${#eq:calculateSpeed}

Where $Speed_{max}$ is the maximum speed available given the voltage on the battery, $Speed_{recognized}$ is the value received from the SSR system and $Speed_{recognitionMax}$ is the maximum recognizable value of the SSR system.

The motor component is registered as a listener to the communication component and when it receives a message it will stop the motors, set the new speed using [@eq:calculateSpeed] and start the motors again.

### Screen Printer Component
The Screen printer component has the responsibility of showing the recognized speed, i.e., the speed that the car is adjusted too. The component simulates a display on the dashboard of a real car showing the driver what speed the car is driving at. This component is registered as a listener as well and displays the value received from the communication component.

The screen printer component also acts as a confirmation component to the group, in the way that it allows for the group to assert that the recognized value is the same as the value of the sign held up in front of the camera.

## Speed Sign Recognizer System
The SSR system is a more complicated system than the car system. It is divided into four main components of which some consists of sub-components. The section will go through the components in the same order as the system uses them; this is to both explain the flow of the data running through the system, but also to achieve a more intuitive understanding of how the different components interact.

The system is designed to support different modes, and this is to allow for different behaviors. At this point two modes are implemented, one for showing a live feed of the system in a browser (this mode is used for development and imperial testing), the other is for production, meaning this is the mode that is to be used in combination with the car system. The production mode does not create a live feed. Instead, it instantiates the communication component to send messages to the car system. The focus will be on the production mode from here on. Any behavior explained is in regards to the production mode.

After initializing the mode, one can start the entire system by calling the method `start` on the mode-object. This method takes three parameters which are a `VideoCapture` object, an `EllipseProccessor` object and an `Interface Function`, which is a Java object that works like a lambda expression. The `Interface Function` allows for passing a function as a parameter and then invoke it inside the method - we use this to pass along the `predict` method of the ANN.

When invoking the `start` method, the connection to the car is established. After a successful connection an infinite-loop starts which reads an image from the `video capture`, pass it to the `ellipse processor` and if that locates and abstracts a speed sign it is then sent to the `predict` method of the ANN. Lastly, the prediction from the ANN is then sent over the communication socket. Sending the message concludes the loop, and it will start over grabbing an image. 

### Video Capture
The OpenCV framework supplies this component, which allows for interacting with a video camera. It is configured to use the machine's default camera and to grab frames in a resolution of 320x240 pixels.  Invoking the `read`-method will retrieve a single frame from the camera, which then can be sent to the `ellipse processor`.

### Ellipse Processor
The Ellipse Processor component contains a lot of moving parts. The responsibility of this component is to detect a speed sign, crop to it, resize and convert the image to binary. In other words, the responsibility of the Ellipse Processor is to act as a pre-processor for the ANN and prepare the image for the network.

The Ellipse Processor works by receiving an image and copying it into two separate variables called `process` and `output`, respectively. The process variable allows for converting the process data to YCrCb and extracting the Cr channel. Afterwards, both images are passed through a `threshold`-method and the process image is blurred using a Gaussian blur. Now the process image is passed to the `circle detector` component, which returns a center point and radius of a circle, if one exists, otherwise it returns null. The process, output, and the center point of the circle is then passed to the `circle cropper` component, the result of which is the output image cropped to the detected circle. This is then resized to a square whose area is equal to the size of the input vector for the ANN before being returned.

#### Detect Circles Sub-Component
The detect circle sub-component is the Hough Circle Detection described in [@sec:searchingForCircles]. The implementation is supplied by the OpenCV framework and only needs to be configured. Configuring the Hough Circle Detection is not a simple task, since there are seven parameters, and each has an impact on how well the algorithm performs. The parameters used in this project were chosen by empirical testing.

#### Circle Cropper Sub-Component
This sub-component has the responsibility of finding the actual ellipse of the speed sign, the reasoning for this can be found in [@sec:ellipticalCropper]. The circle cropper sub-component uses the `process` image, which at this point in time should be a grayscale image with all red colored pixels having a value of 255, and all non red colored pixels having a value of 0.

The center point (provided by the `detect circle` sub-component) is given as a starting point for the circle cropper, and the component crawls in eight directions: left, up, right, down, top-left, bottom-left, top-right and bottom-right; one after another. It crawls in a direction until it encounters a pixel with a value different from 0, records the $(x,y)$ coordinate of that pixel, and starts the next direction. In other words, it starts from what is supposed to be the center and crawls outwards to determine reference points on the the red circle. The reference points are then used to determine the actual ellipse, as described in [@sec:ellipticalCropper]. In the implementation, the `fitEllipse` function provided by OpenCV is used. 

It then tries to convert the ellipse into a perfect circle, by the technique explained in [@sec:flattening]. The conversion is done to normalize the input to the network as much as possible. The group achieved the conversion by using a number of OpenCV-supplied functions such as `getRotationMatrix2D` and `warpAffine` to achieve the result shown in  [@fig:EllipseFlattening1] in [@sec:flattening]. `getPerspectiveTransform` and `warpPerspective` was used to achieve the result shown on [@fig:EllipseFlattening3] in the same section.  Afterwards, the component crawls the new ellipse to retrieve the new pixels defining the now flattened circle. All of this work is done both on the process and the output image, since the ellipse crawling mechanism needs the process image and the image cropper needs the output image. The final step is to crop the image to only contain the circle.

## Communications Between the Car and the SSR System {#sec:communication}
The communication between the car and the SSR System is achieved through a socket connection. The car will start a socket server and listen for a connection, and the SSR system will try to connect every 5 seconds. When the connection is successful a socket between the two systems is establish where every side has an output and input stream.
