package com.andrei.lambdas;

import com.andrei.Employee;

import java.util.*;

public class MainLambdas {

    public static void main(String[] args) {

        // using a tread that print from a class that implement Runnable
        new Thread(new CodeToRun()).start();


        // using a tread that print from an anonymous class that implement Runnable
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printing from an anonymous class that implement Runnable");
            }
        }).start();

        // using a tread that use Lambda to print from an anonymous class that implement Runnable
        //how the compiler knows what to do when see lambda expression: Thread class constructor  accepts a runnable parameter.
        //Runnable interface has one method run() with no parameter
        new Thread(() -> System.out.println("Printing from the Runnable using Lambdas")).start();


        //printing more lines
        new Thread(() -> {
            System.out.println("Line 1");
            System.out.println("Line 2");
            System.out.format("Line %d\n", 3);
        }).start();

        //working with treads - so this separator line will not serve my scope to delimit what was till here to what will follow.
        System.out.println("************************");

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Teacher", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);


        // code with NO Lambda
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee c1, Employee c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });

        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }


        //code with Lambda - new Comparator is an anonymous class
        Collections.sort(employees, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }

        // return value - Version 1
        String sillyStringV1 = doStringStuff(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                return s1.toUpperCase() + s2.toUpperCase();
            }
        }, tim.getName(), snow.getName());
        System.out.println(sillyStringV1);

        //return value Version 2
        //lambda when the interface return a value
        //do not need to use return keyword when is only one line of code
        String sillyStringLambdaV2 = doStringStuff((s1, s2) -> s1.toUpperCase() + s2.toUpperCase(), employees.get(0).getName(), employees.get(1).getName());
        System.out.println(sillyStringLambdaV2);

        //return value Version 3
        //assign lambda to a  variable uc
        UpperConcat uc = ((s1, s2) -> s1.toUpperCase() + s2.toUpperCase());
        String sillyStringLambdaV3 = doStringStuff(uc, employees.get(2).getName(), employees.get(3).getName());
        System.out.println(sillyStringLambdaV3);

// lambda scope
        AnotherClass anotherClass = new AnotherClass();
        String s = anotherClass.doSomething();
        System.out.println(s);
        String sl = anotherClass.doSomethingWithLambda();
        String snb = anotherClass.doSomethingWithNestedBlock();

//Enhanced for loop: employee is effectively final because a new local variable is crated for each iteration of the loop
        for (Employee employee : employees) {
            System.out.println("===========Inside enhance for loop===============");
            System.out.println(employee.getName());

            new Thread(() -> System.out.println(employee.getAge())).start();
        }

// Simple loop
        //Employee is declared inside for loop so is effectively final,
        // If we declare employee outside for loop we get errors.
        //Employee employee; -> error
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            System.out.println("===========Inside simple for loop===============");
            System.out.println(employee.getName());

            new Thread(() -> System.out.println(employee.getAge())).start();
        }

        //Use lambda when iterate over a collection
        System.out.println("===========Iterate over a collection with lambda===============");
        employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });

    }


    //return a value
    public final static String doStringStuff(UpperConcat uc, String s1, String s2){
        return uc.upperAndConcat(s1, s2);
    }

}
