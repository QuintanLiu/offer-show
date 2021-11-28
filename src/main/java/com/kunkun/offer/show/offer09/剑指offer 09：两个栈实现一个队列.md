# 两个栈实现一个队列

## 一、问题

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

### 示例

> **输入：**
>
> ["CQueue","appendTail","deleteHead","deleteHead"]     
>
> [[],[3],[],[]]
> **输出：**
>
> [null,null,3,-1]

> **输入：**
> ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
> [[],[],[5],[2],[],[]]
> **输出：**
>
> [null,-1,null,null,5,2]

## 二、解法

#### 思路：一个栈存储，一个栈获取

- 初始化两个栈
- 入队时，全部放入存储栈
- 出队时，如果获取栈不为空，直接获取，如果获取栈是空的，把存储栈的所有数据放入获取栈。再去取获取栈的数据

```java
public class CQueue {
    private Stack<Integer> storeStack;

    private Stack<Integer> getStack;

    public CQueue() {
        storeStack = new Stack<>();
        getStack = new Stack<>();
    }

    public void appendTail(int value) {
        storeStack.push(value);
    }

    public int deleteHead() {
        if (!getStack.isEmpty()) {
            return getStack.pop();
        }
        while(!storeStack.isEmpty()){
            getStack.push(storeStack.pop());
        }
        if (getStack.isEmpty()){
            return -1;
        }
        return getStack.pop();
    }
}
```

## 三、相关问题

### 两个队列实现栈

请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。

实现 MyStack 类：

- void push(int x) 将元素 x 压入栈顶。
- int pop() 移除并返回栈顶元素。
- int top() 返回栈顶元素。
- boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。

### 示例

> **输入：**
> ["MyStack", "push", "push", "top", "pop", "empty"]
> [[], [1], [2], [], [], []]
> **输出：**
> [null, null, null, 2, 2, false]
>
> 解释：
> MyStack myStack = new MyStack();
> myStack.push(1);
> myStack.push(2);
> myStack.top(); // 返回 2
> myStack.pop(); // 返回 2
> myStack.empty(); // 返回 False

#### 实现：java

```java
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
```

