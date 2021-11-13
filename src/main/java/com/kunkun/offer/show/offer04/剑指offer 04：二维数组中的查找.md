# 剑指offer 04：二维数组中的查找

## 一、问题

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

### 示例1

> 现有矩阵 matrix 如下：
>
> [
>   [1,   4,  7, 11, 15],
>   [2,   5,  8, 12, 19],
>   [3,   6,  9, 16, 22],
>   [10, 13, 14, 17, 24],
>   [18, 21, 23, 26, 30]
> ]
>
> 给定 target = `5`，返回 `true`。
>
> 给定 target = `20`，返回 `false`。

## 二、解法

### 解法一

#### 思路：暴力法

这种方法未使用到条件左右有序和上下有序，但是这种方式比较通用。正常二维数组都可以查找

- 将数组完全遍历，找到target

- 时间复杂度O(n*m) 空间复杂度O(1)

#### 具体实现：java

```java
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
```

### 解法二

#### 思路：结合二分查找

- 首先，按行遍历每一行范围可以知道
- 如果target大于这一行的最小值和最大值
- 在行内进行二分查找
- **时间复杂度O(nlogm) 空间复杂度O(1)**

#### 具体实现：java

```java
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
```

### 解法三

#### 思路：二叉搜索数

#### 分析

> 因为数组是上下有序和左右有序的，那么可以知道，左上角的数是最小的，右下角的数是最大的。但是右上角和左下角这两个数比较特殊
>
> 右上角：右上角这个数，下面的数比他大，左边的数比他小。可知，很像二叉搜索树。左下角同理。

- 首先从右上角的数开始遍历。
- 如果target比这个数大，说明target如果存在，肯定在右上角这个数的下面。（因为左边的数都比target小，可以直接去除一行）
- 如果target比这个数小，说明target如果存在，肯定在右上角这个数的左面。（因为下面的数都比target大，可以直接去除一列）
- **时间复杂度O(n+m) 空间复杂度O(1)**

#### 具体实现：java

```java
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
```

