
## Analysis of the EV3 
The EV3 will be running LeJOS as its operating system, LeJOS is a Linux derivative. We were unable to find its concrete resource usage. Therefore experiments will be conducted to identify how many resources the OS will consume, and hence how many resources are free to be used.

### Hardware specification
The hardware specifications of the lego EV3[@the_lego_group_lmsuser_2015, p. 8]

| EV3 Specification        |             |
| ------------- |:-------------|
| Processor     | ARM 9 - 300Hz |
| Memory      | 64 MB |
| Storage | 16 MB Flash Memory & SD card slot up to 32 GB |
| Communication | Bluetooth v2.1 & 1 USB 2.0 micro\* & 1 USB 1.1** & 4 Motor ports |

\* For communication with host PC.

** For host communication.

#### Test methodology
For testing both heap and stack size of the EV3 with LeJOS, the tests will be separated to minimize noise in the measurements.

For testing the heap a small program was developed, utilizing dynamic allocation for testing the heap size. This will be done using Java's `arrayList` implementation, and then adding elements to the list until the LeJOS throws an `OutOfMemoryException`. To see the actuall memory usage Java's runtime class is utilized.

For testing the stack size, the `arrayList` could not be utilized since it is located on the stack, the same premise applies for normal arrays in Java. Because of this the stack size was tested by conforming a recursion function, which where not a Taylor recursion. The recursion function was conformed in such a way that it would never yield a result, effectively creating an infinite loop. In each recursion call a counter was increased, to count the number of calls. The recursion function then ran until an `stackOverflowException` occurred. Then Java's runtime environment was used to output the memory statistics and the counter was outputted.

#### Hypothesis
Since the EV3 has 64 MB of RAM according to the specifications provided by LEGO, it is assumed that it is 64 MB of RAM installed, this means that some of the RAM will be used by the operating system running on the device. Since the group has chosen to use a third-party operating system, it is assumed that it would rather costly in RAM usages. Also since the chosen operating system runs a Java Virtual Machine it is assumed to take up even more RAM. 

Based on the above considerations the group assumes that the operating system would take roughly around 1/3 of the  RAM and therefore there will be 2/3 of the RAM available for utilization. Since LEGO specifies the EV3 brick to have 64 MB of RAM it is assumed that around 21 MB of RAM is occupied by the operating system and around 43 MB is available for utilization.

#### Test Results 
The first test conducted was the heap test. The result of which are shown in the table below.

| Memory Categorie | RAM in MB |
| ---------------- | :-------: |
| Used Memory      | 23        |
| Free Memory      | 5         |
| Total Memory     | 29        |
| Max Memory       | 29        |

As seen in the table above, the Java runtime identifies the max available as 29 MB, and when utilizing the RAM, we can use all up to a total of 29 MB. It might seem odd why only 23 MB is used and 5 MB is free, but this is due to how the test is conducted. The `ArrayList` implementation in Java, work by doubling its space every time it runs out of memory[@jdk_arraylist]. And the last attempt to do so violates the upper bound of 29 MB, which throws the `OutOfMemoryException`.

Next the stack size was tested. The result of which are shown in the table below.

| Memory Categorie       | RAM in MB |
| ---------------------- | :-------: |
| Used Memory            | 1         |
| Free Memory            | 3         |
| Total Memory           | 4         |
| Max Memory             | 29        |

The Java Runtime is still specifying that the max available memory is 29 MB, but in this test only 4 MB where utilized. Where of those 4 MB, 3MB was free memory and 1 MB was used memory. This shows that the stack size is 1 MB.

### Conclusion
The tests show that only 29 MB of the EV3's 64 MB memory is actually available. The group assumes that the other 35 MB of memory is used by the LeJOS operating system. Since LeJOS do not provide any specification on this part, we cannot assert this assumption.

The stack size test showed that a `StackOverflowException` was thrown when the memory used was only 1 MB and having 3 MB free memory, this leads the group to conclude that the stack size is a fixed size of 1 MB.

Therefore the group concludes that the system is bound by the restrictions of 29 MB of ram where 1 MB is dedicated to a fixed stack size. This means that all software that is intended to run on the EV3 brick should never be able to exceed these limitations.
