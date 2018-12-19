# Conclusion
In 2017, more than 1700 people were seriously injured in a road accident in Denmark, while 175 people were killed.
Speed had a huge impact in a lot of the accidents, and companies today are beginning to automate tasks, in order to increase the safety.
To decrease cars' speed on the road, the project will try to implement an intelligent system that recognizes speed signs and is capable of instructing an embedded system to obey the speed limit.

The embedded system was designed and built to drive at a noticeable speed, with several different levels of speed in mind.
The car employed the embedded system, and was configured to obtain instructions from a Raspberry PI carried onboard the car.
The Raspberry PI is responsible for the processing of the image, and handle it by first taking it through a pre-processing state, and later a neural network.
The pre-processing is responsible for normalizing the data, and obtains some very good results by providing a black-and-white image, where angled perspective is eliminated and the number is isolated.

The neural network is a feed-forward neural network containing 30x30 neurons in the input layer, and a single hidden layer of 21 neurons, and uses the sigmoid activation function, along with the simulated annealing learning method.
To train the neural network, a data set from a German speed sign recognition competition is used, containing about 7.000 pictures in the training set, and more than 3.300 pictures in the test set.
The test set contains a lot of images where the image quality are very bad and challenged. 
The neural network obtained an accuracy of 68 %, but further research is needed to diagnose the limiting features of the system.

Safety features of cars are a continually developed area, and the revelations in this report emphasizes the importance of pre-processing the input data, as well as choosing the right configuration of the neural network.
Operating in as mutable environments as on the roads, where both time of the day and year have a huge influence on the environment, as well as the weather in general, is a huge task for software systems in cars, and demands great stability.
