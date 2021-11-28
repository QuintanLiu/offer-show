# 剑指offer 08：二叉树的下一个节点

## 一、问题

给定一个二叉树和其中的一个节点，如何找出中序遍历序列的下一个节点？树中的节点除了有两个分别指向左右子节点的指针，还有一个指向父节点的指针。

### 示例

> **输入：** inorder = [9,3,15,20,7]， node = 9
>
> **输出：** 20

![](https://assets.leetcode.com/uploads/2021/02/19/tree.jpg)

## 二、解法

### 解法一

#### 思路：中序遍历的栈实现

- 首先通过遍历当前节点的父节点找出根节点
- 然后中序遍历找出node节点的下一个节点

#### 具体实现：java

```java
/**
     * 中序遍历的下一个节点
     * @param nodeFa
     * @return
     */
    public static TreeNodeFa nextNode1(TreeNodeFa nodeFa){
        TreeNodeFa p = nodeFa;
        //找到根节点
        while(p.father != null){
            p = p.father;
        }
        //中序遍历，找到下一个节点返回
        Stack<TreeNodeFa> stack = new Stack<>();
        TreeNodeFa pre = null;
        while(p != null || !stack.isEmpty()){
            if (p != null){
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                if (pre != null){
                    return p;
                }
                if (p == nodeFa){
                    pre = p;
                }
                p = p.right;
            }
        }
        return null;
    }
```

### 解法二

#### 思路：分情况讨论

- 情况一：如果当前节点有右子树，那么中序遍历的下一个节点就是右子树的最左节点
- 情况二：如果当前节点无右子树，并且当前节点是其父节点的左子树，那么中序遍历的下一个节点就是父节点。
- 情况三：如果当前节点无右子树，并且当前节点是其父节点的右子树，那么找到其第一个祖先节点P（P是其父节点的左子树）。那么P的父节点就是其下一个节点。

#### 具体实现：java

```java
/**
     * 中序遍历的下一个节点
     * @param nodeFa
     * @return
     */
    public static TreeNodeFa nextNode(TreeNodeFa nodeFa){
        if (null == nodeFa) {
            return null;
        }
        //如果存在右节点
        if (nodeFa.right != null) {
            TreeNodeFa p = nodeFa.right;
            while (p.left != null){
                p = p.left;
            }
            return p;
        }
        //如果右节点为null
        TreeNodeFa p = nodeFa.father;
        //如果是他父节点的左节点
        if (p.left == nodeFa){
            return p;
        } else {
            //找到祖先节点是其父节点左子树的节点
            while(p.father != null) {
                if (p.father.left == p){
                    break;
                }
                p = p.father;
            }
            if (p.father != null) {
                p = p.father;
                return p;
            }
        }
        return null;
    }
```

## 三、思考

如果是前序和后续遍历的下一个节点呢？怎么实现？