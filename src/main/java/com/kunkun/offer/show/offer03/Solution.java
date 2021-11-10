package com.kunkun.offer.show.offer03;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: liukun
 * @Date: 2021/11/10
 */
public class Solution {

    /**
     * 排序+遍历
     * @param nums
     * @return
     */
    public int findRepeatNumber1(int[] nums) {
        Arrays.sort(nums);
        int compare = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (compare == nums[i]) {
                return compare;
            } else {
                compare = nums[i];
            }
        }
        return -1;
    }

    /**
     * hash表法
     * @param nums
     * @return
     */
    public int findRepeatNumber2(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>(nums.length);
        for(int i = 0;i<nums.length;i++){
            if(map.get(nums[i])==null){
                map.put(nums[i],1);
            }
            else{
                return nums[i];
            }
        }
        return -1;
    }



    /**
     * 冲突法
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; ) {
            if (i==nums[i]){
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) {
                return nums[i];
            } else {
                int t = nums[i];
                nums[i] = nums[t];
                nums[t] = t;
            }
        }
        return -1;
    }

    /**
     * 问题变更：
     * 在一个长度为n+1的数组里的所有数字都在1~n范围内，所以数组种至少有一个数字是重复的。
     * 请找出数组种任意一个重复的数字，但不能修改输入的数组。
     */

    /**
     * 主要分析二分法
     * @param nums
     * @return
     */
    public int findRepeatNumber4(int[] nums){
        if (null == nums || nums.length == 0 || nums.length == 1) {
            return -1;
        }
        int left = 1;
        int right =  nums.length;
        int mid = left + (right - left) / 2;
        while (left <= right) {
            int preNums = mid - left + 1;
            int pre = countRange(nums ,left, mid);
            if (pre == preNums) {
                mid  = mid - 1;
                //边界值
                if (mid < left){
                    mid = left + (right - left) / 2;
                    left = mid + 1;
                    mid = left + (right - left) / 2;
                }
            } else if (pre < preNums) {
                if (left == right) {
                    return left;
                }
                left = mid + 1;
                mid = left + (right - left) / 2;
            } else {
                if (left == right) {
                    return left;
                }
                right = mid;
                mid = left + (right - left) / 2;
            }
        }
        return -1;
    }

    private int countRange(int[] nums, int start, int end){
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= start && nums[i] <= end) {
                count++;
            }
        }
        return count;
    }


}
