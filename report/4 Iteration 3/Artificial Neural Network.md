# Artificial Neural Network
<!--Firstly a small text explaining why we choose to use a neural network and what to expect from the chapter.-->
The final step in recognizing a speed sign is: recognizing the number in the sign. A neural network is proposed in order to 
solve this task. The reason for this is, that the speed signs detected can have a lot of variation in their format
 e.g angel, perspective, and size; these parameters can be difficult to define and take into account if one were to detect
  the letters mathematically. 
In this section the concept of a neural network will be described, following that, how we adopted it to our problem.
This will   

## What is a Neural Network  
This section will explain what a neural network is an abstraction. This section should allow for more in-depth sections later on.

## Why use it
This section will explain in which scenarios it is beneficial to use a neural network and compare those to our scenario.

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