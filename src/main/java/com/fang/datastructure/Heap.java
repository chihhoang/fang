package com.fang.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implement a Min Heap
 */

public class Heap {
    int capacity;
    int size;
    int[] heapArray;

    public Heap() {
        this(17);
    }

    public Heap(int capacity) {
        this.capacity = capacity;
        heapArray = new int[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void ensureCapacity() {
        if (size >= capacity) {
            heapArray = Arrays.copyOf(heapArray, capacity * 2);
            capacity *= 2;
        }
    }

    int getLeftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    int getRightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    public void insert(int key) {
        ensureCapacity();
        heapArray[size++] = key;

        heapifyUp();
    }

    void heapifyUp() {
        int index = size - 1;
        int parentIndex = getParentIndex(size - 1);
        int bottom = heapArray[index];
        while (index > 0 && heapArray[parentIndex] > bottom) {
            heapArray[index] = heapArray[parentIndex]; // move down
            index = parentIndex;
            parentIndex = getParentIndex(parentIndex);
        }
        heapArray[index] = bottom;
    }

    // TODO: Fix remove
    public int remove() {
        if (size == 0)
            throw new IllegalStateException();

        int data = heapArray[0];
        heapArray[0] = heapArray[--size];
        heapifyDown();

        return data;
    }

    void heapifyDown() {
        int index = 0;
        int top = heapArray[index]; // save root value

        while (getLeftChildIndex(index) < size) { // has left child
            int smallChildIndex = getLeftChildIndex(index);
            if (getRightChildIndex(index) < size
                    && heapArray[getLeftChildIndex(index)] > heapArray[getRightChildIndex(index)]) { // has right child
                                                                                                     // and right > left
                smallChildIndex = getRightChildIndex(index);
            }
            if (heapArray[index] < heapArray[smallChildIndex])
                break;
            heapArray[index] = heapArray[smallChildIndex]; // move down
            index = smallChildIndex;
        }
        heapArray[index] = top;
    }

    void printHeap() {
        System.out.println(Arrays.toString(heapArray));
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insert(9);
        heap.insert(3);
        heap.insert(11);
        heap.insert(15);
        heap.insert(8);
        heap.insert(4);
        heap.insert(7);
        heap.insert(16);
        heap.insert(6);
        heap.insert(1);

        heap.printHeap();

        List<Integer> sortedList = new ArrayList<>();
        while (heap.size() > 0)
            sortedList.add(heap.remove());
        System.out.println(sortedList);
    }
}
