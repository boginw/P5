## Detecting red circles
This section will explain how circles are detected in the system, it will try to justify why that approch where choosen, also it will try to highlight any issues or shortcommings of the approch. The section will also show some calculations on the complexity of the algorithms shown, this is to ensure that the implementation will be able to perform on the limited system.

### The overview
Here i will explain the "steps" in a high abstraction with a image that goes through the steps. This is the section that will highlight the justification for every step.

#### Isolating red color
Here i will explain that since a traffic speed sign always have a red circle, we would like to isolate everything red in the image, so that everything after this point dont use computation power on data we dont care about. Also this is here i will introduce the YCrCb color space and why it is nice to use, (proberly gonna look a HLS also, just to ensure that YCrCb is the best way).

#### Removing noise
Here i will explain why we want to remove the noise, what it is and how we remove it. This section will explain how we firstly thresholds the data and the afterwards GuassianBlur.

#### Seaching for circles
This section will explain how we search for circles super simple, it will not go into to much depth, since it will have a proper explaination in its own sub-section below, but it will just explain what the step does and what the result of this step is.

### HoughCircle
Here i will explain the actual algorithm used in openCV, how it uses CannyEdgeDetecting behind the scenes and so on. There will be some math here and some illustration to look at. I hope im able to go into somewhat a deep level, but im not sure how much materiale and information i can get from the openCV community.