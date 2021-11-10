package com.kunkun.offer.show.offer03;

/**
 * @author: liukun
 * @Date: 2021/11/10
 */
public class BinarySearch {
    /**
     * 二分查找：
     * 不考虑查出左右边界的情况.例如：[1,2,2,2,3,4]，target=2,
     * 返回 2
     * 搜索区间[left,right]
     * @return
     */
    public static int binarySearch (int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 二分查找：
     * 搜索出最左边界的target.例如：[1,2,2,2,3,4]，target=2,
     * 返回 1
     * 搜索区间[left,right)
     * @return 表示有几个数小于target
     */
    public static int binarySearch2 (int[] nums, int target){
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return left;
    }


    /**
     * 二分查找：
     * 搜索出最右边界的target.例如：[1,2,2,2,3,4]，target=2,
     * 返回 4
     * 搜索区间[left,right)
     * @return 表示有几个数小于等于 target
     */
    public static int binarySearch3 (int[] nums, int target){
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,2,3,4};
        System.out.println(binarySearch(nums, 2));
        System.out.println(binarySearch2(nums, 5));
        System.out.println(binarySearch3(nums, 5));

    }
}
