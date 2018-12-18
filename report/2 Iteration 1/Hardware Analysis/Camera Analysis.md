### Camera Analysis

The group collected a set of webcams to test, in order to identify one (or more) that would work for this project. Detailed specifications will only be presented in regards to the camera model chosen by the group.

#### Hardware Specification
Chosen camera model:

| Microsoft LifeCam Studio        |             |
| ----------------- |:------------|
| Resolution        | up to 1080p  |
| Wide Angle Lens  | Yes         |
| Autofocus        | Yes |
| Focal length      | from 10 cm to infinite |
| Length            | 113 mm |
| Width             | 40 mm |

Other models:
* Logitech Skype Webcam (specific model unknown)
* Unspecified webcam module from disassembled Acer laptop

#### Hypothesis
As mention in [@sec:ev3HardwareSpec], the USB port available for interaction with external hardware is a USB 1.1-interface. It is therefore required that the camera will be able to communicate through that interface.
It is also required that the camera is supported by the OpenCV API as well as the leJOS API. Finally, it should be possible to grab frames with a custom resolution, to prevent an unnecessary heavy workload computing on frames grabbed in native 1080p resolution.

#### Methodology
Two tests were formulated and used for identifying usable webcams.
The first test was whether or not the camera would be accessible to the EV3 directly through the leJOS webcam API.
The second test was whether or not the webcam would be supported by the OpenCV webcam API (as we, at the time of testing, already knew that OpenCV would be an essential part of this project).

Both tests were conducted by a two-step process, firstly creating a demo program that grabs a frame (using the appropriate API) from the webcam and displaying it on the EV3 Monochrome LCD. Secondly, a demo program is created which grabs a frame (again, using the appropriate API) with a specific resolution, and streaming this frame over a web socket to confirm that the output indeed was the custom resolution.

#### Results
The unspecified webcam module from the Acer laptop was unable to interface with anything, and thereby deemed unusable for this project. 
The Logitech Skype Webcam was unable to interface with the EV3 but seemed to work successfully when tested on a regular laptop. The group assumed that the problem was with the USB 1.1-interface, and deemed it unusable for this project.

The Microsoft LifeCam Studio interfaced successfully with the EV3 and managed to produce the expected pictures on both the EV3 Monochrome LCD as well as on the web socket. 

#### Conclusion
Based on its successful results, the Microsoft LifeCam Studio was deemed usable for this project. 