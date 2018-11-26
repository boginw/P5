## Overview of Other Designs
\label{ssec:MI_Overview}
Instead of trying to invent a usable MI from scratch for this project, our first move was to do some research on how others have tackled the problem regarding recognizing speed encoded signs. The following is a highly abstracted summary of how the typical "MI-work-flow" was implemented by Torresen et al. [@torresen_efficient_2004], Li et al. [@evaluation_study], Eichner et al. [@integrated_speed_limit], and Martinović et al. [@real_time_detection].

![Flow chart for abstracted MI-work-flow](report/assets/pictures/MI_Chart.png){#fig:MI_Chart}

As can be seen in [@fig:MI_Chart], the overall flow consists of seven sequential steps. A more in-depth description of the different tactics for each step will be described at a later point in this report.

#### Picture is Captured

Starting from the left, the first step is to capture a picture. During this step, it has to be predetermined which format to use for the storage of the picture, since this, as previously mentioned in section \ref{ssec:DEMOPROG}, affects overall performance.

#### Red Cirle on Screen?

The next step is to determine whether or not a red circle has been detected on the previously captured picture, since this is a pretty good indication of a speed sign being present. If no circle is found the picture is discarded and the process returns to capturing a new picture, as to not waste time on processing a picture without a speed encoded sign.

#### Locate Bounding box Surrounding Circle

If a circle is determined to be in the picture, the next step is to locate, and pass along, where in the picture this circle is to be found. Once coordinates corresponding to a bounding box has been found, the next step commences.

#### Resize and Reduce (to black/white)

Since a picture containing a speed encoded sign might have been captured with various distances between the camera and the sign, the amount of pixels in the picture occupied by the sign may vary as well. The bounding box containing the sign is therefore cropped out of the original picture, stretched or squashed to a predetermined size, as well as converted into a binary black/ white color spectrum.

#### Normalizing

Having a uniform starting point of pixels, the next step is to normalize the picture, before feeding it to a Neural Network (NN). Normalizing in this context is to be seen as conversion into a some sort of data-abstraction the NN can handle.

#### Neural Network

After all stages of preparation have been applied, the data structure is ready to be given as input to the neural network, of which the implementation will be discussed in a later section.
The purpose of the neural network is for the numbers in the image to be determined, so a speed limit can be given as an output.

#### Return Speed Limit

The final step is to return the determined speed limit to the car-controller, letting it know what speed it should be adjusted to at the given moment. This is immediately followed by a new picture being captured, and the entire process starting over.
