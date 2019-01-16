package com.fang.datastructure;

/*
    A Simple Implementation of Binary Tree
*/
public class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
    }

    void insert(int value) {
        if (value < data) {
            if (left == null) {
                left = new TreeNode(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new TreeNode(value);
            } else {
                right.insert(value);
            }
        }
    }

    boolean contains(int value) {
        if (value == data)
            return true;

        if (value < data) {
            if (left == null)
                return false;
            return left.contains(value);
        } else {
            if (right == null)
                return false;
            return right.contains(value);
        }
    }

    void printInOrder() {
        if (left != null)
            left.printInOrder();
        System.out.print(data + " ");
        if (right != null) {
            right.printInOrder();
        }
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(50);
        tree.insert(10);
        tree.insert(60);
        tree.insert(80);
        tree.insert(40);

        tree.printInOrder(); // 10 40 50 60 80
        System.out.println();
        System.out.println(tree.contains(80)); // true
        System.out.println(tree.contains(20)); // false
    }
}
