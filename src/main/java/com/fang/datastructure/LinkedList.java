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

    public void append(int value) {
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

    public void prepend(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    public void delete(int value) {
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
    public void reverse() {
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

    // O(n) time, O(1) space
    public void reverse2() {
        if (head == null || head.next == null)
            return;
        
        // newHead 1 2 null
        Node newHead = null;
        Node curr = head;
        Node tempNext = null;
        
        while (curr != null) {
            tempNext = curr.next;
            curr.next = newHead;
            newHead = curr;
            curr = tempNext;
        }
        head = newHead;
    }

    public void display() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public void swapInPairs() {
        if (head == null || head.next == null) return;

        Node dummy = new Node(0);
        dummy.next = head;

        Node curr = dummy;

        while (curr.next != null && curr.next.next != null) {
            Node first = curr.next;
            Node second = curr.next.next;
            first.next = second.next;
            curr.next = second; // link
            curr.next.next = first; // link
            curr = curr.next.next;
        }
        head = dummy.next;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.prepend(1);
        list.prepend(2);
        list.prepend(3);
        list.append(4);
        
        list.display(); // 3 2 1 4 
        list.append(5);
        list.append(6);
        list.swapInPairs();
        list.display(); // 2 3 4 1 6 5
        list.delete(4);
        list.display(); // 2 3 1 6 5

        list.reverse2();
        list.display(); // 5 6 1 3 2
    }
}
