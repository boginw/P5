## Machine Intelligence Algorithm Candidates
Three different reports on this subject were analyzed in order to create a foundation for choosing the right sequence of algorithms.
The following section accounts for the different algorithms described in the papers that is considered potential candidate algorithms.
Most papers take the same abstract activities as described in [@sec:MI_Overview] when recognizing a speed sign; detect if there actually is a red circle, isolate and normalize the digits, recognize the digits and thus the speed sign.

This section will describe the different methods in each activity, as the specific method is interchangeable with little to no alterations needed to the previous and following activity. This is due to the similarity of the input and output of each activity.

### Detecting a Red Circle
This activity takes an image as input - typically directly from the camera, without any alterations.
All papers analyzed by the group isolate the red circle in order to reduce the amount of data to process.
This can be done since all speed signs are defined by a red circle. When it comes to actually determining if and where the speed sign is, the approaches differ.

#### Template Matching and Viola-Jones Detection
The first two papers are quite similar during this activity, as they use Template Matching[@torresen_efficient_2004] and the Viola-Jones Detector method[@real_time_detection] respectively.
Both of these algorithms slide a "window" across the picture, where the algorithm only computes inside the window.
In terms of Template Matching, the window fits the size of the templates provided.
A template consists of the item to be recognized, i.e. the red circles.
Templates are size-specific, so it is necessary to provide red circle-templates in different sizes, in order to recognize roadsigns at different distances, as shown on [@fig:templates].

![Templates of different sizes, used for template matching. ](https://i.imgur.com/7HKscOf.png){#fig:templates}

The Viola-Jones Detector on the other hand, slides a window across the screen, wherein it tries to find some predefined Haar-features.
These Haar-features, as seen in [@fig:haar-features] are based on the level of lighting, and will use this to properly recognize the circle shape.

![Different Haar-features used in the Viola-Jones Detector](https://qph.fs.quoracdn.net/main-qimg-f14c8b76756db356a4f168d3a998a061){#fig:haar-features width=80%}

#### Random Sample Consensus
RANdom SAmple Consensus (RANSAC)[@integrated_speed_limit] is a method to filter out outliers in samples. It works by randomly selecting a subset of points from a given set, then attempting to fit it as a circle model, followed by comparing this model to the entire feature set. If the circle model fits a sufficient set of features from the feature set, the feature is evaluated as present.

### Isolate Speed Sign and Normalize Data
Before feeding anything to the neural network, there is a need for having a normalized input.
Normalizing an image containing a speed sign consists of both discarding irrelevant pixels, as well as data-abstracting the relevant pixels to match the expected input of the number-recognizing model, which will in turn determine what speed limit is printed on the sign seen in the initial image.

The papers all use a feed-forward neural network to recognize the digits.
They do however differentiate, in the amount of preparation that they do with the data, before supplying it as input.
This section will describe the method with the least amount of preparation, and then move on to describe the method with most preparation.

#### Grayscaling and Binary Representation
All papers convert the image from a full-color image, to grayscale and then to a binary (black or white) representation.
It is noteworthy that none of the papers does a direct conversion from full-color to binary, due to the fact that the grayscaling is done earlier in the process as a mean to isolate the red channel for locating red circles. 

#### Separating the Digits
Two papers separate the digits.
The first paper does not mention how they separate the digits [@torresen_efficient_2004].
The other paper describes how they use a vertical projection, in order to separate the digits [@real_time_detection].
Vertical projection, as seen in [@fig:vertical-projection] allows the algorithm to separate digits by minimas.

![Vertical projection of the number '55'](https://i.imgur.com/zZcWtEQ.png){#fig:vertical-projection}

#### Skeletal Structure
A single paper finds the skeleton of the digit[@real_time_detection] to check the digits for junctions, loops, and line ends, in order to try and enforce the number recognition.
The paper does not include a description of how this is done.

#### Cropping and Resizing the Image
After going through all the preparational activities, the papers crop the image around the digits, and resize the image to fit the size of the input vector for the neural network.

### Recognize the numbers
As previously mentioned, all papers use a feed-forward neural network.
They do, however, differentiate in the amount of pixels that they feed to their respective networks.
The distribution is as follows:

| Pixels                         | Input nodes  | Hidden nodes  | Output nodes |
| ------------------------------ |-------------| -----|----|
| 7x5[@torresen_efficient_2004]  | 35 | 35 | 6 |
| 6x12[@real_time_detection]     | 72 | 10 | 10 |
| 20x20[@integrated_speed_limit] | 400 | 30 | 12 |

Note: Number of input nodes are just the product of the image pixel height and width given. Number of output nodes are just the number of speedsigns.

Not much is mentioned about how the papers specifically implement the neural network.
One paper does not mention anything at all, another paper mentions OpenCV and Adaboost.

### Skipping Preparation {#sec:skip_prep}
A possible concern is whether preparation actually adds value.
Preparation makes sure that the neural network only receives the data representing the digits inside the speedsign.
The preparation also attempts to ensure that the data given is as normalized as possible, in order to reduce difference in lighting, glare, rotation and other environmental influences.
The goal of the preparation is to priotize what should be the focus of the neural network.

If, instead, the entire picture with the speedsign, road, and surrounding environment was given as an input, the neural network would have to be trained on all possible scenarios: speedsigns at close, medium, and long distance.
All these scenarios had to be placed in all possible environments: A sunny city environment, a rainy city environment and a sunny sahara, and a snowy sahara, etc.