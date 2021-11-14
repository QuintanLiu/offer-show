# 剑指offer 06：从尾到头打印链表

## 一、问题

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

### 示例

> **输入**：head = [1,3,2]
>
> **输出**：[2,3,1]

## 二、解法

### 解法一

#### 思路：反转链表

- 首先将链表反转
- 然后遍历反转后的链表
- **时间复杂度：O(n) 空间O(n)**

#### 具体实现：java

```java
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
```

### 解法二

#### 思路：栈

> 分析可知，链表需要反转输出，但是链表只能顺序遍历。很符合栈的数据结构（后进先出）

- 首先遍历链表，将数据压栈
- 然后遍历栈，将数据出栈打印
- **时间复杂度:O(n) 空间复杂度O(n)**

#### 具体实现：java

```java
public int[] reversePrint(ListNode head) {
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

```

### 解法三

#### 思路：递归

> 递归其实就是个栈结构

- 递归出口链表为null
- 递归体：打印当前的数值。但是这题返回的是个数组，所以要进行数据组装

#### 具体实现：java

```java
public int[] reversePrint(ListNode head) {
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
```

## 三、总结

**这题需要了解的是：如果需要递归的问题的一种解法，用栈来代替递归。**

