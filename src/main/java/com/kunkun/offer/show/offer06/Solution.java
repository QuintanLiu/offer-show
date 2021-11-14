package com.kunkun.offer.show.offer06;

import com.kunkun.offer.show.common.ListNode;

import java.util.Stack;

/**
 * @author: liukun
 * @Date: 2021/11/14
 */
public class Solution {

    /**
     * 反转链表，然后遍历
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        if (null == head) {
            return new int[]{};
        }
        //反转链表
        ListNode pNext = head.next;
        head.next = null;
        int count = 1;
        while (pNext != null) {
            ListNode t = pNext.next;
            pNext.next = head;
            head = pNext;
            pNext = t;
            count++;
        }
        //遍历链表
        int[] result = new int[count];
        int i = 0;
        while(head != null) {
            result[i++] = head.val;
            head = head.next;
        }
        return result;
    }

    /**
     * 栈
     * @param head
     * @return
     */
    public int[] reversePrint1(ListNode head) {
        ListNode p = head;
        Stack<Integer> stack = new Stack<>();
        while(p != null) {
            stack.push(p.val);
            p = p.next;
        }
        int[] result = new int[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.pop();
        }
        return result;
    }

    /**
     * 递归
     * @param head
     * @return
     */
    public int[] reversePrint2(ListNode head) {
        if (head == null){
            return new int[]{};
        }
        int[] result = new int[]{head.val};
        return merge(reversePrint2(head.next), result);
    }

    private int[] merge(int[] a, int b[]){
        if (null == a||a.length == 0) {
            return b;
        }
        if (null == b || b.length == 0){
            return a;
        }
        int[] result = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i];
        }

        for (int i = 0; i < b.length; i++) {
            result[a.length + i] = b[i];
        }
        return result;
    }

}
