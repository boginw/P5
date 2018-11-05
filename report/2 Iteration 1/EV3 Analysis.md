
## Analysis of the EV3 


The EV3 will be running LejOS as its operating system, LejOS is a Linux derivirative. We were unable to find it's concrete resource usage, therfore test will be conducted to identify how many resources the OS will consume, and hence how many resources are free to be used.

### Hardware specification
The hardware specifikations of the lego EV3:

| EV3 Specification        |             |
| ------------- |:-------------|
| Processor     | ARM 9 - 300Hz |
| Memory      | 16 MB |
| Storage | 64 MB & SD card slot |
| Communication | Bluetooth v2.1 & 1 USB 2.0 & 4 Motor ports

<!-- https://web.archive.org/web/20150224035959/http://service.lego.com/en-us/helptopics/products/themes/mindstorms/mindstorms-ev3/ev3-and-nxt-differences -->

#### Test methodology
For testing both heap and stack size of the EV3 with LejOS, the tests will be seperated to minimize noice in the measurments.

For testing the heap a small program was developed, utilizing dynamic allocation for testing the heap size. This will be done using java's `arrayList` implementation, and then adding elements to the list until the LejOS throws an `OutOfMemoryException`. To see the actuall memory usage Java's runtime class is utilized.

For testing the stack size Java's runtime envirionment could not be used as it only detects heap usage. Instead here the stack size will be estimated through recursion.
By making a never ending recursive function; then count the amount of recursions before a `stackOverflowException` occours; find the memory usage of the method and multiply it with the amount.

<!-- http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/tip/src/share/classes/java/util/ArrayList.java -->
#### Test Results 

From dynamic memory allocation we found that the LejOS roughly allocated 29MB to the heap, based on readings from Java's runtime class. 
MORE TO COME

### Conclusion
Fill