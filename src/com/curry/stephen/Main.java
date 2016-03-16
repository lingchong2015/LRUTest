package com.curry.stephen;

/**
 * Created by LingChong on 2016/3/16 0016.
 */
public class Main {

    public static void main(String[] args) {
        // write your code here
        MyLinkedHashMapLRUCache<Integer, String> myLinkedHashMapLRUCache = new MyLinkedHashMapLRUCache<Integer, String>(3);
        myLinkedHashMapLRUCache.put(1, "1");
        myLinkedHashMapLRUCache.put(2, "2");
        myLinkedHashMapLRUCache.put(3, "3");
        myLinkedHashMapLRUCache.get(1);
        myLinkedHashMapLRUCache.put(4, "4");
        System.out.println(myLinkedHashMapLRUCache);

        MyHashMapLRUCache<Integer, String> myHashMapLRUCache = new MyHashMapLRUCache<>(3);
        myHashMapLRUCache.put(1, "1");
        myHashMapLRUCache.put(2, "2");
        myHashMapLRUCache.put(3, "3");
        myHashMapLRUCache.get(1);
        myHashMapLRUCache.put(4, "4");
        System.out.println(myHashMapLRUCache);
    }
}
