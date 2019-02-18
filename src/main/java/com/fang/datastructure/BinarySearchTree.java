package com.fang.datastructure;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * A Simple Binary Search Tree Implementation without recursion
 * 
 */
public class BinarySearchTree {
    class TreeNode {
        TreeNode left, right;
        int data;
        public TreeNode(int data) {
            this.data = data;
        }
    }

    TreeNode root;

    public BinarySearchTree(int data) {
        root = new TreeNode(data);
    }

    void insert(int value) {
        TreeNode newTreeNode = new TreeNode(value);
        if (root == null) {
            root = newTreeNode;
            return;
        }
        
        TreeNode current = root;
        TreeNode parent = current;

        while (true) {
            parent = current;
            if (value < current.data) {
                current = current.left;
                if (current == null) {
                    parent.left = newTreeNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newTreeNode;
                    return;
                }
            }
        }
    }

    boolean contains(int value) {
        TreeNode current = root;
        while (current != null) {
            if (value == current.data)
                return true;
            
            if (value < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        
        if (key < root.data) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.data) {
            root.right = deleteNode(root.right, key);
        } else { // node to delete
            if (root.left == null && root.right == null) { // leaf
                root = null;
            } else if (root.left == null) { // left child null, replace
                root = root.right;
            } else if (root.right == null) { // right child null, replace
                root = root.left;
            } else { // has both left and right children
                // find the inorder successor (go left most of the right child)
                TreeNode curr = root.right;
                while (curr != null && curr.left != null) {
                    curr = curr.left;
                }
                // replace root with the inorder successor data
                root.data = curr.data;
                
                // delete the inorder successor, guarantee no left child
                root.right = deleteNode(root.right, curr.data);
            }
        }
        
        return root;
        
    }

    void printInOrder(TreeNode current) {
        if (current == null)
            return;

        printInOrder(current.left);
        System.out.print(current.data + " ");
        printInOrder(current.right);
    }

    
    void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    void inOrderWithoutRecursion() {
        if (root == null)
            return;

        Deque<TreeNode> stack = new ArrayDeque<>();
        
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) { // each level
            while (current != null) { // push to the smallest on left
                stack.push(current);
                current = current.left;
            }
            current = stack.pop(); // traverse back
            System.out.print(current.data + " "); // print
            current = current.right; // check right
        }
    }

    void printPreOrder(TreeNode current) {
        if (current == null)
            return;

        System.out.print(current.data + " ");
        printPreOrder(current.left);
        printPreOrder(current.right);
    }
    
    void printPreOrder() {
        printPreOrder(root);
        System.out.println();
    }

    void printPostOrder(TreeNode current) {
        if (current == null)
            return;
        
        printPostOrder(current.left);
        printPostOrder(current.right);
        System.out.print(current.data + " ");
    }

    void printPostOrder() {
        printPostOrder(root);
        System.out.println();
    }

    int maximum() {
        if (root == null)
            throw new RuntimeException("Root null");
        
        TreeNode curr = root;
        TreeNode last = curr;

        while (curr != null) {
            last = curr;
            curr = curr.right;
        }

        return last.data;
    }

    int minimum() {
        if (root == null)
            throw new RuntimeException("Root null");

        TreeNode curr = root;
        TreeNode last = curr;
        
        while (curr != null) {
            last = curr;
            curr = curr.left;
        }
        
        return last.data;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree(50);
        tree.insert(10);
        tree.insert(80);
        tree.insert(70);
        tree.insert(40);
        tree.insert(90);
        /*
                  50
            10          80
              40      70  90
        */

        tree.printInOrder(); // 10 40 50 70 80 90 
        tree.inOrderWithoutRecursion(); // 10 40 50 70 80 90 
        tree.printPreOrder(); // 50 10 40 80 70 90 
        tree.printPostOrder(); // 40 10 70 90 80 50
        System.out.println(tree.contains(40)); // true
        System.out.println(tree.contains(60)); // false
        System.out.println(tree.maximum()); // 90
        System.out.println(tree.minimum()); // 10
        tree.deleteNode(tree.root, 50);
        tree.printInOrder(); // 10 40 70 80 90 
        tree.printPreOrder(); // 70 10 40 80 90 
        tree.printPostOrder(); // 40 10 90 80 70
    }
}
