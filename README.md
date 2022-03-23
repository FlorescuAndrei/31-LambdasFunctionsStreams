# 31-LambdasFunctionsStreams
Lambdas. Functions (java.util.function). Streams (java.util.stream)

This is a study project for:   
-  lambda expressions   
-  some interfaces from java.util.function package: Consumer, Supplier, Predicate, Function;   
-  Stream interface from java.util.stream package  

[BACK TO START PAGE](https://github.com/FlorescuAndrei/Start.git)


### Lambda Expressions  

Provide an easy way to work with functional interfaces, interfaces that have only one method.  
When using lambdas?  
-  Replace anonymous classes. New keyword – indicate an anonymous class. If this class implement a functional interface we can consider using lambda;  
-  Iterate over a collection;  
-  Method to Lambda:  java.util.function : Consumer, Supplier, Predicate, Function etc…   
 
Lambda has 3 parts: argument list, arrow and code you want to run: (method parameters)-> {method body}  


Scope and variables  
Lambda replaces an anonymous class but is not treated like an anonymous class (an anonymous instance isn’t created).   
A lambda is treated as a nested block of code and has the same scope as a nested block  
-  code in the nested block can refer to variables defined outside the nested block but within the enclosing block (define in the block of code that contains the nested block – like the method body.  
-  Code in the anonymous class  can refer to a local  variable declared outside of the anonymous class if we declare the variable as final because when the instance of the anonymous class is created it saves the value of the variable  
-  Code in the lambda can refer to a local variable outside the lambda if it is final or effectively final  
Effectively final: not declared final but the value will not be changed.  

### java.util.function  

Contains functional interfaces meant to be used with lambda expressions.  
![functions:](/functions.png)  


### java.util.stream Stream Interface  
.
Stream Interface, not related to IO stream (like Input Stream and Output Stream).  

Crate a stream:  
-  Base on a collection – we use .stream()method (someArrayList.stream(). ….)  
-  Stream<String> stream = Stream.of(string1, string2, string3)  

.stream() method was added to the collections class in Java8. This method creates a stream from a collection.   
  
Stream  is a sequence of computation, intermediate operations eded with a terminal operation.  
Theterminal operation will execute the Stream (example forEach,  or .count).  
Operations in the Stream are lazily evaluated. Intermediate operations are not performed until is a terminal operation.  
After the terminal operation, the Stream can not be reused.   
If we want to save some results from a Stream we can use .collect() method as a terminal operation.  
  
Method used with Stream:   
.filter()  
.sorted()  
.forEach - terminal operation  

.map() use function (s->s.toUpperCase) accept an object and return a value . Map each item from the input stream to the result return by the function   
  
.flatMap() mapping one object to many.    
 -  Accept a function that returns a Stream value, so we can pass an object ( - that contains a list) as the function argument and return a Stream containing several objects (- the items in the list).  
 
collect() method – collect the elements in a Steam into a different type of result like a List.  
  
[BACK TO START PAGE](https://github.com/FlorescuAndrei/Start.git)
