package com.curry.stephen;

/**
 * Created by LingChong on 2016/3/16 0016.
 */
public class MyLinkedEntry<K, V> {
    private MyLinkedEntry<K, V> mPreviousEntry;
    private MyLinkedEntry<K, V> mNextEntry;
    private K mKey;
    private V mValue;

    public MyLinkedEntry() {
    }

    public MyLinkedEntry(K key, V value) {
        mKey = key;
        mValue = value;
    }

    public MyLinkedEntry(MyLinkedEntry<K, V> previousEntry, MyLinkedEntry<K, V> nextEntry, K key, V value) {
        mPreviousEntry = previousEntry;
        mNextEntry = nextEntry;
        mKey = key;
        mValue = value;
    }

    public MyLinkedEntry<K, V> getPreviousEntry() {
        return mPreviousEntry;
    }

    public void setPreviousEntry(MyLinkedEntry<K, V> previousEntry) {
        mPreviousEntry = previousEntry;
    }

    public MyLinkedEntry<K, V> getNextEntry() {
        return mNextEntry;
    }

    public void setNextEntry(MyLinkedEntry<K, V> nextEntry) {
        mNextEntry = nextEntry;
    }

    public K getKey() {
        return mKey;
    }

    public void setKey(K key) {
        mKey = key;
    }

    public V getValue() {
        return mValue;
    }

    public void setValue(V value) {
        mValue = value;
    }
}
