# 剑指offer 07：重建二叉树

## 一、问题

输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

### 示例1：

> **输入：** preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
>
> **输出：**[3,9,20,null,null,15,7]

![](https://assets.leetcode.com/uploads/2021/02/19/tree.jpg)

### 示例2：

> **输入：**preorder = [-1], inorder = [-1]
>
> **输出**：[-1]

## 二、解法

### 解法一

#### 思路：递归

- 中序遍历的左子树都在根节点的左边，右子树都在根节点的右边
- 前序遍历的根节点都是数组的第一个
- 可以用递归来做，中序遍历根节点的左边数组为左子树的中序遍历，右边为右子树的中序遍历
- 从前序遍历中找出顺序找到左子树个数相同的节点作为左子树的前序遍历节点

#### 具体实现：java

```java
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder){
        if (preorder ==null) {
            return null;
        }
        return buildTree2(preorder,0,preorder.length, inorder, 0, inorder.length);
    }

    /**
     * 递归构建二叉树
     * @param preorder
     * @param preStart
     * @param preEnd
     * @param inorder
     * @param inStart
     * @param inEnd
     * @return
     */
    private TreeNode buildTree2(int[] preorder,int preStart,int preEnd, int[] inorder, int inStart, int inEnd){
        if (preorder == null || preEnd<=preStart) {
            return null;
        }
        TreeNode treeNode = new TreeNode(preorder[preStart]);
        int i = 0;
        int count = 0;
        for (i = inStart; i < inEnd; i++) {
            count++;
            if (preorder[preStart] == inorder[i]) {
                break;
            }
        }
        treeNode.left = buildTree2(preorder, preStart+1, preStart + count, inorder, inStart, i);
        treeNode.right = buildTree2(preorder,preStart+count, preEnd, inorder,i+1, inEnd);
        return treeNode;
    }
}
```

## 思考

怎么用中序和后序遍历重建一个二叉树？