### Analysis of the EV3 Memory
The EV3 will be running leJOS, a Linux derivative, as its operating system. 
As the group was unable to identify the actual resource-usage of leJOS, some experiments are conducted to identify how much of the available resources the OS will consume, and hence how many resources are free to use.

#### Hardware specification
Seen below is a table of the specifications of the LEGO EV3 as found in [@the_lego_group_lmsuser_2015, p. 8].

| EV3 Specification        |             |
| ------------- |:-------------|
| Processor     | ARM 9 - 300Hz |
| Memory      | 64 MB |
| Storage | 16 MB Flash Memory & SD card slot up to 32 GB |
| Communication | Bluetooth v2.1 & 1 USB 2.0 micro\* & 1 USB 1.1\*\* & 4 Motor ports |

\* For communication with host PC.

\*\* For host communication.

#### Test methodology
For testing the memory of the EV3 with leJOS, two sub-tests, one for the heap size and one for the stack size, will be conducted to minimize the noise in the measurements.

For testing the size of the heap, a small program was developed, utilizing dynamic memory allocation. This utilization is done by creating a linked list of bytes, and adding a new byte to the head of that linked list until the program throws an `OutOfMemoryError` exception. The exception is then caught, and the memory usage is recorded by utilizing the Java Runtime object.

Through leJOS a Java Virtual Machine is introduced, which in turn introduces a garbage collector. This garbage collector hinders the possibility to naïvely place a new byte in the heap for every step, as these bytes would not be kept "alive" throughout the entire run of the program. To compensate for the garbage collector, a linked list is used, which ensures that the references for every byte are kept alive, albeit at the cost of a memory overhead.
In pracsis, this does not allow for the amount of used memory to increase by one byte per step, which is a trade-off. This trade-off is considered resonable by the group, since the overhead is expected to be relatively low, and therefore the test should still be able to produce an acceptable result.

For testing the stack size, a different approach was used. Another small program was developed, in which a recursive method is invoked. This method takes an integer as input, stores it to a local variable, increments it by 1, and then invokes the method again with the new value. This never-ending recursion happens within a `try` block which ultimately catches a `StackOverflowError` exception. The Java Runtime object is then utilized to get information about the memory usages.

#### Hypothesis
Since the EV3 has 64 MB of RAM, according to the specifications provided by LEGO, it is assumed that this means 64 MB of physical RAM installed. This means that some of the RAM will be used by the operating system running on the device. Since the group has chosen to use a third-party operating system, it is assumed that this OS is rather costly in RAM usage. Also, the chosen operating system runs a Java Virtual Machine, and this VM is assumed to take up even more RAM.

Based on the above considerations, the group assumes that the operating system would occupy roughly 1/3 of the RAM, while 2/3 of the RAM will be available for utilization. Since LEGO specifies the EV3 brick to have 64 MB of RAM, it is therefore assumed that the operating system occupies around 21 MB of RAM, and around 43 MB is available for utilization.

#### Test Results 
The first test conducted was the heap test. The test was conducted five times to establish that the unit was consistent, and the results of these tests are shown in [@tbl:heapTestTable]. The table shows the Java Runtime statistics at the point in time when the `OutOfMemoryError` exception was caught.

| Attempt   | Total Memory | Free Memory | Used Memory |
| --------- | --------------------: | ----------: | ----------: | 
| Attempt 1 |              28,573,696 |         112 |    28,573,584 |
| Attempt 2 |              28,573,696 |         120 |    28,573,576 |
| Attempt 3 |              28,573,696 |         120 |    28,573,576 |
| Attempt 4 |              28,573,696 |         120 |    28,573,576 |
| Attempt 5 |              28,573,696 |         120 |    28,573,576 |
Table: The result of the Heap memory test. The result is represented in bytes. {#tbl:heapTestTable}

As seen in [@tbl:heapTestTable], the Java Runtime identifies the total amount of allocated memory for the program execution as 27.25000 MB. When utilizing the linkedList to fill up the RAM, we can occupy up to a total of 27.24989 MB. The upper bound difference between available and usable memory is 120 bytes. The test also shows consistent usability of the ram throughout the five test runs.

The second test was the stack size test. Just like the heap test, this test was also conducted five times. The result is shown in [@tbl:stackSizeTable], which displays the Java Runtime statistics at the point in time where the `StackOverflowError` exception was caught.

| Attempt   | Total  Memory | Free Memory | Used Memory |
| ----------- | --------------: | -------------: | --------------: |
| Attempt 1     |     5,111,808 |        3,857,824 |        1,253,984 |
| Attempt 2     |        5,111,808 |        3,856,520 |         1,255,288 |
| Attempt 3     |        5,111,808 |        3,855,104 |         1,256,704 |
| Attempt 4     |        5,111,808 |        3,854,776 |         1,257,032 |
| Attempt 5     |        5,111,808 |        3,855,576 |         1,256,232 |
Table: The result of the stack size memory test. The result are represented in bytes.{#tbl:stackSizeTable}

The first thing to note is that the Total Memory for program execution seems to be significantly less than in the heap test. This difference is due to a somewhat confusing naming convention from the Java Runtime object. The program runs out of available stack size before it ever needs to ask for more memory in the heap, thereby only occupying the first 5 MB of ram. These results are what made the group realize that the Total Memory from the Java Runtime turns out to be the total amount of memory allocated to the program as a whole. The heap test was revisited after this finding, since there is a Max Memory attribute in the Java Runtime object as well, which returns the maximum amount of memory that is allocatable for the program. The findings were consistent with the originals, so no further actions were taken.

Secondly, the test shows a couple of findings, one of them being that the stack size is consistently around 1.2 MB, the other being that the stack size is upper bounded - meaning the stack is not allowed to grow dynamically as long as there is free memory.

#### Conclusion
The tests show that 27.25 MB (rounded) of the EV3's 64 MB memory is available. It is assumed that the leJOS operating system uses the remaining 36.75 MB of memory. Since leJOS does not provide any specification on this part, we cannot assert this assumption.

The stack size test showed that a `StackOverflowError` exception was thrown when the memory used was 1.2 MB, which was concluded to be the stack size upper bound.

The group concludes that the system to be designed for the EV3 is bounded by the restrictions of 27.25 MB of RAM, where 1.2 MB is dedicated to the stack, leaving 26.05 MB of memory available for dynamic memory allocation. 
