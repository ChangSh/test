package com.chang.own.algorithm;

import com.chang.own.common.GeneralNode;

/**
 * @author: changsh
 * @date: 2019/5/30 0030
 * <p>
 * 题目描述
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 * <p>
 * 解题思路
 * 一种方法是利用栈来实现；
 * 另外一种方法是利用三个指针把链表反转，关键是 r 指针保存断开的节点。
 **/

public class ListInversion {


    /**
     * 非递归
     *
     * @param node
     * @param <T>
     * @return
     */
    static <T> GeneralNode<T> reverse(GeneralNode<T> node) {

        if (node == null) {
            return null;
        }
        // 1 - 2 - 3 - 4 - 5
        GeneralNode<T> pre = node;
        GeneralNode<T> cur = node.getNextNode();
        GeneralNode<T> tmp;
        while (cur != null) {
            tmp = cur.getNextNode();
            cur.setNextNode(pre);
            pre = cur;
            cur = tmp;
        }
        node.setNextNode(null);
        return pre;
    }

    /**
     * 递归
     *
     * @param node
     * @param <T>
     * @return
     */
    static <T> GeneralNode<T> reverse1(GeneralNode<T> node) {
        if (node.getNextNode() == null) {
            return node;
        }
        GeneralNode<T> preHead = reverse1(node.getNextNode());
        node.getNextNode().setNextNode(node);
        node.setNextNode(null);
        return preHead;
    }

    static <T> void printNode(GeneralNode<T> node) {
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNextNode();
        }
    }


    public static void main(String[] args) {
        GeneralNode<Integer> node5 = new GeneralNode<>(5, null);
        GeneralNode<Integer> node4 = new GeneralNode<>(4, node5);
        GeneralNode<Integer> node3 = new GeneralNode<>(3, node4);
        GeneralNode<Integer> node2 = new GeneralNode<>(2, node3);
        GeneralNode<Integer> node = new GeneralNode<>(1, node2);

        printNode(node);

        node = reverse1(node);

        printNode(node);

    }

}
