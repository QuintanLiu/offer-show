package com.kunkun.offer.show.offer07;


import com.kunkun.offer.show.common.TreeNode;

/**
 * @author: liukun
 * @Date: 2021/11/21
 */
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
