package com.chang.own.algorithm;

/**
 * @author: changsh
 * @date: 2019/2/19 0019
 **/
public class Fibonacci {

    public static Integer fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(6));
        System.out.println(fibonacci(7));
    }
}
