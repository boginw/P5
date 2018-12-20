## Overview of Other Designs {#sec:MI_Overview}
Instead of trying to invent a usable MI from scratch for this project, the group's first move was to do some research on other approaches to solving the problem regarding recognizing speed signs. The following is a highly abstracted summary of how the common MI-activities implemented by Torresen et al. [@torresen_efficient_2004], Li et al. [@evaluation_study], Eichner et al. [@integrated_speed_limit], and Martinović et al. [@real_time_detection].

![Flow chart for abstracted MI-activities](report/assets/pictures/MI_Chart.png){#fig:MI_Chart}

As can be seen in [@fig:MI_Chart], the common flow consists of seven different activities. A more in-depth description of the different tactics for select activities will be described in [@sec:].

##### Picture is Captured

The first activity is to capture a picture and store it in a resolution (and file format) fit for use, since this, as previously mentioned in [@sec:CamAnal], can affect overall performance.

##### Red Circle on Screen?

The next activity is to determine whether or not a red circle has been detected on the previously captured picture since this is a pretty good indication of a speed sign being present. If no circle is found, the picture is discarded, and the process returns to capturing a new picture, as to not waste time on processing a picture without a speed encoded sign.

##### Locate Bounding box Surrounding Circle

If a circle is determined to be in the picture, the next activity is to locate, and pass along, where inside the picture this circle can be found.

##### Resize and Reduce (to black/white)

Since a picture containing a speed encoded sign might have been captured with various distances between the camera and the sign, the number of pixels in the picture occupied by the sign may vary as well. The bounding box containing the sign is therefore cropped out of the original picture, stretched or squashed to a predetermined size, as well as converted into a binary black/ white color spectrum.

##### Normalizing

Having achieved a unified starting point of pixels, the next activity is to normalize the picture, before feeding it to a Neural Network (NN). Normalizing in this context is to be seen as conversion into some sort of data-abstraction the NN can handle.

##### Neural Network

After all activities of preparation have been completed, the data structure is ready to be given as input to the neural network.
The purpose of the neural network is for the numbers in the image to be recognized, such that a speed limit can be given as an output.

##### Return Speed Limit

The final activity is to return the recognized speed limit to the car system, letting it know what speed it should be adjusted to at the given moment. This is immediately followed by a new picture being captured, and the entire process starting over.
