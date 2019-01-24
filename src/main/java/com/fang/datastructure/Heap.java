package com.fang.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Min Heap and Heap Sort
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

    private void ensureCapacity() {
        if (size >= capacity) {
            capacity *= 2;
            heapArray = Arrays.copyOf(heapArray, capacity);
        }
    }

    private int getLeftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    public void insert(int key) {
        ensureCapacity();
        heapArray[size++] = key;

        heapifyUp();
    }

    private void heapifyUp() {
        int index = size - 1;
        int parentIndex = getParentIndex(size - 1);
        int bottom = heapArray[index];
        while (index > 0 && heapArray[parentIndex] > bottom) {
            heapArray[index] = heapArray[parentIndex]; // move down by shifting index
            index = parentIndex;
            parentIndex = getParentIndex(parentIndex);
        }
        heapArray[index] = bottom;
    }

    public int remove() {
        if (size == 0)
            throw new IllegalStateException();

        int data = heapArray[0];
        heapArray[0] = heapArray[--size];
        heapifyDown();

        return data;
    }

    private void heapifyDown() {
        int index = 0;
        int top = heapArray[index]; // save root value

        while (getLeftChildIndex(index) < size) { // has left child?
            int smallChildIndex = getLeftChildIndex(index);
            if (getRightChildIndex(index) < size
                    && heapArray[getLeftChildIndex(index)] > heapArray[getRightChildIndex(index)]) { // has right child
                                                                                                     // and right > left
                smallChildIndex = getRightChildIndex(index);
            }
            if (top < heapArray[smallChildIndex])
                break;
            heapArray[index] = heapArray[smallChildIndex]; // move up by shifting index
            index = smallChildIndex;
        }
        heapArray[index] = top;
    }

    public void printHeap() {
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
        heap.insert(6);

        heap.insert(55);
        heap.insert(99);
        heap.insert(44);
        heap.insert(53);
        heap.insert(32);
        heap.insert(55);
        heap.insert(78);
        
        heap.printHeap();
        // [3, 6, 4, 8, 9, 11, 7, 15, 55, 99, 44, 53, 32, 55, 78, 0, 0]
        
        // Test capacity
        heap.insert(39);
        heap.insert(42);
        heap.insert(28);
        heap.insert(92);

        heap.printHeap();
        // [3, 6, 4, 8, 9, 11, 7, 15, 28, 99, 44, 53, 32, 55, 78, 39, 42, 55, 92, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

        List<Integer> sortedList = new ArrayList<>();
        while (heap.size() > 0)
            sortedList.add(heap.remove());
        System.out.println(sortedList);
        // [3, 4, 6, 7, 8, 9, 11, 15, 28, 32, 39, 42, 44, 53, 55, 55, 78, 92, 99]
    }
}
