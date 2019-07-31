package com.chang.own.algorithm;

import java.util.Stack;

/**
 * @author: changsh
 * @date: 2019/2/18 0018
 **/

public class Stack2Queue {

    static class MyQueue {
        private Stack<Integer> pushStack = new Stack<>();

        private Stack<Integer> popStack = new Stack<>();


        public Integer spop() {
            if (pushStack.empty()) {
                return null;
            }
            while (!pushStack.empty()) {
                Integer num = pushStack.pop();
                popStack.push(num);
            }
            Integer result = popStack.pop();
            while (!popStack.empty()) {
                Integer num1 = popStack.pop();
                pushStack.push(num1);
            }
            return result;
        }

        public boolean spush(Integer num) {
            pushStack.push(num);
            return true;
        }

        public boolean empty() {
            if (pushStack.empty() && popStack.empty()) {
                return true;
            }
            return false;
        }
    }


    public static void main(String[] args) {

        MyQueue queue = new MyQueue();
        queue.spush(1);
        queue.spush(2);
        queue.spush(3);
        queue.spush(4);
        queue.spush(5);
        System.out.println(queue.spop());
        queue.spush(6);
        while (!queue.empty()) {
            System.out.println(queue.spop());
        }

    }
}
