# Artificial Neural Network
The final step in recognizing a speed sign is recognizing the number printed on the sign. A neural network is proposed in order to solve this task.
The reason for this is that, even after our normalization steps used to minimize variation in angle, perspective and size of the detected sign, there might still be some variation in the normalized input, as shown below in [@fig:webcam_demo].

![normalizing-demo with the normalized version of each picture shown in its corner](report/assets/pictures/webcam_demo.png){#fig:webcam_demo width=80%}

Unless otherwise specified, it is to be assumed that all knowledge has been aquired through the Machine Intelligence course, which was attended during the alongside creating this project.

## Why use Neural Network
A neural network is excellent at solving tasks that are complicated or having inconsistent, incomplete or imprecise data.
Neural networks primarily shine in pattern recognition because of their ability to generalize and respond to rather unexpected input or patterns.
<!-- A specific situation that could illustrate all these scenarios could be when trying to recognize handwritten digits. -->
As an example, recognizing handwritten digits is a feat previously used to demonstrate the capabilities of a neural network by many different groups/individuals, such as with the MNIST database.
The data provided for this task can be inconsistent since every person writing a digit has their own style of handwriting, just like our normalized pictures of speed signs can have some variation as well.
In order to solve our number recognizing task, the group will need a technique capable of approximating an answer based on inconsistent data-input and previous experince. Solving this type of task through analyzing and recognizing patterns is what a neural network does quite well.

It is often said that a neural network is a black box of magic since there is no way to deterministically know beforehand which patterns it would recognize, or how it would derive at any result.
However, this behavior is the strength of the neural network since it allows for finding patterns in data that even humans can struggle to recognize, thereby allowing to solve tasks that humans cannot formulate in plain old computer algorithms.
When a neural network is appropriately configured and efficiently trained, it is an efficient way to solve tasks, since the prediction-part of a neural network is linear algebra.

In configuring a network, there is a lot of trial and error, and training is a very computational demanding task.
Depending on the network and training data, it can take hours, weeks, months or even years to train a network sufficiently, which is one of the bigger drawbacks of a neural network.

## What is an Artificial Neural Network  
An Artificial Neural Network (ANN) is inspired by neurons in the brain.
The ANN works by having a network of artificial neurons structured into layers.
Each neuron can be connected to neurons in adjacent layers, where each connection is either receiving or sending a signal.
In this report, all explanations will assume a fully connected network, which means that every neuron has a connection to all neurons in the adjacent layers.
An illustration of this can be seen in [@fig:simpleFullyConnectedNN].

![A example of a fully connected Neural Network](report/assets/pictures/nn/6.pdf){#fig:simpleFullyConnectedNN}

Another detail that should be noted in [@fig:simpleFullyConnectedNN] is the flow of information.
When illustrating the neural network, the flow of information will only move from left to right.
Due to this nature, information will never move in cycles, which makes it a feed-forward neural network.

### A Neuron {#sec:neuron}

A neuron, also called a unit or node, is a mathematical function.
The neuron is the smallest possible entity of the neural network.
Each neuron is connected to all neurons in the adjacent layers, where weights define the relationship between these two.
The equation for calculating the value of a neuron is described in {@eq:neuronCalc}.

$$a = \displaystyle\sum_{j = 0}^{N_{L-1}} (weight_j^{(L)} * a_j^{(L-1)} + bias)$$ {#eq:neuronCalc}

$N_{L-1}$ is the number of neurons in the previous layer.
The weight is a multiplier between two neurons that describe the relevance of a neuron's influence on the neuron that is considered.
Neurons has a weight for each neuron in adjacent layers, which is altered by the training function.
The bias is a unique offset for every neuron, that the training function is also capable of altering.
The last parameter, $a^{(L-1)}$ is the output of the neuron in the previous layer.

### The layers
A neural network consists of multiple layers: input (always the first layer), output (always the last layer), and an arbitrary amount of hidden layers (between first and last).
Considering images as the input, a possible architecture will create an input layer where the number of neurons in the first layer is the same as the number of pixels in the image. 
Each neuron will then get a specific pixel that is passed to the adjacent layer.

This output layer represents the result of what the neural network computed, and can be converted into a probability of the approximated result.
If the neural network should determine if it is presented with a picture of a dog or a fish, the output layer will consist of two neurons: one for the dog and one for the fish.
Each of those neurons will a result, which in turn can be converted into the probability that the input is what the neuron represents.

Between the input layer and the output layer is the hidden layers.
The number of layers, and the number of neurons in these layers can be anywhere from zero to almost infinite.
When a number is passed to a neuron in a hidden layer, the neuron will compute an output value, which can be passed on to the next layer.
Computation of the value is described in [@sec:neuron].

The value of the neuron is passed through an activation function that normalizes the output.
A typical application is for the activation function to map the output value to some value between 0 and 1.
The activation function can either use a simple threshold, a linear function, or a more sophisticated approach to map the value of the neuron inside the 0-1 range.
The activation function is further described in later sections.

<!-- 
The weight is something that is related to a connection between two neurons.
The weight is also what is adjusted when training the neural network, in order to increase or decrease the importance of certain inputs.
Another way to alter the weight is through bias, which is a multiplier that is applied directly to a neuron.  -->
<!-- 
Manually altering the weights and biasses can happen through manual training, where each weight or bias are assigned a fixed value. -->

When doing manual training, designers of the network try to manually understand and alter the behavior of the network's hidden layers.
If designers of the neural network can identify neurons in the network responsible for different features of the input, they can alter the importance of these features in respect to the output.
Consider the neural network that tried to identify if it was a fish or a dog on a picture.
Designers might identify neurons that look for a tail or for finns, and add extra importance to these, while decreasing the importance of neurons looking for whether or not there is a clear sky in the picture.

## Theory behind
Neural networks are practically a collection of matrices on which linear algebra operations are performed.
The following section will explain some of the mathematics involved in the different methods considered in this project, as well as outlining the benefits and drawbacks of the different techniques.

### Activation Function {#sec:activation_function}
Each neuron calculates a weighted sum when given an input.
Without an activation function to normalize these values, the value for each neuron could range from -infinity to infinity as each neuron does not know the bounds of the value.
The purpose of an activation function is then to map resulting values in a neuron to a desired range, which is typically between 0 and 1.
The type of activation function can differ between different implementations, and in every implementation it can theoretically differ between layers.
The most basic activation function is the step function, which simply return 1 if the value is above a certain threshold, and 0 otherwise. Another activation function, which is quite popular, is the Sigmoid activation funcion ([@eq:sigmundurvisavnerdig]) 

$$S(x) = \frac{1}{1 + e^{-x}}$$ {#eq:sigmundurvisavnerdig}

<!-- I asked our MI teacher Thomas to verify if the following claim is credible. The source is: https://www.learnopencv.com/understanding-activation-functions-in-deep-learning/ -->

One of the problems of the sigmoid activation function is when it is used in training.
The optimal way of using the sigmoid function involves using manual training, where the weights of the neurons are manually adjusted to give the best result, but when a training function like the backpropagation technique is used, the gradient of the sigmoid function leads to problems.

<div id="fig:sigmoid">
![](report/assets/pictures/sigmoid-activation-function.png){#fig:sigmoidAF width=45%}

![](report/assets/pictures/sigmoid-derivative.png){#fig:sigmoidAF_derivative width=45%}

The sigmoid function and its derivative. Figures from [@ActivationFunctions].
</div>

As seen on [@fig:sigmoidAF_derivative], which is the derivative of the sigmoid function in [@fig:sigmoidAF], the gradient will yield a low value whenever the sigmoid function is nearing its maximum and minimum value.
This results in a vanishing gradient problem, due to backpropagation using the partial derivative of the error function to change the weight of the neuron.
When the value of the partial derivative is very small, because the sigmoid function yields a minimum or a maximum, the weight between neurons are also barely changed.
This results in barely no training taking place, as neurons reach this state.
The vanishing gradient problem is primarily present when using backpropagation, as it is dependent on the gradient to be low when the system is successful, and high when it is not.
Changing the activation function or the learning method is a way to mitigate the problem[@deepLearningBook].

### The Model
If the network is considered to be a data structure, then the model would be the actual values populating the data structure.
The model is generated by populating the network with different model-permutations, tweaking the values for an arbitrary amount of time, and then storing all values for safe-keeping.
A concern in the case of models, is not whether they are correct, but rather if they are generalized.
Generalization is shown as the model's success of predicting previously unseen objects in the world around it; a high success, and thus a high accuracy, means that the model is useful.

### Training
In order to actually "learn" how to recognize anything, the neural network needs to train on what it is supposed to recognize.
There exists numerous different methods for doing the training, but the two that were considered in this report was the Backpropagation and the Simulated Annealing, due to the availability of these methods in OpenCV.
These methods are described in the following section, and the potential advantages and drawbacks according to the problem domain be discussed.

#### Backpropagation {#sec:backpropagation}
Training consists of two phases: prediction and backpropagation.
This section will outline the steps needed for prediction and backpropagation, followed by the mathematical notations needed for understanding the method.
The following section is based on the work of [@machineIntelligence;@michaelNeuralNet;@calculusComputation;@3b1b].

##### Conceptual steps
When training-data is given to the network, it will make a prediction of what is seen in the image. This prediction will be a probabilistic vector containing the options from the output layer.

At first, the prediction might seem random, and quite possibly a long way off the correct result.
Each image in the training-data is combined with a label, which represents what output-node it belongs to.

After the prediction, it is time to do the backpropagation.
Backpropagation calculates how far off the prediction is from the optimal result, and adjusts the weights, biases, thereby indirectly the neurons, in the neural network, afterwhich the cycle starts over with a new prediction on the next training image.

The difference between the output vector and the target vector is known as the cost, and the overall goal of training is to decrease the overall cost of the network.
The cost is calculated as the differences between the prediction- and the target vector, squared.
Decreasing the cost is obtained by nudging the weights, biases, and neurons that influence the output layer.
Determining which weights, biases, or neurons to nudge is a matter of determining their influence on the final output vector.
If a neuron is very far away from its predicted value, the connections affecting it will be heavily modified.
When the opposite holds true, they will only be slightly modified.

Changing the weight or the bias is straightforward on the output-nodes, as the backpropagation algorithm is directly allowed to do so.
Changing the neurons in preceding layers, however, takes an extra step.
Because backpropagation cannot alter the value of neurons directly, it will have to affect it the same way as it alters the value of the neurons in the output layer - by changing _its_ weights, biases, and neurons.
This way, the backpropagation algorithm becomes a recurrent function that will consider each preceding neuron to change it, according to what output is expected.

The backpropagation algorithm wants to change the neurons that have the biggest impact on the output value.
As an example, if the output of one preceding neuron is $0.10$ compared to $4.20$ of another, changing the weight for the first neuron will impact the output value by $42$ times less, as if the same change in weight was applied to the second neuron.

RProp is a variation of backpropagation, where the factor that the weights are altered with is increased or decreased depending on the movement of the cost.

##### Mathematical notation
Consider an example of a 4-layer neural network, where each layer consists of a single neuron, as depicted in [@fig:network].
The last neuron, the only one in the output layer, will be named $a^{(L)}$ and $a^{(L-1)}$ is the neuron in the layer preceding it.
Taking an example of training, the $a^{(L)}$ layer outputs the value $0.66$ as a prediction of the result.
However, the target, $y$ is $1.00$.
How should the network be trained?

![The simple network considered in this section.](report/assets/pictures/nn/Figure4.4.pdf){#fig:network}

First of all, the cost of this training instance is calculated by the formula for sum of square errors, as seen in [@eq:squareError].

$$ C_0 = (a^{(L)} - y)^2 $$ {#eq:squareError}

For the example given, this would be $(0.66 - 1)^2 = 0.12$.
This tells that the prediction by $a^{(L)}$ is obviously off, and the network needs to be changed.

Thinking back to the conceptual walkthrough of the backpropagation algorithm, the prediction of the network can be changed by changing either the weights, the biasses, (and thereby the neurons).
Looking at how $a^{(L)}$ is calculated in [@eq:outputCalc], it can be determined what to change.

$$ a^{(L)} = \sigma(w^{(L)}a^{(L-1)}+b^{(L)}) \iff a^{(L)} = \sigma(z^{(L)}) $$ {#eq:outputCalc}

Where $w^{(L)}$ is the weight from $a^{(L-1)}$ to $a^{(L)}$, $b^{(L)}$ is the bias for $a^{(L)}$, and $\sigma$ is the sigmoid activation function applied to the value of $a^{(L)}$.
For ease of future referencing, the value of $a^{(L)}$ (everything without the sigmoid function) is notated as $z^{(L)}$.

![The direct and indirect influence of different parameters on the cost.](report/assets/pictures/nn/Figure4.5.pdf){#fig:effectOnOutput}

[@Eq:outputCalc] shows the equation describing $a^{(L)}$, where it is seen that both the weight, bias, and previous neuron have an impact on the output.
As is also described in the conceptual walkthrough, changing the values of these three parameters will change the value of the output.
Looking at [@fig:effectOnOutput] it is seen that changes to the weight $w^{(L)}$ directly affects the value of $z^{(L)}$.
This can be described as seen in [@eq:deltaW].

$$\frac{\partial z^{(L)}}{\partial w^{(L)}}$$ {#eq:deltaW}

Furthermore, changes to $z^{(L)}$ will directly affect $a^{(L)}$, which is described in [@eq:deltaA].

$$\frac{\partial a^{(L)}}{\partial z^{(L)}} $$ {#eq:deltaA}

Lastly changes to $a^{(L)}$ will affect $C_0$, as shown in [@eq:deltaZ].

$$\frac{\partial C_0}{\partial a^{(L)}}$$ {#eq:deltaZ}

The three formulas in [@eq:deltaW], [@eq:deltaA], and [@eq:deltaZ] shows a relation between changes in $w^{(L)}$ and changes to $C_0$.
The correlation is shown in [@eq:chainRule].

$$ \frac{\partial C_0}{\partial w^{(L)}} = \frac{\partial z^{(L)}}{\partial w^{(L)}} \frac{\partial a^{(L)}}{\partial z^{(L)}} \frac{\partial C_0}{\partial a^{(L)}} $$ {#eq:chainRule}

[@Eq:chainRule] is also called the chain rule and holds true for both the bias and the weight, but it also hold true for $a^{(L-1)}$, where $a^{(L-1)}$ itself will be dependent on $w^{(L-1)}$, $b^{(L-1)}$, and $a^{(L-2)}$.
In order to change the weight of all the parameters influencing the cost, it is necessary to calculate the derivative, as shown in the chain rule.
From [@eq:squareError], the definition for $C_0$ is given, where the derivative is shown in [@eq:cDerivative].

$$ \frac{\partial C_0}{\partial a^{(L)}} = 2(a^{(L)}-y) $$ {#eq:cDerivative}

And from [@eq:outputCalc], the derivate of $a^{(L)}$ is given in [@eq:aDerivative].

$$ \frac{\partial a^{(L)}}{\partial z^{(L)}} = \sigma'(z^{(L)})  $$ {#eq:aDerivative}

And lastly, as well from [@eq:outputCalc], the derivative of $z^{(L)}$ is given in [@eq:zDerivative].

$$ \frac{\partial z^{(L)}}{\partial w^{(L)}} = a^{(L-1)} $$ {#eq:zDerivative}

In [@eq:zDerivative] it is clear that when changing the weight of $w^{(L)}$ it is dependent on the neuron from the previous layer, $a^{(L-1)}$.
A downside of the weight being dependent on the activation function is that weights with low outputs from the activation function only will experience small changes in the weight, and in cases where the weight needs to be changed a lot, it needs the activation function to provide a bigger value first.
This leads to the saturation problem described in the [@sec:activation_function].

The chain rule is described for the weight, but it also holds true for the bias, and the activation function from the previous layer.
These are substituted in the chain rule, and their derivatives are calculated in order to obtain their function.

For the bias, the chain rule and its derivative is depicted in [@eq:biasChain].

$$ \frac{\partial C_0}{\partial b^{(L)}} = \frac{\partial z^{(L)}}{\partial b^{(L)}} \frac{\partial a^{(L)}}{\partial z^{(L)}} \frac{\partial C_0}{\partial a^{(L)}} = 1 \sigma'(z^{(L)})2(a^{(L)}-y) $$ {#eq:biasChain}

The chain rule for the previous layer follows the principle of propagating backwards, as can be seen in [@eq:activationChain] where the weight of the preceding neuron is influencing the cost function.

$$ \frac{\partial C_0}{\partial a^{(L-1)}} = \frac{\partial z^{(L)}}{\partial a^{(L-1)}} \frac{\partial a^{(L)}}{\partial z^{(L)}} \frac{\partial C_0}{\partial a^{(L)}} = w^{(L)} \sigma'(z^{(L)})2(a^{(L)}-y) $$ {#eq:activationChain}

But so far this walkthrough have only been considering a network with a single neuron in each layer.
The final part of the section will describe how the equations change if several neurons are introduced in every layer.
Consider the network depicted in [@fig:newNetwork] where there are 3 neurons in layer $a^{(L-1)}$ and 2 neurons in the output layer $a^{(L)}$.
In order to describe the neurons in each layer, the neurons in $a^{(L-1)}$ will be labeled as $a_k^{(L-1)}$, while the neurons in $a^{(L)}$ will be labeled as $a_j^{(L)}$.
The index $k$ and $j$ will thus be used to range over layer $a^{(L-1)}$ and $a^{(L)}$ respectively.
The weight between the two neurons will be denoted as $w_{jk}^{(L)}$.
There will also be more output targets, which will be labeled as $y_j$.

![A network containing more than one neuron in each layer.](report/assets/pictures/nn/Figure4.6.pdf){#fig:newNetwork}

The new network will alter the cost function, as it is now depending on multiple neurons in the output layer.
The new cost function is depicted in [@eq:newCost].

$$ C_0 = \displaystyle\sum_{j = 0}^{N_{L-1}} (a_j^{(L)}-y_j)^2 $$ {#eq:newCost}

The only way that the new cost function in [@eq:newCost] differs from the cost function in [@eq:squareError] is the addition of the summation over all output neurons.
In the new network $z$ also changes its value to take the extra neurons into account, as shown in [@eq:newZ].

$$ z_j^{(L)} = \displaystyle\sum_{k = 0}^{N_{L-1}} w_{jk}^{(L)} a_k^{(L-1)} $$ {#eq:newZ}

For the chain rule for the bias and the weight, the only difference is the addition of the indices in the definition, and these equations are thus omitted.
However, when it comes to the addition of neurons in the previous layer, each neuron will be dependent on the additional neurons, as they each take an input from neurons in their preceding layer.
This new equation is described in [@eq:newA].

$$ \frac{\partial C_0}{\partial a_k^{(L-1)}} = \displaystyle\sum_{j = 0}^{N_{L-1}} \frac{\partial z_j^{(L)}}{\partial a_k^{(L-1)}} \frac{\partial a_j^{(L)}}{\partial z_j^{(L)}} \frac{\partial C_0}{\partial a_j^{(L)}} $$ {#eq:newA}

In [@eq:newA] the summation was added, as all neurons in layer $a^{(L-1)}$ influences all neurons in $a^{(L)}$, and will then become a sum of chain rules.

This section described what happens for every single image in the training process.
The entire process starts over when the next image is given as an input.

#### Simulated annealing
Another technique for learning is called simulated annealing.
This section describes the technique based on multiple sources, namely [@openCV_simulatedAnnealing;@cleverAlgorithms;@machineIntelligence].
The technique imitates annealing from metallurgy where a material is heated and slowly cooled in order to reach the optimal crystal structure of the material.
The simulated annealing algorithm uses much of the terminology from the original method.

The algorithm begins at a maximum temperature, where the algorithm is doing a random walk, and when it cools, and the temperature gets closer to zero, the algorithm changes from a random walk to a pure greedy descent.
Slowly transitioning from a random walk to a greedy descent will allow for the initial search to jump out of local minima in order to search for areas with a higher potential to contain a global minima.
When the algorithm become a pure greedy descent it will focus on finding the local minima in the area that it is currently checking.

The following will discuss each step of the algorithm.
Initially the algorithm picks a random configuration, i.e. random weights and biasses for each connection in the network.
During the runs, the algorithm will keep track of the configuration that has been giving the lowest cost.

##### Picking a solution
The algorithm will randomly pick a new configuration of weights and biases, that is not associated with the previous configuration in any way.
This configuration will be evaluated, and if the new configuration produces a lower cost for the system, in simulated annealing terms called energy, the new configuration is used instead.
Since 'cost' is already used to describe what the 'energy' is, cost will be used in this section, to keep consistency throughout the report.
The term 'energy' is introduced, in case the reader needs to refer to documentation regarding the simulated annealing algorithm outside of this report.
A new configuration, which changes a few of the weights or biasses, will be chosen, in order to be tested again.
If the new configuration does not have a lower cost, the configuration might still be chosen in order to investigate neighboring solutions.
Whether the configuration with a higher cost is chosen or not depends on the temperature $T$.
A high temperature means the algorithm are likely to investigate configurations with higher cost, while a low temperature gives a very low probability that configurations with higher cost are chosen.

##### Temperature
The temperature governs how willing the algorithm is to accept a configuration with a higher cost.
This is done with the Gibbs distribution, which is seen in [@eq:gibbs].

$$ e^{-(C(h')-C(h))/T} $$ {#eq:gibbs}

Where $h$ is the current configuration, $h'$ is the randomly chosen new configuration, and $C$ is the cost of some configuration.
As an example, if the temperature is $10$ and a configuration is $1$-worse (i.e., $C(h')-C(h) = 1$) the probability that this configuration will be chosen, even though it is worse than the current configuration, will be $e^{-1/10}=e^{-0.1} \approx 0.9$.
In contrast, if the temperature is 0.1 the probability that the same configuration will be chosen are $e^{-1/0.1}=e^{-10} \approx 0.00005$

An implementation-specific detail is that the OpenCV library tries a number of different neighboring configurations before decreasing the temperature, instead of decreasing the temperature every time a new configuration is tried.

When the temperature reaches the minimum that is specified beforehand, the configuration with the lowest cost will be chosen.



<!--  
In the first phase the neural network needs to make a prediction in order to see how well it can recognize the desired features or patterns. For doing this, a set of training data is required. In the example case with images of dogs, fish,
or neither,  the training set needs to consist of images of dogs, fish, and neither. Furthermore the images needs to be labeled with what they actually contains, so that the neural network can check if its prediction is correct.-->
<!-- Maybe go more in detail on what a good training set is, and what can be done to improve it -  or maybe leave this to the discussion -->
<!--  Then the second phase is to calculate how accurate the prediction was, and based on this make adjustments to the neural
network based on this, this is called back-propagation. More specifically back-propagation strives to minimize a cost function.
An example of a cost function is to calculate the sum-of-square errors [@eq:squareError].

$$ cost = \sum_{n}(p - a)^2 $$-->
<!-- {#eq:squareError} -->

<!--  
Where $n$ is the number of neurons in the layer, $p$ is the prediction, and $a$ is the actual feature. One approach on
minimizing the cost, is to use gradient descent, to find the local minima, this can be used to find best-->

<!-- label of the image seen in [@eq:accuracy]. Here the first column contains the output from a network with 3 output nodes,the
second column contains the target feature, in this case it can be seen the the network is quite uncertain on which output
feature is present in the input, as the prediction indicates that multiple features could be present, when the label
states that only one feature is.


$$
cost =
\begin{matrix}
  (0.5 - 0.0)^2 & = & 0.5 \\
  (0.8 - 1.0)^2 & = & -0.2  \\
  (0.2 - 0.0)^2 & = & 0.2
\end{matrix}
$$ {#eq:accuracy}

The resulting column shows the quadratic error from each output node. The lower the error the more precise the
neural networks prediction is. The individual errors can then be used to indicate how weights should be adjusted, and in
which direction.  

The difference is found by calculating the sum-of-square errors see [@eq:squareError]. The sum-of-square errors are
calculated for each neuron in the output layer, this yields a vector with a square error for each neuron, the higher the
number is the worse the neural network is at predicting the desired features, and the more it shows which weights needs
 needs to be adjusted.
-->

#### Overfitting
<!-- Here we will explain the dangers of overfitting the model. Also, how to ensure that it does not happen. -->

The concept of overfitting in machine learning is that the model is really good at classifying data based on the
training set, but when the model is utilized to classify based on data that was not part of the test data, the model
performs poorly and cannot classify the data correctly.
Overfitting occurs when the model is unable to generalize, and instead focuses on too concrete data in the training data.
This means that whatever features the model was trained on, it would only correctly classify those, since data that
slightly deviate from the training set are not correctly classified.

A possible way to reduce the effect of overfitting is to give the model more, and different, data to train on.
This can be achieved with data augmentation, which is the act of slightly manipulating the data by changing values or by
rotating or zooming in on an image.
This is beneficial because it provides data that is similar to the original data but with concrete data obscured, so the network is forced to generalize.

To test if the model has been subject to overfitting, the model should be benchmarked relative to data that is has not trained on.
If the model experiences high accuracy on the training data, while it experiences low accuracy on the test data, it means the model is subject to overfitting.
The high accuracy on the training data means that the model will not get change a lot if it keeps training - it might only become more overfit.

# Our Artificial Neural Network 
\label{sec:ourANN}

Our implementation uses the OpenCV multi-layer perceptron (MLP) artificial neural network (ANN) to recognize the digits in the speed sign.
The following section will describe the methodology behind the creation of the neural network.
Topics such as configuration and activation- or training functions which were used are discussed, as well as why they were chosen.

## Configuration
In order to train the network, different configuration parameters of the network had to be chosen.
The implementation had a total of 10 different configuration parameters to alter, all relating to one of the following:
- The activation function
- The training method
- The training length
- The layer structure

The implementation was capable of performing a single training session in about 2 minutes, when performed on an Intel Xeon W3530, 2.80 GHz and 8 logical cores, 6 x 4 GB ECC memory 1066MHz DDR2 and two GPU's, GTX 750 TI and GTX 660 respectively.
Our implementation allowed for the values of the configurations to be loaded through a .csv file format, to which we created a script that generated different test configurations.
When testing a hypothesis, it was sufficient to supply estimates of the appropriate ranges for the values, as the script methodically creates tests that ensures all configurations in the supplied range, and with the supplied interval, are tested.

Testing would ensue at night, and test results was analyzed in the morning.
This method of testing allowed for the exploration of several hypotheses about the structure of the network, and for finetuning the final settings of the network.
Training was done this way, in order to determine the best activation and training functions, while also testing multiple different layer configurations.

### Training function
The neural network was tested with three different training functions from OpenCV: gradient descent, RProp, and simulated annealing, where simulated annealing yielded the best results.
This finding could be due to simulated annealing's ability to find different local minima, and approximate a global minima, as gradient decent and RProp are more dependent on the initial seed of the neural network.
This does not mean that gradient decent and RProp cannot be used, but more extensive testing is required in order to explore the possible global minima.
This is partly due to the problem concerning the saturation of the neurons, described in [@sec:backpropagation].

### Activation function
The sigmoid activation function was chosen, even though it has the potential of giving saturated neurons.
This problem, however, was countered with the choice of training function.

The only other activation function that is fully supported is the step function.
The step function does not give a descriptive gradient that allows the training function to know if a neuron is improving the cost function or not.
This is due to the step function being a threshold function that simply returns 0 or 1.

### Data set

The data set used to train the neural network is called GTSRB (German Traffic Sign Recognition Benchmark) [@GTSRB] -  a large
data set consisting of German traffic signs, which resembles Danish traffic signs. The data set is diverse, as it contains images of traffic signs under
varying conditions e.g. different angles and/or varying degrees of obstruction.
Training on this data set proved hard to achieve high prediction accuracy, as the images were so diverse, and in some cases
so noisy that it was almost unrecognizable by humans, as seen in [@fig:badSigns].

<div id="fig:badSigns">

![](report/assets/pictures/50SignObstructed.png){width=36% height=36%}
![](report/assets/pictures/OtherBadSign.png){width=50% height=50%}

Examples of edge case signs from the GTSRB data set.

</div>

The prediction accuracy turned out to be better when tested on the actual car's camera, with signs that were not distorted, angled, or obstructed.
This shows that the model was still trained to detect normal signs, and that the issues was mainly in the event of edge cases.

 <!-- http://benchmark.ini.rub.de/?section=gtsrb&subsection=dataset -->

#### Increasing Accuracy
Trying to recognize both digits in the speed signs was suspected to be difficult for the netowrk.
Since the last digit, the zero, does not change from speed sign to speed sign, it does not make sense to feed this number to the neural network.
This realization occurred very late in the process, which is why a proof-of-concept implementation, handling the number splitting, was the only version the group had time to do.
After a circle is flattened, the digits are expected to be in each their own half of the speed sign.
The proof-of-concept method then takes the right half of the image and discards it, and sends the left half to the network.

The group acknowledges that a _proper_ implementation of this strategy _should_ have been part of the pre-processor.