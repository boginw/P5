# Machine intelligence algorithm candidates
Three different reports on this subject was analyzed in order to create a foundation for choosing the right sequence of algorithms.
The following section accounts for the different algorithms described in the papers that is considered potential candidate algorithms.
Most papers take the same abstract steps in recognition of a speed sign: detect if there's actually a red circle, check if it's a speed sign, isolate and normalize the numbers, recognize the numbers and thus the speed sign.

This section will describe the different methods in each step, as the specific method is interchangeable with little to no alterations needed to the previous and following steps. This is due to the similarity of the input and output of each step.

## Detecting a red circle
This step takes an image as input - typically directly from the camera, without any alterations.
All reports isolate 