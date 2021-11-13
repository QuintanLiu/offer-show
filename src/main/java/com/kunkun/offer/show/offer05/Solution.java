package com.kunkun.offer.show.offer05;

import java.util.Arrays;

/**
 * @author: liukun
 * @Date: 2021/11/13
 */
public class Solution {

    /**
     * 直接调库
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        return s.replaceAll(" ","%20");
    }

    /**
     * 使用额外空间的方法（String 的底层就是字符数组）
     * 使用字符数组来实现
     *
     * 时间复杂度：O(n) 空间辅助度O(n)
     * @param s
     * @return
     */
    public String replaceSpace2(String s) {
        if (null == s) {
            return null;
        }
        char[] result = new char[s.length() * 3];
        int size = 0;
        for (int i = 0; i < s.length(); i++) {

            if (' ' == s.charAt(i)) {
                result[size++] = '%';
                result[size++] = '2';
                result[size++] = '0';
            } else {
                result[size++] = s.charAt(i);
            }
        }
        return new String(result, 0, size);
    }

    /**
     * 从后向前遍历的思想
     *
     * 如果发现有数组插入和数组删除操作的。可以优先考虑的思想
     * @param s
     * @return
     */
    public String replaceSpace3(String s) {
        if (null == s) {
            return null;
        }
        //将字符复制到数组同时统计字符 ‘ ’的个数
        int count = 0;
        char[] result = new char[s.length() * 3];
        for (int i = 0; i < s.length(); i++) {
            result[i] = s.charAt(i);
            if (result[i] == ' ') {
                count ++;
            }
        }
        //计算新数组的长度
        int length = s.length() + (count << 1);
        for (int i = length - 1, j = s.length() - 1; i >= 0 && j >= 0; j--) {
            if (result[j] == ' ') {
                result[i--] = '0';
                result[i--] = '2';
                result[i--] = '%';
            } else {
                result[i--] = result[j];
            }
        }
        return new String(result,0, length);
    }

    /**
     * 有两个排序的数组A1和A2，内存在A的末尾有足够多的空域空间容纳A2。请实现一个函数，把A2中的数字插入A1中，并且所有的数字都是有序的。
     * 要求：
     * 空间复杂度为O(1)。
     * @param a1
     * @param a2
     */
    public static int[] mergeSortArray(int[] a1, int a1Length, int[] a2){
        if (null == a1 || a1Length == 0 || null == a2 || a2.length == 0) {
            return a1;
        }
        int length = a1Length + a2.length;
        int i = a1Length - 1;
        int j = length - 1;
        int k = a2.length - 1;
        while (i >= 0 && j >= 0 && k >= 0) {
            if (a1[i] >= a2[k]) {
                a1[j--] = a1[i--];
            } else {
                a1[j--] = a2[k--];
            }
        }
        if (k >= 0) {
            for (int l = 0; l <= k; l++) {
                a1[j--] = a2[k--];
            }
        }
        return a1;
    }

    public static void main(String[] args) {
        int[] a1 = {1,2,3,4,5,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] a2 = {4,5,7,9,11,13};

        System.out.println(Arrays.toString(mergeSortArray(a1,5 ,a2)));
    }

}
