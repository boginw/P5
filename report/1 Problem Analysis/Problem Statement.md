## Problem Statement

By investigating data from The Danish Road Directive it was found that there was a clear correlation between speed and severity in traffic accidents, and, as previously presented, a lot of car manufacturers are currently working on a variety of different technologies used for assisting drivers in driving more safely - a feat that we would like to participate in.

The goal of the project will not be to implement a fully interfaced system in an actual car, but rather to make a small-scale demo of some sorts. Seeing as this semester is about embedded systems, implementing some sort of autonomy in a small remote-controlled car would be a feasible task. Furthermore we have access to Lego Mindstorm EV3 through Aalborg University, which gives us an opportunity to produce a satisfactory product on relatively limited hardware, with the purpose of emulating the restrictions of an embedded system.

Following this initial problem analysis, we arrived at the following problem statement:

> *How would an adaptive-cruise-control-demo that uses intelligent processing be implemented/designed to communicate with an embedded system?*

As a solution to this problem, the expectation is to present a demonstrational prototype capable of the following:

* Driving in a straight line.
* Being able to manually adjust speed.
* Being able to intelligently adjust speed according to road signs.

Another subproblem that the project will handle, is the normalization of input data to the intelligent system.

> *How can input data be normalized so data that are as alike as possible is given to the intelligent system?*

Current solutions described in {@sec:currentSystems} treats the data by, e.g. cropping the input image to only consider the relevant speed sign on the image.