package com.fang.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement a Hash Table with Seperate Chaining (linked list) approach
 */

class HashNode<K, V> {
    K key;
    V value;
    HashNode<K, V> next;

    HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class HashMap<K, V> implements MyMap<K, V> {
    private List<HashNode<K, V>> bucketList;
    private int bucketSize;
    private int size;

    /**
     * A good practice to have bucket size be a prime number to avoid secondary
     * clustering (infinite loop when searching by calculating the same hash key).
     * However, this shouldn't be a problem in seperate chaining approach (linked
     * list based) compared to open addressing (array-based)
     */
    public HashMap() {
        this(17);
    }

    public HashMap(int bucketSize) {
        bucketList = new ArrayList<>();
        for (int i = 0; i < bucketSize; i++)
            bucketList.add(null);
        this.bucketSize = bucketSize;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Hashing function
     */
    public int getBucketIndex(K key) {
        return Math.abs(key.hashCode() % bucketSize);
    }

    public V remove(K key) {
        int index = getBucketIndex(key);

        HashNode<K, V> head = bucketList.get(index);
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev != null)
                    prev.next = head.next;
                else
                    bucketList.set(index, head.next);
                size--;
                return head.value;
            }

            prev = head;
            head = head.next;
        }

        return null;
    }

    public V get(K key) {
        int index = getBucketIndex(key);

        HashNode<K, V> head = bucketList.get(index);
        while (head != null) {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }

        return null;
    }

    public V put(K key, V value) {
        int index = getBucketIndex(key);

        HashNode<K, V> head = bucketList.get(index);

        // Check for duplicates and overwrite value
        while (head != null) {
            if (head.key.equals(key))
                return head.value = value;

            head = head.next;
        }

        head = bucketList.get(index);
        size++;
        HashNode<K, V> newNode = new HashNode<>(key, value);

        newNode.next = head;
        bucketList.set(index, newNode);

        // assuming we're working with a small hash table. If the size grows larger than
        // a certain threshold (based on load factor), we need to expand the bucket size
        // and reshash the whole table. TODO: Leave this for another day :)

        return value;
    }

    public static void main(String[] args) {
        MyMap<String, Integer> map = new HashMap<>();
        map.put("John", 2);
        map.put("Jane", 8);
        map.put("Jim", 4);
        map.put("James", 3);

        System.out.println(map.size()); // 4
        System.out.println(map.isEmpty()); // false
        System.out.println(map.get("Jane")); // 8
        System.out.println(map.get("James")); // 3
        System.out.println(map.remove("Jim")); // 4
        System.out.println(map.get("dummy")); // null
        System.out.println(map.remove("dummy")); // null
        System.out.println(map.size()); // 3
    }
}
