# Machine Intelligence Implementation Language Candidates
1. Python
2. Java
3. C++

### Description of the Language Paradigm
Python is a widely used language and has existed for many years. Python is popular for its simplicity, readability and being a general-purpose language. Python also supports multi programming paradigms including object-oriented, imperative, functional, procedural.

Java is almost used everyday and is a popular language because of its wide availability. Java is also a general-purpose language and supports a class-based, object-oriented paradigm. 

C++ is primarily used where you are especially interested in performance and proper resource utilization. One platform could be embedded systems. C++ is also a general-purpose programming language and has support for imperative and object-oriented programming features. 

### Description of Features Regarding Machine Intelligence
Python has been extensively used for Artifical Intelligence particularly Machine Learning. A quick search for "machinelearning" on GitHub shows a large amount of projects that use Python for development [^github_machine_learning]. This could somewhat indicate its popularity. However, the most prominent features specific to Python is probably its readability and simplicity, but also because its syntax is much like mathematical notation and being pseudocode-like. Another fortunate point of Python is the extensive development towards open source projects that remove boilerplate of creating machine intelligence algorithms and other related software.
// Unsure as to how to express the last sentence. 

Java is also a popular language with a large and active community. Java, like Python, also has a lot of reliable libraries providing the basics towards development of MI algorithms for solving problems related to some requirement of intelligent behavior. 

C++ has the clear benefit of being very fast performance wise, which is great because of the level of control it provides. This is perhaps also why many of the libraries for Python and Java have been developed using C++. C++ could also be considered a desirable language to work with as it very low level and is therefore a good fit when working with embedded systems. However, using C++ would definitely require a lot of boilerplate and probably extend the development period of the project. 

Even though all listed languages can help solve the MI part of this project, certain languages do provide open source libraries that can be utilized to remove much boilerplate code, which is required to implement MI related algorithms. 

### Pros and Cons Table
It is important to note that almost any high or low level programming language can be used for implementation of machine intelligence algorithms. However, major difference in the language constructs that result in low or high readability or simplicity do exist. Therefore, certain criteria for picking a language must be considered in order to have a concrete measure for which language is to be chosen.

In regards to a language expressing simplicity and/or readability, a certain measure is required as these adjectives can be of subjective opinion to a certain extend. 
Therefore, code examples of how to output "Hello, World!" to the console will be used as a form of measurement even though this might not be as definitive. 

Python "Hello, World!" example:
```python
print("Hello, World!")
```

Java "Hello, World!" example:
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

C++ "Hello, World!" example:
```c++
#include <iostream>
using namespace std;

int main() 
{
    cout << "Hello, World!";
    return 0;
}
```

// The table description should be "Feature table that outlines the desired features labeled with *low, medium* or *high*".
Based on the code examples and  description of the languages above, the following table was created:

| Features       | Python | Java   | C++    |
|----------------|--------|--------|--------|
| Simplicity     | High   | Medium | Low    |
| Readability    | High   | Medium | Low    |
| MI Community   | High   | Medium | Low    |
| Modularity     | High   | High   | High   |
| Learning Curce | High   | Low    | High   |

The fortunate thing about Java is that it can almost be directly mapped to C# syntactically. This is benefical in the sense that all contributors to this project have had an indepth C# course, which is very similar to Java in serveral ways. Therefore, the learning curve is minimized (low), which means that more of the focus and time spent can be forwarded towards actual implemention and solving the problem of this project rather than also having to spend time learning Python or C++. However, the clear benefit of Python is its large amount of open source libraries related to MI or rather machine learning (ML), which can be utilized for fast prototyping and testing of ideas as their appear.  

Based on the table above, language X was chosen to proceed with the development of the project solution. 

### Conclusion
The purpose of this section was to compare three languages and figure out whether or not these languages would provide some features towards the development of the project as a whole. This was done by first comparing the languages and finally create a table that would elicit different features measured in a scale from low to medium to high. 

// Notes
// + Perhaps argue why the chosen features have been selected over other features?
// + Reference of the languages being simple and readable or not?

[^github_machine_learning]: https://github.com/search?q=machinelearning
[^stackoverflow_dev_survey_2018]: https://insights.stackoverflow.com/survey/2018/#most-popular-technologies