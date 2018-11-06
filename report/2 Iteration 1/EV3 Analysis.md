
## Analysis of the EV3 


The EV3 will be running LeJOS as its operating system, LeJOS is a Linux derivative. We were unable to find its concrete resource usage. Therefore experiments will be conducted to identify how many resources the OS will consume, and hence how many resources are free to be used.

### Hardware specification
The hardware specifications of the LEGO EV3:

| EV3 Specification        |             |
| ------------- |:-------------|
| Processor     | ARM 9 - 300Hz |
| Memory      | 64 MB |
| Storage | 16 MB Flash Memory & SD card slot up to 32 GB |
| Communication | Bluetooth v2.1 & 1 USB 2.0 micro\* & 1 USB 1.1** & 4 Motor ports

<!-- https://web.archive.org/web/20150224035959/http://service.lego.com/en-us/helptopics/products/themes/mindstorms/mindstorms-ev3/ev3-and-nxt-differences -->

\* For communication with host PC.

** For host communication.

#### Test methodology
For testing both heap and stack size of the EV3 with LeJOS, the tests will be separated to minimize noise in the measurements.

For testing the heap a small program was developed, utilizing dynamic allocation for testing the heap size. This will be done using Java's `arrayList` implementation, and then adding elements to the list until the LeJOS throws an `OutOfMemoryException`. To see the actuall memory usage Java's runtime class is utilized.

For testing the stack size, the `arrayList` could not be utilized since it is located on the stack, the same premise applies for normal arrays in Java. Because of this the stack size was tested by conforming a recursion function, which where not a Taylor recursion. The recursion function was conformed in such a way that it would never yield a result, effectively creating an infinite loop. In each recursion call a counter was increased, to count the number of calls. The recursion function then ran until an `stackOverflowException` occurred. Then Java's runtime environment was used to output the memory statistics and the counter was outputted.

<!-- http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/tip/src/share/classes/java/util/ArrayList.java -->
#### Test Results 

The first test conducted was the heap test. The result of which are shown in the table below.

| Memory Categorie | Ram in MB |
| ---------------- | :-------: |
| Used Memory      | 23        |
| Free Memory      | 5         |
| Total Memory     | 29        |
| Max Memory       | 29        |

As seen in the table above, the Java runtime identifies the max available as 29 MB, and when utilizing the ram, we can use all the up to a total utilized of 29 MB. It might seem odd why only 23 MB is used and 5 MB is Free, but this is due to how the test is conducted, the `ArrayList` implementation in Java, work by every time the array is too small, it doubles its space. And the last attempt to do so violates the upper bound of 29 MB, which throws the `OutOfMemoryException`.

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
