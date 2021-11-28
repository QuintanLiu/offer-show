package com.kunkun.offer.show.offer09;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现栈
 *
 * @author: liukun
 * @Date: 2021/11/28
 */
public class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        if (!queue2.isEmpty()){
            queue2.add(x);
            return;
        }
        queue1.add(x);
    }

    public int pop() {
        if (queue2.isEmpty()) {
            while (queue1.size() > 1){
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        }

        while (queue2.size() > 1){
            queue1.add(queue2.poll());
        }
        return queue2.poll();
    }

    public int top() {
        if (queue2.isEmpty()) {
            while (queue1.size() > 1){
                queue2.add(queue1.poll());
                if(queue1.size() == 1) {
                    queue2.add(queue1.peek());
                    return queue1.poll();
                }
            }
            return queue1.peek();
        }

        while (queue2.size() > 1){
            queue1.add(queue2.poll());
            if(queue2.size() == 1) {
                queue1.add(queue2.peek());
                return queue2.poll();
            }
        }
        return queue2.peek();
    }

    public boolean empty() {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            return true;
        }
        return false;
    }
}
