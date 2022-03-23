package com.andrei.streams;

//Stream Interface
//no connections to Java IO streams like InputStream or OutputStream
//A Stream has elements like a collection, but when we refer to a stream we make a sequence of computation

import com.andrei.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainStreams {

    public static void main(String[] args) {

        //Arrays.asList - create and populate the list
        List<String> someBingoNumbers = Arrays.asList(
                "N40", "N35",
                "B12", "B6",
                "G53", "G49", "G60", "G50", "g64",
                "I26", "I17", "I29",
                "O71");

        List<String> gNumbers = new ArrayList<>();



    //sort and print G numbers
        someBingoNumbers.forEach(number ->{
            if(number.toUpperCase().startsWith("G")){
                gNumbers.add(number);
            }
        });

        gNumbers.sort((s1, s2) -> s1.compareTo(s2));
        gNumbers.forEach(s -> System.out.println(s));

        System.out.println("===========Stream based on Collection============");

    //same code  sort and print gNumbers with Streams
        //.stream() method was added to collections class in Java8. This method create a stream from a collection
        // the original ArrayList someBingoNumbers will not be changed
        someBingoNumbers
                .stream()
                //.map(s -> s.toUpperCase())
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);

        //The result of the .stream() call is a stream that contains all the items from the someBingoNumbers in the same order that appear in the list
        //this will be the input stream
        //the result of .map() will return a stream that contains all uppercase bingo numbers.
        //.map() accept a function (s->s.toUpperCase) = (String::toUpperCase).
        //(s -> s.toUpperCase) is a function, accept an object and return a value
        // will map each item from the input stream to the result returned by the function
        //.filter() accept a predicate (s -> s.startsWith("G") take one parameter, s, and return true or false - like if()
        //.forEach() is from the Stream class (like all other .map, .filter, .sorted) is not the regular forEach method from Iterator class use in other projects,
        // but is similar. It accepts a consumer as a parameter and evaluate the consumer for each item in the stream.
        //System.out.println accept an argument and does not return any value, so we can map it to a consumer

        //all methods return a value that will be use in the next step except the last method: forEach().
        //forEach() is a terminal operation - return void or non stream result
        //the other methods ar intermediate operation

//        Class::Method	 “::” notation is called method reference (no relation to Stream).
//        We can use them when all a lambda does is call an existing method
//        (s->s.startWith("G")) can not be transformed in method reference because we can't pass "G"


        System.out.println("=========== Stream ============");

        // the previous stream is based on a collection
        // now we will create a stream from scratch

        Stream<String> isNumberStream = Stream.of("I26", "I17", "I29", "S71");
        Stream<String> imNumberStream = Stream.of("M40", "M36", "I26", "I17", "I29", "S71");
        Stream<String> concatSteam = Stream.concat(isNumberStream, imNumberStream);

//        System.out.println(concatSteam.count());

        // count without duplicates
//        System.out.println(concatSteam.distinct().count());

        //print the items and count
        System.out.println(concatSteam
                .distinct()
                .peek(System.out::println)
                .count());
        //we use peek() instead forEach() because forEach() is a terminal operation. Here we use .count() as terminal operation.


        System.out.println("============= .flatMap() method =============");
//map() method accept a function that takes an object and return a value . Map each item from the input stream to the result return by the function
//flatMap() method: intended use: mapping one object to many
//flatMap() accept a function that returns a Stream value, so we can pass an object as the function argument and return a Stream containing several objects.

        //we use flatMap() to perform operation on a list but the list isn't the source, the list is nested, an object containing a list
        //we use the method to create a Stream from all the object in that list
        //we give an object that contains a list as an argument for the function and return a Stream with the items from the list

        Employee john = new Employee("Jon Doe", 30);
        Employee jane = new Employee("Jane Deer", 25);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        Department hr = new Department("Human Resources");
        hr.addEmployee(jane);
        hr.addEmployee(jack);
        hr.addEmployee(snow);

        Department accounting = new Department("Accounting");
        accounting.addEmployee(john);

        //print all Employees
        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        departments
                .stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

        //.stream() create a Stream from departments list
        //flatMap() returns another Stream.
        //flatMap() take a function, each department from the source Stream become the argument for the function
        //we get a list of employees for a department, and we call the .stream() on that list to return a stream of employees
        //I understand that we have two Stream of employees because there are two departments.
        //the items in these streams are added to the stream return from the flatMap() method
        //forEach() print the elements from the stream

// I create a Stream based on departments list
// each department has a nested list of employees,
// so I give the department object as an argument to the flatMap() function and obtain a new Stream with employees.
// How does these streams, for each department, combine in a single Stream that is the result of the flatMap() is not clear to me.
// What if I want to get the employees from only one department?, how do I do that?


        System.out.println("\n============  collect() method ===========");

        //collect() method – collect the elements from a Steam into a different type of result like a List.
        List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(Collectors.toList());

        for(String s : sortedGNumbers){
            System.out.println(s);
        }


    }



}
