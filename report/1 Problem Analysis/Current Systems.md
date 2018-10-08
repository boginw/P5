Safety is highly regarded by car manufacturers, extensive testing is done in order to make sure that users of cars can feel as safe as possible while driving. Many car manufactures are working on automating the different aspects of driving, to make them both more safe, and convenient. This amount of automation is described in a taxonomy called "The Five Levels of Automation". [^sae_5_level]
Where level 0 describes a car with no automation, and level 5 describes a completely autonomous car. Many of the major car manufactures, like BMW [^bmw], Mercedes[^mercedes], and Hyundai [^hyundai], work with this scale.

In 2008 Marcin L. Eichner et. al. published a paper[^eichner08] on how to recognize round speed limit signs with a neural network. The paper separated the recognition into two tasks: detection and then recognition. A camera was to be placed in the car facing the road ahead. The algorithm would then extract the red channel from the image and try to detect circles in this channel, which would identify a speed sign. After detecting the speed sign the image would be cropped as to only contain the necessary part of the image, namely the speed sign. This cropped image would then be fed to an Artificial Neural Network to classify which speed sign it was.

The technology used in the paper was first used in the redesigned BMW 7-Series. Although, only a sidenode to the car, this paper set the stepping stone for traffic-sign-recognition in cars. 

Since then such algorithms have been integrated in many cars. Manufacturers such as BMW, Ford, and Audi have integrated traffic sign recognition in their cars with disparate actions[^bmwRec] [^fordRec] [^audiRec]. The technology is used to notify the driver of the current speed limit in case the driver missed the sign. In some cases cars even adjust the speed of the car to the current speed limit in an adaptive-cruise-control matter.

[^eichner08]: http://breckon.eu/toby/publications/papers/eichner08speedlimit_a.pdf

[^fordRec]: https://www.ford.co.uk/shop/research/technology/driving-experience/traffic-sign-recognition-system

[^audiRec]: https://www.audi-mediacenter.com/en/technology-lexicon-7180/driver-assistance-systems-7184

[^bmwRec]: http://www.adelaidebmw.com.au/com/en/newvehicles/3series/sedan_active_hybrid/2011/showroom/safety/traffic_sign_recognition1.html

[^vejdirektoratet]: http://www.vejdirektoratet.dk/DA/om-os/nyheder-og-presse/nyheder/Sider/F%C3%A6rre-dr%C3%A6bte-i-trafikken-i-2017---men-fortsat-for-mange.aspx

[^marketresearch]: https://blog.marketresearch.com/artificial-intelligence-in-cars-what-to-expect-from-2017-to-2021

[^sae_5_level]:
https://www.sae.org/binaries/content/assets/cm/content/news/press-releases/pathway-to-autonomy/automated_driving.pdf

[^bmw]:
https://www.bmw.com/en/automotive-life/autonomous-driving.html

[^mercedes]:
https://www.mercedes-benz.com/en/mercedes-benz/next/automation/mapping-the-way-to-autonomous-driving/

[^hyundai]:
https://www.hyundai.news/eu/technology/how-do-self-driving-cars-work/
