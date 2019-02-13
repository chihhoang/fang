package com.fang.datastructure;

/**
 * Linked List Implementation
 */
public class LinkedList {
    class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }

    ListNode head;

    public void append(int value) {
        if (head == null) {
            head = new ListNode(value);
            return;
        }

        ListNode curr = head;
        ListNode newNode = new ListNode(value);

        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = newNode;
    }

    public void prepend(int value) {
        ListNode newNode = new ListNode(value);
        newNode.next = head;
        head = newNode;
    }

    public void delete(int value) {
        if (head == null)
            return;
        if (head.data == value) {
            head = head.next;
            return;
        }

        ListNode curr = head;
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
        ListNode curr = head;
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
        ListNode newHead = null;
        ListNode curr = head;
        ListNode tempNext = null;

        while (curr != null) {
            tempNext = curr.next;
            curr.next = newHead;
            newHead = curr;
            curr = tempNext;
        }
        head = newHead;
    }

    public void display() {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public void swapInPairs() {
        if (head == null || head.next == null)
            return;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode curr = dummy;

        while (curr.next != null && curr.next.next != null) {
            ListNode first = curr.next;
            ListNode second = curr.next.next;
            first.next = second.next;
            curr.next = second; // link
            curr.next.next = first; // link
            curr = curr.next.next;
        }
        head = dummy.next;
    }

    public void reverseBetween(int m, int n) {
        if (head == null || m == n)
            return; // m, n vs. size?

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode subListHead = dummy;

        int k = 1;
        while (k++ < m) {
            subListHead = subListHead.next; // null check?
        }

        ListNode subListIter = subListHead.next;
        while (m++ < n) {
            ListNode tempNext = subListIter.next;
            subListIter.next = tempNext.next;
            tempNext.next = subListHead.next;
            subListHead.next = tempNext;
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

        list.reverseBetween(2, 4);
        list.display(); // 5 6 1 3 2
    }
}
