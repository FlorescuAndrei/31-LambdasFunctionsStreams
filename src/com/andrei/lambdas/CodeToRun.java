package com.andrei.lambdas;

public class CodeToRun implements Runnable{
    @Override
    public void run() {
        System.out.println("Printing from a class that implement  Runnable");
    }
}
