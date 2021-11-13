# 剑指offer 05：字符串替换

## 一、问题

请实现一个函数，把字符串 `s` 中的每个空格替换成"%20"。

### 示例

> **输入**：s = "We are happy."
> **输出**："We%20are%20happy."

## 二、解法

### 解法一

#### 思路：调库

> 既然使用了JDK的库函数，最好还是去看一下源码怎么实现的。如下，可以看到其实JDK内部使用了正则表达式来替换全部的空格。正则虽然通用，但是在时间效率这一块必然没那么好，如果面试遇到这种问题，世界调库也不太好。

```java
public String replaceAll(String regex, String replacement) {
        return Pattern.compile(regex).matcher(this).replaceAll(replacement);
    }
```

- 直接调用jdk自带的replace方法

#### 具体实现：java

```java
public String replaceSpace(String s) {
        return s.replaceAll(" ","%20");
    }
```

### 解法二

#### 思路：使用额外空间

> 字符串在本质上就是一个字符数组，那么可以直接新建一个字符数组，遍历原有字符串的字符数组，如果遇到空格就新增三个字符%20。
>
> 当然使用StringBuilder也可以。

- 首先创建一个字符数组，是原字符串长度的三倍（防止数组长度不够）
- 遍历字符串，如果不是空格直接塞
- 如果是空格，塞三个字符%20
- **时间复杂度O(n) 空间复杂度O(n)**

#### 具体实现：java

```java
public String replaceSpace(String s) {
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
```

### 解法三

#### 思路：通用数组插入问题

> 这种思路是要求空间复杂度为O(1),但是在java中字符串是不可变的，所以我们只能将字符串换成一个足够长的字符数组。这个字符数组[0-length-1]是原本的字符串，后面全是\u0000。
>
> 显然，因为空格是一个字符，%20是三个字符。如果需要原地变更这个字符数组，就需要解决怎么插入字符的问题。因为数组的插入时间复杂度是O(N),每一个空格都变更的话，整体时间复杂度会变成O(n*n)，这显然是不能接受的。这边介绍一个从后向前遍历的方法，如果遇到数组的插入和删除可以优先考虑这种方法。

- 首先遍历字符串计算空格的个数count
- 然后计算变更后字符数组的长度，length+2*count
- 双指针，一个指针指向新数组长度-1，另一个指针指向length-1
- 从后向前遍历，如果遇到空格就向前塞三个字符，其他的都塞原本的字符。
- **时间复杂度O(n)  空间复杂度O(1)**

**说明：**下面的空间复杂度是O(n)，因为我没有用输入字符数组的方式，我直接字符串转了一下。**主要是思想**

#### 具体实现：java

``` java
public String replaceSpace(String s) {
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
```

## 相关问题

有两个排序的数组A1和A2，内存在A的末尾有足够多的空域空间容纳A2。请实现一个函数，把A2中的数字插入A1中，并且所有的数字都是有序的。要求：

空间复杂度为O(1)。

### 解法

#### 思路：通用数组的插入和删除

#### 具体实现：java

```java
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
```

