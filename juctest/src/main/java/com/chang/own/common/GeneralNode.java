package com.chang.own.common;

/**
 * @author: changsh
 * @date: 2019/5/30 0030
 **/
public class GeneralNode<T> {

    T value;

    GeneralNode<T> nextNode;


    public GeneralNode(T value, GeneralNode<T> nextNode) {
        this.value = value;
        this.nextNode = nextNode;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public GeneralNode<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(GeneralNode<T> nextNode) {
        this.nextNode = nextNode;
    }
}
