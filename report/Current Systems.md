# Current Systems

In 2008 Marcin L. Eichner et. al. published a paper[^eichner08] which could recognize round speed limit signs. The paper seperated the recognition into two tasks, detection and then recognition. A camera was to be placed in the car, facing the road ahead. The algorithm would then extract the red channel from the image, and try to detect circles in this channel, which would identify a speed sign. After detecting the speed sign, the image would be cropped as to only contain the neccessary part of the image, namely the speed sign. This cropped image would then be fed to an Artificial Neural Network, to classify which speed sign it was.

The technology used in the paper was first used in the redesigned BMW 7-Series. Although only a sidenode to the car, this paper set the stepping stone for traffic-sign-recognition in cars. 

Since then such algorithms have been integrated in many cars. Manufacturers such as BMW, Ford, and Audi have integrated traffic sign recognition in their cars, with differing actions[^bmwRec] [^fordRec] [^audiRec]. The technology is used to notify the driver of the current speed limit, incase the driver missed the sign, or in some cars it can even adjust the speed of the car to the current speed limit in an adaptive-cruise-control matter.

[^eichner08]: http://breckon.eu/toby/publications/papers/eichner08speedlimit_a.pdf

[^fordRec]: https://www.ford.co.uk/shop/research/technology/driving-experience/traffic-sign-recognition-system

[^audiRec]: https://www.audi-mediacenter.com/en/technology-lexicon-7180/driver-assistance-systems-7184

[^bmwRec]: http://www.adelaidebmw.com.au/com/en/newvehicles/3series/sedan_active_hybrid/2011/showroom/safety/traffic_sign_recognition1.html
