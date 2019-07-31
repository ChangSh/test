package com.chang.own;

/**
 * @author: changsh
 * @date: 2018/12/19 0019
 **/
public class JMMTest {

    private static void recursion(String a) {
        if (!"".equals(a)) {
            recursion("a");
        }
    }

    public static void main(String[] args) {
        recursion("1");
    }
}
