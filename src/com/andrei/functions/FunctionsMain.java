package com.andrei.functions;

import com.andrei.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class FunctionsMain {

    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Teacher", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("Red RidingHood", 35);
        Employee charming = new Employee("Prince Charming", 31);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);

        System.out.println("111111111  CONSUMERS  11111111111");

//Consumer - accept arguments but does not return a value
        // functional method: accept()

        //consumer does not return any value. print the string and return nothing.
        Consumer<String> c = s -> System.out.println(s);
        c.accept("Hello, World!");

        // we can not use s.toUpperCase(). because no string will be returned
        Consumer<String> c2 = s -> s.toUpperCase();
        //System.out.println(c2.accept("hello")); - does not work because consumer return null

        //forEach() method call accept method() from Consumer Interface - that is why we do not see accept()method on the code
        //we pass to forEach method a lambda expression that meets the requirements of a consumer interface
        //consumer accepts one argument and perform an action that doesn't return a value
        //consumer = object in (employee), nothing out (name and age is printed to the console and nothing is returned)
        employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });



        System.out.println("Employee over 30:");
        for(Employee employee : employees){
            if(employee.getAge() > 30){
                System.out.println(employee.getName());
            }
        }

        employees.forEach(employee -> {
            if(employee.getAge()>30){
                System.out.println(employee.getName());
            }
        });

        System.out.println("\n Employees 30 and younger: ");
        employees.forEach(employee -> {
            if(employee.getAge() <= 30){
                System.out.println(employee.getName() );
            }
        });


        System.out.println("\n222222222222222  PREDICATES  22222222222222");
//Predicate - accept arguments and return true or false
        // functional method: test()
        //use Predicate Interface to print employee over 30 or employee = or less than 30
        //a predicate return a boolean value, we can replace the if condition with a predicate.
        printEmployeesByAge(employees, "Employees over 30", employee -> employee.getAge()>30);
        printEmployeesByAge(employees, "\n Employees 30 or under: ", employee -> employee.getAge() <= 30);
        //use anonymous class instead of lambda
        printEmployeesByAge(employees, "Employees younger than 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 25;
            }
        });

        IntPredicate intPredicate = i -> i<15;
        System.out.println(intPredicate.test(10));

        System.out.println("\n3333333333  SUPPLIERS  333333333333333");

 //Suppliers - no arguments but return a value
        // functional method: get()
        System.out.println("Random 10 integer");
        Random random = new Random();
        for(int i=0; i<10; i++){
            System.out.println(random.nextInt(1000));
        }


        System.out.println("\nRandom 10 integers with supplier");
        //use supplier
        //take no argument and return a value
        Supplier<Integer> supplier = () -> random.nextInt(1000);
        for(int i=0; i<10; i++){
            System.out.println(supplier.get());
        }

        System.out.println("\n4444444444444 Functions 444444444444444");
//Functions - accept arguments and return a value
        // functional method: apply()
        // in this example accept a employee and return a string

        //no function
        employees.forEach(employee -> {
            String lastName = employee.getName().substring(employee.getName().indexOf(' ') + 1);
            System.out.println("Employee last name is: " + lastName);
        });

        System.out.println("=======================");

       //with function
        //generics <argument type, return type>
        Function<Employee, String> getLastName = (employee -> {
            return employee.getName().substring(employee.getName().indexOf(' ') + 1);
        });

        String lastName = getLastName.apply(employees.get(2));
        System.out.println("Last name with function: " + lastName);

        Function<Employee, String > getFirstName = (employee -> {
           return employee.getName().substring(0, employee.getName().indexOf(' '));
        });

        System.out.println("====================");
        Random random1 = new Random();
        for(Employee employee : employees){
            if(random1.nextBoolean()){
                System.out.println(getAName(getFirstName, employee));
            }else {
                System.out.println(getAName(getLastName, employee));
            }
        }



    }
    //method use for predicate
    private static void printEmployeesByAge(List<Employee> employees, String ageText, Predicate<Employee> ageCondition){
        //we can not use forEach() because it accepts a consumer, so we will use an enhanced loop
        System.out.println(ageText);
        for(Employee employee : employees){
            if(ageCondition.test(employee)){
                System.out.println(employee.getName());
            }
        }

    }

    //method use for functions
    private static String getAName(Function<Employee, String> getName, Employee employee){
        return getName.apply(employee);
    }


}
