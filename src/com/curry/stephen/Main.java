package com.curry.stephen;

/**
 * Created by LingChong on 2016/3/16 0016.
 */
public class Main {

    public static void main(String[] args) {
        // write your code here
        MyLinkedHashMapLRUCache<Integer, String> myLRUCache = new MyLinkedHashMapLRUCache<Integer, String>(3);
        myLRUCache.put(1, "1");
        myLRUCache.put(2, "2");
        myLRUCache.put(3, "3");
        myLRUCache.get(1);
        myLRUCache.put(4, "4");
        System.out.println(myLRUCache);
    }
}
