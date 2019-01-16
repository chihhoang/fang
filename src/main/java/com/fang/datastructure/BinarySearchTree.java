package com.fang.datastructure;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * A Simple Binary Search Tree Implementation without recursion
 * 
 */
public class BinarySearchTree {
    class Node {
        Node left, right;
        int data;
        public Node(int data) {
            this.data = data;
        }
    }

    Node root;

    public BinarySearchTree(int data) {
        root = new Node(data);
    }

    void insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return;
        }
        
        Node current = root;
        Node parent = current;

        while (true) {
            parent = current;
            if (value < current.data) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    boolean contains(int value) {
        Node current = root;
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

    void delete(int i) {
        // TODO: fairly complicated :)
    }

    void printInOrder(Node current) {
        if (current == null)
            return;

        printInOrder(current.left);
        System.out.print(current.data + " ");
        printInOrder(current.right);
    }

    
    void printInOrder() {
        printInOrder(root);
    }

    void inOrderWithoutRecursion() {
        if (root == null)
            return;

        Deque<Node> stack = new ArrayDeque<>();
        
        Node current = root;
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

    void printPreOrder(Node current) {
        if (current == null)
            return;

        System.out.print(current.data + " ");
        printPreOrder(current.left);
        printPreOrder(current.right);
    }
    
    void printPreOrder() {
        printPreOrder(root);
    }

    void printPostOrder(Node current) {
        if (current == null)
            return;
        
        printPostOrder(current.left);
        printPostOrder(current.right);
        System.out.print(current.data + " ");
    }

    void printPostOrder() {
        printPostOrder(root);
    }

    int maximum() {
        if (root == null)
            throw new RuntimeException("Root null");
        
        Node curr = root;
        Node last = curr;

        while (curr != null) {
            last = curr;
            curr = curr.right;
        }

        return last.data;
    }

    int minimum() {
        if (root == null)
            throw new RuntimeException("Root null");

        Node curr = root;
        Node last = curr;
        
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
        System.out.println();
        tree.inOrderWithoutRecursion(); // 10 40 50 70 80 90 
        System.out.println();
        tree.printPreOrder(); // 50 10 40 80 70 90 
        System.out.println();
        tree.printPostOrder(); // 40 10 70 90 80 50
        System.out.println();
        System.out.println(tree.contains(40)); // true
        System.out.println(tree.contains(60)); // false
        System.out.println(tree.maximum()); // 90
        System.out.println(tree.minimum()); // 10
    }
}
