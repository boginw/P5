# Artificial Neural Network

The final step in recognizing a speed sign is: recognizing the number in the sign. A neural network is proposed in order to 
solve this task. The reason for this is that the speed signs detected can have a lot of variation in their format
 e.g angle, perspective, and size; these parameters can be difficult to define and take into account if one were to detect
  the letters mathematically. 
In this section the concept of a neural network will be described, why we used it, and following that, how we adopted it to our problem.

## Why use it

A neural network is excellent at solving tasks that are complicated or having inconsistent, incomplete or imprecise data. Neural networks primarily shine in pattern recognition because of their ability to generalize and respond to rather unexpected input or patterns. A specific situation that could illustrate all these scenarios could be when trying to recognize handwritten digits. This task can be entirely inconsistent since every person writing a digit has their style of handwriting. Also, the positioning and angle of the digit can fluctuate a lot from person to person. These conditions make it a complicated task to create an algorithm that can reliably recognize the digits since one person could write their `9` almost identical with another persons `8`. So, in order to solve this task, we need something that can analyze handwritten digits and search for patterns that can help in determining the actual digits of unknown cases in an unsupervised learning manner. 

Analyzing and recognizing these patterns are what a properly configured neural network do quite well. 
It is often said that a neural network is a black box of magic since there is no way to deterministically know beforehand which patterns it would recognize or how it would derive at that result. 
However, this behavior is the strength of the neural network since it allows for finding patterns in data that even humans can struggle to recognize, thereby allowing to solve tasks that humans cannot formulate in plain old computer algorithms.
When a neural network is appropriately configured and efficiently trained, it is a very efficient way to solve tasks since the prediction part of a neural network is some simple linear algebra across a set of matrices. 
This showcases one of the strengths of using a neural network, which is that the prediction is very fast, and the heavy computational aspects exist in the training part of the network. 

An appropriately configured and efficiently trained network is not an easy task to create.
In configuring a network, there is a lot of trial and error.
Next, the training is a very computational demanding task.
Depending on the network and training data, it can take hours, weeks, months or even years to train a network correctly, and this is the biggest drawback of a neural network.

## What is an Artificial Neural Network  

An Artificial Neural Network is inspired by neurons in the brain.
It works by having a network of neurons structured into layers.
Each neuron can be connected to neurons in adjacent layers, where each connection is either receiving a signal or sending a signal.
In this report, all explanations will assume a fully connected network, which means that every neuron has a connection to all neurons in the adjacent layers.
An illustration of this can be seen in [@fig:simpleFullyConnectedNN].

![A example of a fully connected Neural Network](report/assets/pictures/nn/6.pdf){#fig:simpleFullyConnectedNN}

Another detail that should be noted in [@fig:simpleFullyConnectedNN] is the flow of information.
When illustrating the neural network, the flow of information will only go from left to right.
Due to this nature, information will never move in cycles, which is the definition of a feed-forward neural network.

### A Neuron

A neuron, also called a unit or node, is a mathematical function that accepts some input, calculates a weighted sum, adds a bias and then produces an output. 

The mathematical equation for the function that is used to calculate the weighted sum is:

> $$Y = \sum (weight * input) + bias$$

Before producing a final output, the weighted sum from the original input is sent to adjacent layers within the Artifical Neural Network to be processed. 
Finally a value between 0 and 1 is produced based on what type of activation function is used in the neural network. 
The activation function is described in a later subsection. 

### The layers

A neural network consists of multiple layers: input, output, and hidden layers.
The first layer is called the input layer.
Considering images as the input, a possible architecture will create an input layer where the number of neurons are the same as the number of pixels in the image.
Each neuron will then get a specific pixel that is passed to the adjacent layer.

The last layer is the output layer.
This layer represents the result of what the neural network computed, and is the probability of a given option.
If the neural network should determine if it is presented with a picture of a dog, a fish, or neither, the output layer will typically consist of three neurons: one for the dog, one for the fish, and one for none of the two animals.
Each of those neurons will hold a probability that the input is what the neuron describes.

Between the input layer and the output layer is the hidden layers.
The number of layers, and the number of neurons in these layers can be anywhere from zero to almost infinite.
When a number is passed to a neuron in a hidden layer, the neuron will compute it's own value, which can be passed on to the next layer.
Computation of the value will be done by the following function:

$$ \varphi ( a \omega + b) $$

Where $\varphi$ is the activation function, $a$ is the input, $\omega$ is the weight, and $b$ is the bias. (we need to insert the real symbols here)
Each neuron is typically connected to every neruon in the adjacent layers, which is why the output of the neuron is the sum of all inputs from the previous layer.
The activation function is a function that normalizes the input.
When the product of the input, weight, and bias can become greater than one, the neurons output should be between zero and one.
The activation function will do this, by mapping the input to a value specified by the function.
It can also be used as a threshold function that will only accepts values greater than some threshold.
The activation function is further described in later sections.

The weight is something that is related to a connection between two neurons.
The weight is also what is adjusted when training the neural network, in order to increase or decrease the importance of certain inputs.
Another way to alter the weight is through bias, which can be introduced with manual training.
When doing manual training, designers of the network try to manually understand and alter the behavior of the network's hidden layers.
If designers of the neural network can identify neurons in the network responsible for different features of the input, they can alter the importance of these features.
Consider the neural network that tried to identify if it was a cat or a dog on a picture.
Designers might identify neurons that looks for a tail or finns, and add extra importance to these, while decreasing the importance of neurons that looks for whether there is a clear sky in the picture, or not.

## Theory behind
This section will start to explain the theory behind the neural network, and it will try to go through in a 3Blue1Brown inspired manner.

#### Hidden Layer

### Activation Function
<!-- Here it will explain what an activation function is and how it relates to data streaming through the network. We will here introduce the sigmoid function. -->

Each neuron calculates a weighted sum when given an input.
Without an activation function to normalize these values, the value for each neuron could range from -infinity to infinity as each neuron does not know the bounds of the value. 
The purpose of an activation function is then to map resulting values in a neuron to a desired range, which is typically between 0 and 1. 
The type of activation function can differ between different implementations, and in every implementation it can differ between layers.
The most basic activation function is the step function, which simply return 1 if the value is above a certain threshold, and 0 otherwise.
In this project, the sigmoid function has been used due to it being the only activation function, apart from the step function, that was fully supported in the OpenCV library.

> $S(x) = \frac{1}{1 + e^{-x}}$

<!-- I asked our MI teacher Thomas to verify if the following claim is credible. The source is: https://www.learnopencv.com/understanding-activation-functions-in-deep-learning/ -->

One of the problems of the sigmoid activation function is when it is used in training.
The optimal way of using the sigmoid function involves using manual training, where the weights of the neurons are manually adjusted to give the best result.
When a training function like the backpropagation technique is used, the gradient of the sigmoid function gives problems.

<div id="fig:sigmoid">
![](report/assets/pictures/sigmoid-activation-function.png){#fig:sigmoidAF width=45%}

![](report/assets/pictures/sigmoid-derivative.png){#fig:sigmoidAF_derivative width=45%}

The sigmoid function and its derivative. Figures from [@ActivationFunctions].
</div>

As seen on [@fig:sigmoidAF_derivative], which is the derivative of the sigmoid function in [@fig:sigmoidAF], the derivative function will yield a low value whenever the sigmoid function is nearing it's maximum and minimum value.
This results in a vanishing gradient problem, due to backpropagation using the partial derivative of the error function to change the weight of the neuron.
When the value of the partial derivative is very small, becuase the sigmoid function yields a minimum or a maximum, the weight between neurons are also barely changed.
This results in barely no training taking place, as neurons reach this state.
The vanishing gradient problem is primarily present when using backpropagation, and a method of avoiding it, is by using the RProp technique[@RPROP, p. 578].


### The Entire Network
This section will explain how the entire flow of the network works with neurons in the layers and activation functions. This section is where we will introduce the fact that it is all just simple linear algebra, i.e. matrices and such.

### The Model
Explain what a `model` is regarding a neural network.

## Training <!-- WIP -->

<!-- This section will explain what it means to train a neural network and explain the Backpropagation function and use it to create an example. This section should also introduce the notion of training data and what to be aware of when creating a training data set. -->
In order to actually "learn" anything the neural network need to train on what it is suposed to recognize. Training consists of two steps: prediction and back-propagation. 
In the first step the neural network needs to make a prediction in order to see how well it can recognize the desired features or patterns. For doing this a set of training data is required, in the example case with images of dogs, fish, or neither,  the training set needs to consist of images of dogs, fish, or neither. Furhtermore the images needs to be labeled with what they actually contains, so that the neural network can se if it predicts correctly.
<!-- Maybe go more in detail on what a good training set is, and what can be done to improve it -  or maybe leave this to the discussion -->
Then the second step is to calculate how accurate the prediction was, and based on this make adjustments to the neural network based on this. This is called back-propagation and is done by comparing the result from the output layer to the label of the image seen in [@eq:accuracy]. 

$$
cost = 
 \begin{bmatrix}
   &  &  \\
   &  &  \\
   &  & 
 \end{bmatrix}
 too come
 $$ {#eq:accuracy}

The difference is found by calculating the sum-of-square errors see [@eq:squareError], this means that lower numbers mean higer prediction accuracy. Note that the sum-of-square errors are calculated for each output neuron  

$$ error = \sum(t - a)^2 $$ {#eq:squareError}

whare $t$ is the prediction and $a$ is the actual labeled value.


More to come here
<!-- image her like 3blue1brown - of prediction vector vs label vector -->
<!-- Then insert picture of squer error! -->

### Overfitting
<!-- Here we will explain the dangers of overfitting the model. Also, how to ensure that it does not happen. -->

The concept of overfitting in machine learning is that the model is really good at classifying data based on the training set, but when the model is then to classify based on the test examples, the model was not trained on, the model performs poorly and cannot classify the data correctly.
Overfitting basically means that the model is unable to generalize well. 
This means that whatever features the model was trained on, it would only correctly classify those since data that slightly deviate from the training set are not correctly classified. 

A possible way to reduce the effect of overfitting is to give the model more data to train on. 
This means that it will be able to learn more from the training set by, hopefully, adding more diversity. 
Another possible way is data augmentation, which is the act of slightly manipulation the data by changing values or by rotating or zooming in on an image, is another method that can be used to reduce overfitting. 
This is beneficial because you have data that is similar to your orginal data but with reasonable modified.

### Testing
<!-- Here we will explain how to test the model after training using a testing data set. It should explain what to be aware of when creating the dataset and also why it is a good idea to have.  -->

As shortly mentioned in the `Overfitting` subsection, the desired model is one that can generalize and not only work on the data set the model was trained on. 
The model should correctly classify new input as they are given to the neural network. 
This is why a general rule of thump is to only use a certain percentage to train the model and the rest to test the model to determine overfitting. This distribution could be 75% of the training set used to train the model and 25% used for testing the model. 

# Our Artificial Neural Network.

Explain how we configured our neural network and why we did as we did. Explain which activation function/ training function we used, explain how we handled overfitting and the data sets used. 