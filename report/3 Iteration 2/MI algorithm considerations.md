## Machine intelligence algorithm candidates
Three different reports on this subject were analyzed in order to create a foundation for choosing the right sequence of algorithms.
The following section accounts for the different algorithms described in the papers that is considered potential candidate algorithms.
Most papers take the same abstract steps as described in section \ref{ssec:MI_Overview} when recognizing a speed sign: detect if there's actually a red circle, check if it's a speed sign, isolate and normalize the numbers, recognize the numbers and thus the speed sign.

This section will describe the different methods in each step, as the specific method is interchangeable with little to no alterations needed to the previous and following steps. This is due to the similarity of the input and output of each step.

### Detecting a red circle
This step takes an image as input - typically directly from the camera, without any alterations.
All reports isolate the red circle in order to reduce the amount of data to process.
This can be done since all speed signs are defined by a red circle. When it comes to actually determining if and where the speed sign is, the approaches differ.

This process should rather have false-positives (recognizing something as a speedsign when it is not a speedsign) than false-negatives, as the false-positives will be sorted in later sections.

#### Template Matching and Viola-Jones Detection
The first two methods are quite similar, as they use Template Matching[@torresen_efficient_2004] and the Viola-Jones Detector method[@real_time_detection].
Both of these algorithms slide a "window" across the picture, where the algorithm only computes inside the window.
In terms of Template Matching, the window fits the size of the templates provided.
A template consists of the item to be recognized, i.e. the red rings.
Templates are size-specific, so it is necessary to provide red rings in different sizes, in order to recognize roadsigns at different distances, as shown on [@fig:templates].

![Templates of different sizes, used for template matching. ](https://i.imgur.com/7HKscOf.png){#fig:templates}

The Viola-Jones Detector on the other hand, slides a window across the screen, wherein it tries to find some predefined Haar-features.
These Haar-features, as seen in [@fig:haar-features] are based on the level of lighting, and will use this to properly recognize the circle shape.

![Different Haar-features used in the Viola-Jones Detector](https://qph.fs.quoracdn.net/main-qimg-f14c8b76756db356a4f168d3a998a061){#fig:haar-features}

#### Random Sample Consensus
RANdom SAmple Consensus (RANSAC)[@integrated_speed_limit] is a method to filter out outliers in samples. It works by randomly selecting a subset of points from a given set, then attempting to fit it as a circle model, then this model is compared to the whole feature set. If the circle model fits a sufficient set of feature from the feature set, then the feature is evaluated as present.

Another, similar approach called Hough Circle Transform uses voting instead of fitting.
Each possible candidate is evaluated on how well it fits the given parameters, and then the best fit is chosen.
This approach is the OpenCV counterpart to the RANSAC algorithm.

### Isolate and normalize the numbers
In order to feed the numbers to the neural networks, there is a need for normalizing the image of the numbers.
When normalized, the numbers can be given as input to the number-recognizing algorithm, which will determine what speed limit is printed on the sign.

The papers all use a feed-forward neural network to recognize the numbers.
They do however differentiate, in the amount of preparation that they do with the numbers, before supplying them as input.
This section will thus begin by describing the least amount of preparation, and then move on to describe the method with most preparation.

#### Grayscaling and binary representation
All papers convert the image from a full-color image, to grayscale and then to a binary (black or white) representation.
It's important to note that all papers first convert to grayscale and then to a binary representation, albeit none of them provide reasoning for this.

#### Separating the numbers
Two papers separate the numbers[@torresen_efficient_2004][@real_time_detection].
The first paper does not mention, how they separate the numbers.
The other paper, however, describes how they use a vertical projection, in order to separate the numbers.
Vertical projection, as seen in [@fig:vertical-projection] allows the algorithm to separate numbers by minimas.

![Vertical projection of the number '55'](https://i.imgur.com/zZcWtEQ.png){#fig:vertical-projection}

#### Skeletal structure
A single paper finds the skeleton of the number[@real_time_detection].
The paper does not include a description of how they do it.
The paper needs it, as they check the numbers for junctions, loops, and line ends, in order to try and enforce the number recognition.

#### Cropping and resizing the image
After going through all the stages of preparation, the papers just crop the image around the numbers, and resize it to only be the size of their neural network.
One paper[@integrated_speed_limit] only use cropping and resizing as preparation before feeding the neural network, i.e. they feed both numbers to the neural network.

### Recognize the numbers
As previously mentioned, all papers use a feed-forward neural network.
They do, however, differentiate in the amount of pixels that they include in the network.
The distribution is as follows:

| Pixels                         | Input nodes  | Hidden nodes  | Output nodes |
| ------------------------------ |-------------| -----|----|
| 7x5[@torresen_efficient_2004]  | 35 | 35 | 6 |
| 6x12[@real_time_detection]     | 72 | 10 | 10 |
| 20x20[@integrated_speed_limit] | 400 | 30 | 12 |

Note: Number of input nodes are just the product of the image pixel height and width given. Number of output nodes are just the number of speedsigns plus a node for no sign.

Not much is mentioned about the algorithm itself, but the papers implement it differently.
One paper does not mention how they implement the algorithm.
Another paper mentions OpenCV and Adaboost.
And the last paper implemented the algorithm on their own.

### Skipping preparation {#sec:skip_prep}
A possible concern in the consideration between using preparation or not, is whether preparation actually adds value.
Neural networks act by analyzing the given input.
Preparation makes sure that the neural network only receives the numbers inside the speedsign.
The preparation also tries to ensure that the numbers given, albeit still pictures, are as normalized as possible, in order to reduce difference in lighting, glare and rotation.
This way, the neural network will only be focusing on the numbers that was given as input.

If, instead, the entire picture with the speedsign, road, and surrounding environment was given as an input, the neural network had to work with similarities inside this picture.
The neural network would not have any way of knowing what is has to look for, or even where to look.
In order to properley identify a speedsign, the network would have to be trained on all possible scenarios: speedsigns at close, medium, and long distance.
All these scenarios had to be placed in all possible environments: A sunny city environment, a rainy city environment and a sunny sahara, and a snowy sahara, etc.

By preparing the input image, the neural network is capable of focusing on the relevant data; the numbers inside the speed sign.
