### Analysis of the EV3 
The EV3 will be running LeJOS, a Linux derivative, as its operating system. As the actual resource usage of LeJOS was not found some experiments will be conducted to identify how much of the available resources the OS will consume, and hence how many resources are free to use.

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
For testing both heap- and stack size of the EV3 with LeJOS, the tests will be separated to minimize noise in the measurements.

For testing the heap, a small program was developed, utilizing dynamic allocation for testing the heap size. This utilization should be done by creating a LinkedList of bytes, and add a new byte to the head of that LinkedList until the program throws an `OutOfMemoryError`. Then Catch that exception, and record the memory usage, by utilizing the Java Runtime object. The point is to increase the memory usages by as little as possible every step, therefore the choice of increasing with a single byte at a time. Although there is some overhead in running the program and using LinkedList, so in reality, it is not the case that a single byte is added at a time, it will be a higher number of bytes per step. Doing so is a trade-off that is needed, since not keeping references to every byte alive, would result in the garbage collector clearing the memory before we would be able to fill it.

For testing the stack size, a different technique is required. A small program was created, which calls a recursion function that merely takes an integer as input, stores it to a variable and increment it by 1, then calls itself with the new value. We call this never-ending recursion in a `try` block and waits for a `StackOverflowError`. Then uses the Java Runtime object to get the information about memory usages.

#### Hypothesis
Since the EV3 has 64 MB of RAM, according to the specifications provided by LEGO, it is assumed that this means 64 MB of physical RAM installed. This means that some of the RAM will be used by the operating system running on the device. Since the group has chosen to use a third-party operating system, it is assumed that this OS is rather costly in RAM usage. Also, the chosen operating system runs a Java Virtual Machine, and this VM is assumed to take up even more RAM.

Based on the above considerations, the group assumes that the operating system would occupy roughly 1/3 of the RAM, and 2/3 of the RAM will be available for utilization. In specific numbers: LEGO specifies the EV3 brick to have 64 MB of RAM. It is assumed that the operating system occupies around 21 MB of RAM and around 43 MB is available for utilization.

#### Test Results 
The first test conducted was the heap test. The test was conducted five times to establish an average and to confirm that the unit was consistent. The result of which is shown in the table below, the table shows the Java Runtime statistics at the point of the `OutOfMemoryError` happened.

| Attempt   | Total Memory | Free Memory | Used Memory |
| --------- | --------------------: | ----------: | ----------: | 
| Attempt 1 |              28,573,696 |         112 |    28,573,584 |
| Attempt 2 |              28,573,696 |         120 |    28,573,576 |
| Attempt 3 |              28,573,696 |         120 |    28,573,576 |
| Attempt 4 |              28,573,696 |         120 |    28,573,576 |
| Attempt 5 |              28,573,696 |         120 |    28,573,576 |
|           |                       |             |             |
| Average   |              28,573,696 |       118.4 |  28,573,577.6 |
Table: The result of the Heap memory test. The result is represented in bytes.

As seen in the table above, the Java runtime identifies the max available memory as around 28.5 MB, and when utilizing the RAM, we can use up to a total of 28.5 MB. The average difference in available memory and usable memory is only 118.4 bytes. The test also shows consistent usability of the ram.

The second test was the stack size test. Just like the heap test, this test was also conducted five times to get an average. The result is shown in the table below, and the table shows the Java Runtime statistics at the point where the `StackOverflowError` happened.

| Attempt   | Total  Memory | Free Memory | Used Memory |
| ----------- | --------------: | -------------: | --------------: |
| Attempt 1     |     5,111,808 |        3,857,824 |        1,253,984 |
| Attempt 2     |        5,111,808 |        3,856,520 |         1,255,288 |
| Attempt 3     |        5,111,808 |        3,855,104 |         1,256,704 |
| Attempt 4     |        5,111,808 |        3,854,776 |         1,257,032 |
| Attempt 5     |        5,111,808 |        3,855,576 |         1,256,232 |
|                   |                             |              |                   |
| Average       |        5,111,808 |        3,855,960 |     1,255,848 |
Table: The result of the stack size memory test. The result are represented in bytes.

The first thing of note is that the Total Memory seems to be significantly less than the heap test. However, this is due to a somewhat confusing naming convention from the Java Runtime object. The Total Memory turns out to be the total amount of memory allocated to the program as a whole. This particular program runs out of stack size before it ever needs to ask for more memory in the heap. Therefore we only get the first 5 MB of ram. The heap test was revisited after this finding since there is a Max Memory attribute in the Java Runtime object also, which return the maximum amount of memory that is allocatable to the program, but the findings were consistent with the originals, so no further actions were taken.

Secondly, the test shows a couple of findings, one of them is that the stack size is roughly 1.2 MB, the next thing is that the stack size is upper bounded. Meaning it is not allowed to grow as long as there is enough memory dynamically. That means that every program written for the car should be aware of the stack size to ensure never to reach a `StackOverflowError`.

#### Conclusion
The tests show that only 28.5 MB of the EV3's 64 MB memory is available. The group assumes that the LeJOS operating system uses the remaining 35.5 MB of memory. Since LeJOS does not provide any specification on this part, we cannot assert this assumption.

The stack size test showed that a `StackOverflowError` was thrown when the memory used was only 1.2 MB and having 3.8 MB free memory, this leads the group to conclude that the stack size has an upper bound of roughly 1.2 MB.

Therefore the group concludes that the system is bound by the restrictions of 28.5 MB of ram where 1.2 MB is dedicated to the stack, leaving 27.3 MB of memory available for dynamic allocations. This means that all software that is intended to run on the EV3 brick should never be able to exceed these limitations.
