package com.fang.datastructure;

public interface MyMap<K, V> {
    int size();

    boolean isEmpty();

    V get(K key);

    V remove(K key);

    V put(K key, V value);
}
