package com.chang.own.algorithm;

import com.chang.own.common.GeneralNode;

/**
 * @author: changsh
 * @date: 2019/6/6 0006
 **/
public class Review {


    static <T> GeneralNode<T> reverse(GeneralNode<T> node){
        if (node == null) {
            return null;
        }
        GeneralNode<T> pre = node;
        GeneralNode<T> cur = node.getNextNode();
        GeneralNode<T> tmp;
        //1 -2 -3 -4 -5
        while (cur != null) {
            tmp = cur.getNextNode();
            cur.setNextNode(pre);
            pre = cur;
            cur = tmp;
        }
        node.setNextNode(null);
        return pre;
    }


    static <T> GeneralNode<T> reverse1(GeneralNode<T> node){
        if (node.getNextNode() == null) {
            return node;
        }
        GeneralNode<T> preHead = reverse1(node.getNextNode());
        node.getNextNode().setNextNode(node);
        node.setNextNode(null);
        return preHead;
    }

    public static void main(String[] args) {


        GeneralNode<Integer> node5 = new GeneralNode<>(5, null);
        GeneralNode<Integer> node4 = new GeneralNode<>(4, node5);
        GeneralNode<Integer> node3 = new GeneralNode<>(3, node4);
        GeneralNode<Integer> node2 = new GeneralNode<>(2, node3);
        GeneralNode<Integer> node = new GeneralNode<>(1, node2);

        node = reverse(node);

        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNextNode();
        }
    }
}
