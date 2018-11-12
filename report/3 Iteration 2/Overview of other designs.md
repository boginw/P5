## Overview of Other Designs

Instead of trying to invent a usable MI from scratch for this project, our first move was to do some research on how others have tackled the problem regarding recognizing speed encoded signs. The following is a conjoined (and highly abstracted) summary of how the typical "MI-work-flow" was implemented by Torresen et al. [@torresen_efficient_2004], Li et al. [@evaluation_study], Eichner et al. [@integrated_speed_limit], and Martinović et al. [@real_time_detection].

![Flow chart for abstracted MI-work-flow](report/assets/pictures/MI_Chart.png){#fig:MI_Chart}

As can be seen in +@fig:MI_Chart, the overall flow consists of seven more or less sequential steps. A more in-depth description of the different tactics for each step will be described at a later point in this report.

#### Picture is Captured

Starting from the left, the first step is to capture a picture. During this step, it has to be predetermined which format to use for the storage of the picture, since this, as previously mentioned in  affects 
