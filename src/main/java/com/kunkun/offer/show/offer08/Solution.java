package com.kunkun.offer.show.offer08;

import com.kunkun.offer.show.common.TreeNodeFa;

import java.util.Stack;

/**
 * @author: liukun
 * @Date: 2021/11/28
 */
public class Solution {

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

    public static void main(String[] args) {
        TreeNodeFa root = new TreeNodeFa(1);
        root.left = new TreeNodeFa(2);
        root.left.father = root;
        root.left.right = new TreeNodeFa(3);
        root.left.right.father = root.left;
        System.out.println(nextNode(root.left.right).val);
    }

}
