package com.chang.own;

/**
 * @author: changsh
 * @date: 2018/12/13 0013
 **/
public class MyTask1 extends Thread {

    private Integer a;

    public MyTask1(Integer a) {
        this.a = a;
    }

    @Override
    public void run() {
        Integer aa = 0;
        for (int i = 0; i < 10; i++) {
            a = a + aa;
            System.out.println(this.getName() + "-" + a);
        }
    }

}
