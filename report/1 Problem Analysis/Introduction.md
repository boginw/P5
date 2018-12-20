# Introduction

When navigation on public roads, in (or amongst) motorized vehicles, it is not uncommon to encounter one or more signs campaigning for speeding awareness [@RFST_saenk_farten].
In this chapter, the aspects of speeding will be introduced to achieve a better understanding of how big of a factor speeding is, and to help determine whether or not this is a relevant point of attention.

## Is Speed a Concern?

Many factors affect every single traffic accident.
In 2017, 1.756 people were seriously injured in a road accident, and 175 people lost their life in Denmark[@hvid_trafikulykker_2018].
To better understand how speed affects traffic accidents, the Danish Council for Safe Traffic composed some statistics based on data published by The Norwegian Public Roads Administration.
The results show a correlation between speed and fatalities in accidents[@CFST_statistik].

| Speed     | Car Frontal-Colliding into Other Car     | Car Colliding into Crossing Pedestrian        |
| --------- | -------------------------------------    | --------------------------------------------- |
| 80 km/h   | In 3 out of 10 accidents someone dies    | In 6 out of 10 accidents a pedestrian dies.   |
| 90 km/h   | In 6 out of 10 accidents someone dies    | In 8 out of 10 accidents a pedestrian dies.   |
| 100 km/h  | In 8 out of 10 accidents someone dies    | In 9 out of 10 accidents a pedestrian dies.   |
: Correlation between traveling speed and collision-fatality {#tbl:SpeedVsFatality}

Likewise, a report from The Danish Road Directorate[@vejdirektoratet_temaanalyse:_2011] shows that 52% of the accidents in 2010, where the driver drove more than 20 km/h above the speed limit, resulted in a fatal accident, and only 6% escaped without any injuries.
In cases where the driver complied with the speed limit, 31% of accidents were fatal, and 37% were completely unharmed.
Furthermore, the numbers reveal that the speeding limit was exceeded in 59% of all fatal accidents[@vejdirektoratet_temaanalyse:_2011].

Having presented these numbers, it is easy to establish _speed_ as being a relevant factor in traffic accidents and fatality.
Based on this factor, the following initial problem statement is formed:

> __*What is done today to assist drivers in driving more safely, in order to reduce the amount and severity of traffic accidents?*__

## Current Systems
In this section, some of the current solutions car manufacturers use to increase traffic safety are identified.
These solutions will be presented since they solve problems derived from our initial problem using a technological approach.

Safety is highly regarded by car manufacturers, as extensive testing is done in order to make sure that users of cars can feel as safe as possible while driving[@inproceedings, p. 1], which in turn also affects the sales on the cars being tested [@inproceedings, p. 4].
Many car manufacturers are working on automating the different aspects of driving to make them more safe, and more convenient[@NCAP_safety].
This amount of automation is described in a taxonomy called "The Five Levels of Automation", where level 0 describes a car with no automation, and level 5 describes a fully autonomous car[@sae_international_automated_nodate].
Many of the major automobile manufacturers, like BMW[@bmw], Mercedes[@mercedes], and Hyundai[@hyundai], work with this scale.

A concrete example of such automation can be found at Hyundai.
They use ultrasonic- and radar sensors to cover blind spots around the car.
The car will then alert the driver if another vehicle is approaching, or has entered, the blind spot.
The system will also prevent the driver from either leaving their lane, or break, if it has detected a vehicle in the blind spot.
[@hyundai_blindspots]

In 2008 Marcin L. Eichner et al. published a paper on how to recognize round speed limit signs with a neural network[@eichner_integrated_2008].
The algorithm would use the red channel of an image to detect speed signs (since all speed signs had the limit printed within a red circle), then crop the image down to only contain the speed sign, and finally feed it to a neural network, which would then classify the speed sign.
Since then, such algorithms have been integrated into many cars.
Manufacturers such as BMW, Ford, and Audi have integrated traffic sign recognition in their cars with different actions[@bmw_speedsigns] [@ford_speedsigns] [@audi_speedsigns].
The technology is used to notify the driver of the current speed limit in case the driver missed the speed sign.
In some cases, cars even adjust the speed of the car to the current speed limit in an adaptive-cruise-control matter[@eichner_integrated_2008].
