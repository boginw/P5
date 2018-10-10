# Introduction

<!---
#### Formulating a Relevant Initial Problem Statement
Statistics from DEMA (Denmark's Emergency Management Agency) show that 1748 traffic accidents were responded to in 2017 [^DEMA_statistics]. Violation of speed regulations might be a contributing factor, which led to the following initial problem statement:
-->

> *What is the cost of speeding for the Danish society in terms of economic and social perspectives?*

[^AAU_Curriculum_SW5]: Aalborg University curriculum for Software 5 2018 (https://www.sict.aau.dk/digitalAssets/106/106274_software-bachelor.pdf)

[^Amazon_Go]: Amazon.com:: Amazon Go (https://www.amazon.com/b?ie=UTF8&node=16008589011)

[^DEMA_statistics]: DEMA (Denmark's Emergency Management Agency) on amount of 112-calls regarding traffic accidents (https://statistikbank.brs.dk/sb#page=e0048a3f-66c0-4ab9-b894-6d5f3de59ff0)

# Is Speed Really a Concern?
When it comes to traffic safety there are a lot of factors that affect every single accident. In 2017 1.756 people were seriously injured in a road accident, and 175 people lost their life in Denmark[^VD_Trafikulykker_for_året_2017].  
To better understand how speed affects traffic accidents, the Council for Safe Traffic composed some statistics based on data published by The Norwegian Public Roads Administration. These statistics show a correlation between speed and fatalities in accidents[^CFST_Statistics].

| Speed | Frontal Collision | Pedestrian Crossing |
| ----- | ----------------- | ------------------- |
| 80 km/h | In 3 out of 10 accidents someone dies | In 6 out of 10 accidents a pedestrian dies. |
| 90 km/h | In 6 out of 10 accidents someone dies | In 8 out of 10 accidents a pedestrian dies. |
| 100 km/h | In 8 out of 10 accidents someone dies | In 9 out of 10 accidents a pedestrian dies. |

Likewise, a report from The Danish Road Directorate[^DRD_fatal_accidents] shows that in 2010 52% of the accidents where the driver drove more than 20 km/h above the speed limit resulted in a fatal accident, and only 6% escaped without any injuries. In cases where the driver complies with the speed limit, 31% of accidents were fatal, and 37% were completely unharmed. Furthermore, the numbers reveal that the speeding limit was exceeded in 59% of all fatal accidents.
In Denmark the government funded institution, The Danish Road Traffic Accident Investigation Board (AIB), is responsible for analyzing the different accidents involving severe injury or death.  
<!---In a report from December 2014[^HVU_hvorfor_sker_ulykker] the AIB investigated the factors contributing to the cause and the severity of the accidents.  
In this report the AIB assesses how often high speed, amongst other factors, had a significant involvement in the incident happening, as well as how often high speed actually worsened the injuries sustained.  
On page 16 the AIB reports that out of 291 accidents high speed was a significant factor in 123 of them. This is more than 2 out of 5 incidents. Furthermore, it was found that in 30 of the 291 accidents, which is about 1 in 10 incidents, the high speed actually worsened the injuries sustained in the incident.
-->

![obeying the speed limit](pictures/obeying_speed_limit.png)
![Exceeding the speed limit](pictures/exceeding_speed_limit.png)
**These should be placed side by side. Caption: "Graphs showing injuries when you obey the speed limit compared to when you exceed it."**

<!--Looking at the broad numbers is not enough. The AIB, on page 18, recognizes the difference between exceeding the signed speed limit (being risk-willing) and driving faster than the conditions allow, possibly due to bad weather (being risk-blind).

Exceeding the speed limit is the predominant factor in the case of young drivers involved in solo accidents and for motorcyclists. For young drivers in solo accidents the number is about 65%, and for motorcyclists it is above 40%.

In a 2018 report by the AIB[^HVU_Risikovillig_kørsel] they deeply investigate 27 different road accidents where the driver showed risk-willing behavior.  
On page 15 they conclude that of these accidents half of them happened on a straight road, where the driver was exceeding the speed limit by 30 to 150 % when another driver misjudges the distance and the speed of the other vehicle and drives onto the road in front of the risk-willing driver.  
On a road with a limit of 50 km/h there was an average speed of 90 km/h while there on a road with a limit of 80 km/h was an average speed of 138 km/h.
The driver coming onto the road would need to look further than they normally do when orienting themselves, and would have a harder time spotting the other driver, as the car would be further away.  
The increased speed means the risk-willing driver has less time to recognize and react to the situation. This is coupled with a longer breaking distance, which results in the accident.

In another report  from 2011[^HVU_Grove_Hastighedsovertrædelser] the AIB investigates the reason why people decides to drive too fast. 99 drivers who exceeded the speed limit was interviewed, and out of these, 44 of the drivers mention being busy as the reason for exceeding the speed limit, i.e. having to pick up their kids from school or getting to work.  
32 drivers mention inattention as the reason why they drove too fast. Based on these 32 drivers only 8 of them were not aware that they actually exceeded the speed limit. The remaining 26 drivers was aware that they exceeded the speed limit, but not by how much.
-->

[^DRD_fatal_accidents]: The Danish Road Directorate - fatal accidents and speed - http://www.vejdirektoratet.dk/DA/viden_og_data/publikationer/Lists/Publikationer/Attachments/508/hastigheder%20ved%20d%C3%B8dsulykker.pdf

[^CFST_Statistics]: The Council For Safe Traffic - Statistic - https://www.sikkertrafik.dk/presse/statistik/adfaerd/fart

[^HVU_hvorfor_sker_ulykker]: The Danish Road Traffic Accident Investigation Board: Hvorfor sker trafikulykkerne? from http://www.hvu.dk/SiteCollectionDocuments/PDFx_HVUdec14_HvorforSkerUlykkerne.pdf

[^HVU_Risikovillig_kørsel]: The Danish Road Traffic Accident Investigation Board: Risikovillig kørsel from http://www.hvu.dk/SiteCollectionDocuments/Havarikommissionen2018_RisikovilligK%C3%B8rsel.pdf

[^HVU_Grove_Hastighedsovertrædelser]: The Danish Road Traffic Accident Investigation Board: Grove Hastighedsovertrædelser from: http://www.hvu.dk/SiteCollectionDocuments/HVUrapp08_Hastighed.pdf

[^VD_Trafikulykker_for_året_2017]: Vejdirektoratet: Trafikulykker for året 2017 from http://www.vejdirektoratet.dk/DA/viden_og_data/publikationer/Lists/Publikationer/Attachments/979/Trafikulykker%20for%20%C3%A5ret%202017%20-%20web.pdf


# Identifying Actors

<!-- This section concerns the identification and reasoning of actors that are interested in a solution that could reduce the risk of death or any other incapacitated state caused by car accidents.

In articles written by Vejdirektoratet [^vejdirektoratet] and Marketresearch[^marketresearch], two actors were identified. -->

5 levels of automation



Safety is highly regarded by car manufacturers, extensive testing is done in order to make sure that users of cars can feel as safe as possible while driving. Many car manufactures are working on automating the different aspects of driving, and to varying degrees. This amount of automation is called "the five levels of automation". [^sea_5_level]
Where level 0 describes a car with no automation, and level 5 describes a completely autonomous car. Many of the major car manufactures work with this scarle []



Safety is highly regarded by car manufacturers such as Tesla where extensive testing is done in order to make sure that users of their cars can feel as safe as possible while driving [^tesla_safety_measures]. This is also why Tesla is so diligent about their AutoPilot system, which provides full autonomous driving capabilities.

Safety is a very important topic for car manufacturers. Any kind of accident that leads to death, especially where families are involved, is most likely, as indicated in an article published by Fortune [^tesla_stock_price_decrease], to decrease the stock price and potentially the total amount of cars sold by the individual car manufacturer. This is one reason why Tesla recommends drivers always stay fully aware and not sleep while the Tesla AutoPilot system is on [^tesla_hands_on].

<!--Safty assisting technology is now mature enough to be used in commercial cars, without compromising safty. -->

[^vejdirektoratet]: http://www.vejdirektoratet.dk/DA/om-os/nyheder-og-presse/nyheder/Sider/F%C3%A6rre-dr%C3%A6bte-i-trafikken-i-2017---men-fortsat-for-mange.aspx

[^marketresearch]: https://blog.marketresearch.com/artificial-intelligence-in-cars-what-to-expect-from-2017-to-2021

[^tesla_hands_on]: https://electrek.co/2018/06/11/tesla-autopilot-update-nag-hands-wheel/

[^tesla_safety_measures]: https://www.tesla.com/da_DK/blog/update-last-week%E2%80%99s-accident?redirect=no

[^tesla_stock_price_decrease]: http://fortune.com/2018/03/28/tesla-stock-price-model-x-crash-credit-crunch/

[^sea_5_level]:
https://www.sae.org/binaries/content/assets/cm/content/news/press-releases/pathway-to-autonomy/automated_driving.pdf


Safety is highly regarded by car manufacturers, extensive testing is done in order to make sure that users of cars can feel as safe as possible while driving. Many car manufactures are working on automating the different aspects of driving, to make them both more safe, and convenient. This amount of automation is described in a taxonomy called "The Five Levels of Automation". [^sae_5_level]
Where level 0 describes a car with no automation, and level 5 describes a completely autonomous car. Many of the major car manufactures, like BMW [^bmw], Mercedes[^mercedes], and Hyundai [^hyundai], work with this scale.

An example of a part of such automation is described in 2008 Marcin L. Eichner et. al. published a paper[^eichner08] on how to recognize round speed limit signs with a neural network. The algorithm would use the red channel of an image to detect speed signs. Then crop the image to only contain the speed sign, and then feed it to a neural network, which will then classify the speed sign.
Since then such algorithms have been integrated in many cars. Manufacturers such as BMW, Ford, and Audi have integrated traffic sign recognition in their cars with disparate actions[^bmwRec] [^fordRec] [^audiRec]. The technology is used to notify the driver of the current speed limit in case the driver missed the sign. In some cases cars even adjust the speed of the car to the current speed limit in an adaptive-cruise-control matter.

Another example is Hyundai, they use a ultrasonic and radar sensors to cover blind spots in the car. The car will then alert the driver if other vehicles is approaching or has entered the blind spot. The system will also prevent the driver from leaving his/her lane it has detected a vehicle in the blind spot[^hyundai_blind_spot].

[^eichner08]: http://breckon.eu/toby/publications/papers/eichner08speedlimit_a.pdf

[^fordRec]: https://www.ford.co.uk/shop/research/technology/driving-experience/traffic-sign-recognition-system

[^audiRec]: https://www.audi-mediacenter.com/en/technology-lexicon-7180/driver-assistance-systems-7184

[^bmwRec]: http://www.adelaidebmw.com.au/com/en/newvehicles/3series/sedan_active_hybrid/2011/showroom/safety/traffic_sign_recognition1.html

[^vejdirektoratet]: http://www.vejdirektoratet.dk/DA/om-os/nyheder-og-presse/nyheder/Sider/F%C3%A6rre-dr%C3%A6bte-i-trafikken-i-2017---men-fortsat-for-mange.aspx

[^marketresearch]: https://blog.marketresearch.com/artificial-intelligence-in-cars-what-to-expect-from-2017-to-2021

[^sae_5_level]: https://www.sae.org/binaries/content/assets/cm/content/news/press-releases/pathway-to-autonomy/automated_driving.pdf

[^bmw]: https://www.bmw.com/en/automotive-life/autonomous-driving.html

[^mercedes]: https://www.mercedes-benz.com/en/mercedes-benz/next/automation/mapping-the-way-to-autonomous-driving/

[^hyundai]: https://www.hyundai.news/eu/technology/how-do-self-driving-cars-work/

[^hyundai_blind_spot]: https://www.hyundai.news/eu/technology/another-set-of-eyes-how-does-blind-spot-detection-work/
