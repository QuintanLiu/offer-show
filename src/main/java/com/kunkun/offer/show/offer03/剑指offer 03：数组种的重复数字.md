# 剑指offer 03：数组中的重复数字

## 问题

找出数组中重复的数字。

在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

### 示例1

> **输入**：[2, 3, 1, 0, 2, 5, 3]
>
> **输出：**2或者3

## 解法一

### 思路：排序+遍历

- 首先对数据排序，
- 排序完成后遍历数据，如果遇到相同的数据，立即返回。
- 时间复杂度O(nlogn),空间复杂度O(logn)

### 具体实现：java

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
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
}
```

## 解法二

### 思路： hash表

- 遍历数组，将数据作为hash表的key，个数作为value
- 如果发现有一个key已经存在直接返回数据
- 时间复杂度O(n),空间复杂度O(n)

### 具体实现：java

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
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
}
```

## 解法三

### 思路：冲突法

#### 分析：

可以知道所有数字都在0~n-1的范围内，那么我们可以知道：如果完全不冲突，必然0-n-1每个数一个，那么我们可以将数组下表作为key，必然有key是重复的。找到这个重复的key就好了

- 遍历数组，将数据放在对应的下标。如果下标冲突，则返回数据，如果不冲突，则与当前数据交换位置
- 比如：[2, 3, 1, 0, 2, 5, 3]
- 第一次：[1,3,2,0,2,5,3]
- 第二次：[3,1,2,0,2,5,3]
- 第三次：[0,1,2,3,2,5,3]
- 第四次，2冲突了，直接返回2
- 时间复杂度O(n),空间复杂度O(1)

```java
class Solution {
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
}
```



## 问题变更一

> 如果需要找出全部的相同的数字，上面的方法还有效吗？

- 全部可以实现（建议自行尝试）

## 问题变更二

> 在一个长度为n+1的数组里的所有数字都在1~n范围内，所以数组种至少有一个数字是重复的。请找出数组种任意一个重复的数字，**但不能修改输入的数组**。

## 解法一

### 思路：复制数组

- 首先将数组复制下来。
- 然后利用原问题种的任意一种解法

**代码参考以上**

### 解法二

### 思路：二分查找的思想

#### 分析：

我们把1~n的数字从中间的数字m分为两部分，前面一般为1~m，后面一般为m+1~n。如果1~m的数字的数目超过了m，那么这一版的区间里面一定包含重复的数字；如果两个区间都相等，那么将m的位置前移一位，再进行比较。以此为思想

- 比如数组 [1,2,2,4,5]
- 第一次比较：m为3； 1-3和4-5分别有3个和2个。无法辨别，移动m=2
- 第二次比较：m为2；1-2和3-5分别有3个和2个。区间1-2中一定有重复的数字
- 第三次比较：m为1；1为1次，2为2次。重复数字为2
- 时间复杂度O(nlogn),空间复杂度O(1)

```java
public int findRepeatNumber(int[] nums){
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
```

**参考：**[二分查找](https://www.cnblogs.com/kyoner/p/11080078.html)
