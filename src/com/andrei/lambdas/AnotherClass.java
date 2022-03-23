package com.andrei.lambdas;


public class AnotherClass {
    public String doSomething(){

        System.out.println("The AnotherClass class's name is: " + getClass().getSimpleName());

        return MainLambdas.doStringStuff(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                System.out.println("The anonymous class's name from AnotherClass is (Should be nothing because does not have a name): " + getClass().getSimpleName());
                return s1.toUpperCase() + s2.toUpperCase();
            }
        }, "String1", "String2");
    }
//lambda replace an anonymous class but is not treated like an anonymous class but like a nested block of code
    public String doSomethingWithLambda(){

        System.out.println("The AnotherClass class's name is: " + getClass().getSimpleName());
        int i = 0;
        return MainLambdas.doStringStuff((s1, s2) -> {

                System.out.println("The anonymous class's name from AnotherClass, when using lambdas, is (Should be nothing because does not have a name): " + getClass().getSimpleName());

                System.out.println(i); // i will be printed if is final or effective final (if his value will be changed ex: i++, we will get an error)
            //System.out.println(i++); - this will not work
                return s1.toUpperCase() + s2.toUpperCase();


        }, "String1", "String2");
    }

    public String doSomethingWithNestedBlock(){

        System.out.println("The AnotherClass class's name is: " + getClass().getSimpleName());

        //the variable can be referred from the nested block because is defined in the enclosing block of the nested block - the metod body in this case

        //nested block of code
        int i = 0;
        {
            UpperConcat uc = new UpperConcat() {
                @Override
                public String upperAndConcat(String s1, String s2) {

                   // i++ - can't use here because i is not final
                    return s1.toUpperCase() + s1.toUpperCase();
                }
            };
            System.out.println("Inside nested block of code, class name is: " +getClass().getSimpleName());
            i++;
            System.out.println(" i = " + i);
            return MainLambdas.doStringStuff(uc,"String1", "String2");
        }
    }

}
