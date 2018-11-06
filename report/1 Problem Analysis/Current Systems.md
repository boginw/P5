# Current Systems

In 2008 Marcin L. Eichner et. al. published a paper[@eichner_integrated_2008] which could recognize round speed limit signs. The paper seperated the recognition into two tasks: detection and then recognition. A camera was to be placed in the car facing the road ahead. The algorithm would then extract the red channel from the image and try to detect circles in this channel, which would identify a speed sign. After detecting the speed sign the image would be cropped as to only contain the necessary part of the image, namely the speed sign. This cropped image would then be fed to an Artificial Neural Network to classify which speed sign it was.

The technology used in the paper was first used in the redesigned BMW 7-Series. Although, only a sidenode to the car, this paper set the stepping stone for traffic-sign-recognition in cars. 

Since then such algorithms have been integrated in many cars. Manufacturers such as BMW, Ford, and Audi have integrated traffic sign recognition in their cars with disparate actions[@bmw_sign_recognition][@ford_sign_recognition][@audi_sign_recognition]. The technology is used to notify the driver of the current speed limit in case the driver missed the sign. In some cases cars even adjust the speed of the car to the current speed limit in an adaptive-cruise-control matter.
