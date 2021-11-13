package com.kunkun.offer.show.offer04;

/**
 * @author: liukun
 * @Date: 2021/11/13
 */
public class Solution {

    /**
     * 暴力法解
     *  用这种方式就用不到上下有序和左右有序两个条件了。显然不是好的方式
     * 时间复杂度O(n*m) 空间复杂度O(1)
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(null == matrix||matrix.length==0){
            return false;
        }
        for(int i = 0;i<matrix[0].length;i++){
            for(int j = 0;j<matrix.length;j++){
                if(matrix[j][i] == target){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 在有效行内进行二分查找
     *
     *  时间复杂度O(nlogm) 空间复杂度O(1)
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        if(null == matrix||matrix.length==0){
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i].length == 0){
                return false;
            }
            if (matrix[i][0] <= target && matrix[i][matrix[i].length - 1] >= target) {
                if (query(matrix[i], target)) {
                    return true;
                }
                continue;
            }
        }
        return false;
    }

    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public boolean query (int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return false;
    }


    /**
     * 二叉搜索树的算法
     *
     *
     * 时间复杂度O(n+m) 空间复杂度O(1)
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray3(int[][] matrix, int target) {
        if(null == matrix||matrix.length==0){
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0){
            return false;
        }

        for (int i = 0,j = matrix[0].length - 1; i < matrix.length && j >= 0;) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }


}
