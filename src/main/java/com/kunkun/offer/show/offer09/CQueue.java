package com.kunkun.offer.show.offer09;

import java.util.Stack;

/**
 *
 * 两个栈实现队列
 * @author: liukun
 * @Date: 2021/11/28
 */
public class CQueue {
    private Stack<Integer> storeStack;

    private Stack<Integer> getStack;

    public CQueue() {
        storeStack = new Stack<>();
        getStack = new Stack<>();
    }

    public void appendTail(int value) {
        storeStack.push(value);
    }

    public int deleteHead() {
        if (!getStack.isEmpty()) {
            return getStack.pop();
        }
        while(!storeStack.isEmpty()){
            getStack.push(storeStack.pop());
        }
        if (getStack.isEmpty()){
            return -1;
        }
        return getStack.pop();
    }
}
