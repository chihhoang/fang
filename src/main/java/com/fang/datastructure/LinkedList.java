package com.fang.datastructure;

/**
 * Linked List Implementation
 */
public class LinkedList {
    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    Node head;

    void append(int value) {
        if (head == null) {
            head = new Node(value);
            return;
        }

        Node curr = head;
        Node newNode = new Node(value);

        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = newNode;
    }

    void prepend(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    void delete(int value) {
        if (head == null) return;
        if (head.data == value) {
            head = head.next;
            return;
        }

        Node curr = head;
        while (curr.next != null) {
            if (curr.next.data == value) {
                curr.next = curr.next.next;
                return;
            }
            curr = curr.next;
        }
    }

    // O(n) space, not in place
    void reverse() {
        if (head == null || head.next == null) {
            return;
        }

        LinkedList newList = new LinkedList();
        Node curr = head;
        while (curr != null) {
            newList.prepend(curr.data);
            curr = curr.next;
        }

        head = newList.head;
    }

    void display() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.prepend(1);
        list.prepend(2);
        list.prepend(3);
        list.append(4);

        list.display(); // 3 2 1 4 
        list.delete(4);
        list.display(); // 3 2 1 

        list.reverse();
        list.display(); // 1 2 3 
    }
}
