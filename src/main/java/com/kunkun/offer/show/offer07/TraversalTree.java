package com.kunkun.offer.show.offer07;

import com.kunkun.offer.show.common.TreeNode;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * 二叉树的先序、中序和后续遍历
 *
 * 两种实现方式
 *
 * @author: liukun
 * @Date: 2021/11/21
 */
public class TraversalTree {

    /**
     * 先序遍历:根左右
     *
     * 递归
     * @param root
     */
    public void preTraversal(TreeNode root){
        //递归出口
        if(null == root) {
            return;
        }
        System.out.println(root.val);
        preTraversal(root.left);
        preTraversal(root.right);
    }

    /**
     * 先序遍历
     *
     * 栈
     * @param root
     */
    public void preTraversal2(TreeNode root){
        //递归出口
        if(null == root) {
            return;
        }
        //初始化栈
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        //如果p不为空，栈非空。进入循环
        while (null != p || !stack.isEmpty()){
            if (p != null) {
                System.out.println(p.val);
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                p = p.right;
            }
        }
    }

    /**
     * 中序便利的递归实现：左根右
     *
     * @param root
     */
    public void inTraversal(TreeNode root) {
        if (null == root) {
            return;
        }
        inTraversal(root.left);
        System.out.println(root.val);
        inTraversal(root.right);
    }

    /**
     * 中序遍历的栈实现
     * @param root
     */
    public void inTraversal2(TreeNode root) {
        if (null == root) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        //循环条件
        while(null != p || !stack.isEmpty()){
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                System.out.println(p.val);
                p = p.right;
            }
        }
    }

    /**
     * 二叉树后序便利的递归实现：左右根
     *
     * @param root
     */
    public void postorderTraversal(TreeNode root){
        if (null == root) {
            return;
        }
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.println(root.val);
    }

    /**
     * 后序遍历栈实现:
     *
     * 失去遍历算法核心的实现方法
     * @param root
     */
    public void postorderTraversal2(TreeNode root){
        if (null == root) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        TreeNode p = root;
        while(null != p || !stack.isEmpty()) {
            if (null != p) {
                list.add(p.val);
                stack.push(p);
                p = p.right;
            }else {
                 p = stack.pop();
                 p = p.left;
            }
        }
        Collections.reverse(list);
    }

    /**
     * 真正的后序遍历的栈实现
     *
     * 遍历算法的核心是内存的读取顺序
     *
     * 要熟练掌握这种方式
     *
     * @param root
     */
    public void postorderTraversal3(TreeNode root){
        if (null == root) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        TreeNode prev = null;
        List<Integer> result = new LinkedList<>();
        while (null != p || !stack.isEmpty()) {
            while(p != null){
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            if (null == p.right || prev == p.right) {
                result.add(p.val);
                prev = p;
                p = null;
            } else {
                stack.push(p);
                p = p.right;
            }
        }
    }

    /**
     * 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> levelOrder(TreeNode root) {
        if (null == root) {
            return new LinkedList<>();
        }
        List<TreeNode> queue = new LinkedList<>();
        TreeNode p = root;
        queue.add(p);
        List<Integer> result = new LinkedList<>();
        while(queue.size() != 0){
            p = queue.remove(0);
            result.add(p.val);
            if (p.left != null) {
                queue.add(p.left);
            }
            if (p.right != null) {
                queue.add(p.right);
            }
        }
        return result;
    }

    /**
     * 层序遍历的另一种返回值
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (null == root) {
            return new LinkedList<>();
        }
        List<TreeNode> queue = new LinkedList<>();
        TreeNode p = root;
        queue.add(p);
        List<List<Integer>> result = new LinkedList<>();
        while(queue.size() != 0){
            int count = queue.size();
            List<Integer> list = new LinkedList<>();
            while (count > 0) {
                p = queue.remove(0);
                list.add(p.val);
                if (p.left != null) {
                    queue.add(p.left);
                }
                if (p.right != null) {
                    queue.add(p.right);
                }
                count--;
            }
            result.add(list);
        }
        return result;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(Arrays.toString(levelOrder(root).toArray()));

    }
}
