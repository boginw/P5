# Artificial Neural Network
<!--Firstly a small text explaining why we choose to use a neural network and what to expect from the chapter.-->
The final step in recognizing a speed sign is: recognizing the number in the sign. A neural network is proposed in order to 
solve this task. The reason for this is, that the speed signs detected can have a lot of variation in their format
 e.g angel, perspective, and size; these parameters can be difficult to define and take into account if one were to detect
  the letters mathematically. 
In this section the concept of a neural network will be described, following that, how we adopted it to our problem.
This will   

## What is an Artificial Neural Network  
An Artificial Neural Network is inspired by how the neurons work in the brain.
It works by having a network of neurons structured into layers. Each neuron can be connected to neurons on adjacent layers, each connection either receiving a signal or sending a signal.
In this report, all explanations will assume a fully connected network, which means that every neuron has a connection to all neurons in the adjacent layers, an illustration of this can be seen on [@fig:simpleFullyConnectedNN].

![A example of a fully connected Neural Network](report/assets/pictures/nn/6.pdf){#fig:simpleFullyConnectedNN}

Another detail that should be noted in [@fig:simpleFullyConnectedNN] is the flow of information.
When illustrating the neural network, the flow of information will only go from left to right.
Due to this nature, information will never move in cycles, which is the definition of a feed-forward neural network.

The first layer is called the input layer.
Considering images as the input, a possible architecture will create an input layer where the number of neurons are the same as the number of pixels in the image.
Each neuron will then get a specific pixel that is passes to the adjacent layer.

As the last layer is the output layer.
This layer represents the result of what the neural network computed.
Usually the result is a probability of a given option.
If the neural network should determine if it is presented with a picture of a dog, a fish, or neither, the output layer will typically consist of three neurons: one for the dog, one for the fish, and one for none of the two animals.
Each of those neurons will hold a probability that the input is what the neuron describes.

Between the input layer and the output layer is the hidden layers.
The number of layers, and the number of neurons in these layers can be anywhere from zero to almost infinite.
When a number is passed to a neuron in a hidden layer, the neuron will compute it's own value, which can be passed on to the next layer.
Computation of the value will be done by the following function:

$$ Insert function here $$

Where $\varphi$ is the activation function, $i$ is the input, $w$ is the weight, and $B$ is the bias. (we need to insert the real symbols here)
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


## Why use it
A neural network is excellent at solving tasks that are complicated or having inconsistent, incomplete or imprecise data. Neural networks primarily shine in pattern recognition because of their ability to generalize and respond to rather unexpected input or patterns. A specific situation that could illustrate all these scenarios could be when trying to recognize handwritten digits. This task can be entirely inconsistent since every person writing a digit has their style of handwriting. Also, the positioning and angle of the digit can fluctuate a lot from person to person. These conditions make it a complicated task to create an algorithm that can reliably recognize the digits since one person could write their `9` almost identical with another persons `8`. So, in order to solve this task, we need something that can analyze handwritten digits and search for patterns that can help in determining the actual digits of unknown cases in an unsupervised learning manner. 

Analyzing and recognizing these patterns are what a properly configured neural network do quite well. It is often said that a neural network is a black box of magic since there is no way too deterministically know beforehand which patterns it would recognize or how it would derive at that result. However, this behavior is the strength of the neural network since it allows for finding patterns in data that even humans can struggle to recognize thereby allowing to solve tasks that humans cannot formulate in plain old computer algorithms. When a neural network is appropriately configured and efficiently trained, it will be a very efficient way to solve tasks since the prediction part of a neural network is some simple linear algebra agros a set of matrices. This showcases one of the strengths of using a neural network, which is that the prediction is very fast, and the heavy computational aspects exist in the training part of the network. 

An appropriately configured and efficiently trained network is not an easy task to create. In configuring a network, there is a lot of trial and error. Next, the training is a very computational demanding task. Depending on the network and training data, it can take hours, weeks, months or even years to train a network correctly, and this is the biggest drawback of a neural network.

## Theory behind
This section will start to explain the theory behind the neural network, and it will try to go through in a 3Blue1Brown inspired manner.

### A Neuron
Here we will explain what a neuron is.

### The layers
<!--Here we will explain what a layer is, and explain the types of layers, ie. Input, Hidden and Output layers also, that they consist of neurons.-->
An artificial neural network typically consists 3 layer types: Input layer; Hidden layer; and the Output layer. All of the layers consists of neurons and usually vary in size.

* Input Layer is where the neural network receives its input. The number of neurons in the input layer has to match the number of features in the input, if the input is an image of e.g 20x20 pixels then the size of the input layers would have to be 400 neurons.
* Hidden Layer is where the  

#### Hidden Layer


### Activation function
Here it will explain what an activation function is and how it relates to data streaming through the network. We will here introduce the sigmoid function.

### The entire network
This section will explain how the entire flow of the network works with neurons in the layers and activation functions. This section is where we will introduce the fact that it is all just simple linear algebra, i.e. matrices and such.

### The model
Explain what a `model` is regarding a neural network.

## Training
This section will explain what it means to train a neural network and explain the Backpropagation function and use it to create an example. This section should also introduce the notion of training data and what to be aware of when creating a training data set.

### Overfitting
Here we will explain the dangers of overfitting the model. Also, how to ensure that it does not happen.

### Testing
Here we will explain how to test the model after training using a testing data set. It should explain what to be aware of when creating the dataset and also why it is a good idea to have.

# Our Artificial Neural Network.
Explain how we configured our neural network and why we did as we did. Explain which activation function/ training function we used, explain how we handled overfitting and the data sets used. 