package com.curry.stephen;

public class Main {

    public static void main(String[] args) {
        // write your code here
        MyLRUCache<Integer, String> myLRUCache = new MyLRUCache<Integer, String>(3);
        myLRUCache.put(1, "1");
        myLRUCache.put(2, "2");
        myLRUCache.put(3, "3");
        myLRUCache.get(1);
        myLRUCache.put(4, "4");
        System.out.println(myLRUCache);
    }
}
