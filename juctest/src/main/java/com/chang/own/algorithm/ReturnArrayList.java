package com.chang.own.algorithm;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author: changsh
 * @date: 2019/2/15 0015
 **/
public class ReturnArrayList {

    static class Node {
        private Integer data;
        private Node next;

        public int getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    static ArrayList<Integer> getResult(Node targetNode) {
        Stack<Integer> stack = new Stack<>();
        while (targetNode.getNext() != null) {
            stack.push(targetNode.getData());
            targetNode = targetNode.getNext();
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!stack.empty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        Node node = new Node();
        node.setData(0);

        Node node1 = new Node();
        node1.setData(1);
        Node node2 = new Node();
        node2.setData(2);
        Node node3 = new Node();
        node3.setData(3);
        Node node4 = new Node();
        node4.setData(4);
        Node node5 = new Node();
        node5.setData(5);

        node4.setNext(node5);
        node3.setNext(node4);
        node2.setNext(node3);
        node1.setNext(node2);
        node.setNext(node1);

        ArrayList<Integer> arrayList = getResult(node);

        for (Integer ii : arrayList) {
            System.out.println(ii);
            System.out.println("\n");
        }

    }

}
