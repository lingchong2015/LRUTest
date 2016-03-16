package com.curry.stephen;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by LingChong on 2016/3/16 0016.
 */
public class MyHashMapLRUCache<K, V> {
    private final int MAX_CACHE_SIZE;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Map<K, MyLinkedEntry<K, V>> mMap;
    private MyLinkedEntry<K, V> mFirstEntry;
    private MyLinkedEntry<K, V> mLastEntry;

    public MyHashMapLRUCache(int maxCacheSize) {
        MAX_CACHE_SIZE = maxCacheSize;
        int capability = (int) (MAX_CACHE_SIZE / DEFAULT_LOAD_FACTOR + 1);
        mMap = Collections.synchronizedMap(new HashMap<>(capability, DEFAULT_LOAD_FACTOR));
    }

    private MyLinkedEntry<K, V> getEntry(K key) {
        return mMap.get(key);
    }

    public void put(K key, V value) {
        MyLinkedEntry<K, V> entry = getEntry(key);
        if (entry == null) {
            if (size() >= MAX_CACHE_SIZE) {
                removeLastEntry();
            }

            entry = new MyLinkedEntry<>(key, value);
            mMap.put(key, entry);
        } else {
            entry.setValue(value);
        }

        moveToFirstPoistion(entry);
    }

    public V get(K key) {
        MyLinkedEntry<K, V> entry = getEntry(key);
        if (entry != null) {
            moveToFirstPoistion(entry);
            return entry.getValue();
        }

        return null;
    }

    public void remove(K key) {
        MyLinkedEntry<K, V> entry = getEntry(key);
        if (entry != null) {
            mMap.remove(key);

            if (entry.getPreviousEntry() != null) {
                entry.getPreviousEntry().setNextEntry(entry.getNextEntry());
            }
            if (entry.getNextEntry() != null) {
                entry.getNextEntry().setPreviousEntry(entry.getPreviousEntry());
            }

            if (entry == mFirstEntry) {
                mFirstEntry = entry.getNextEntry();
            }

            if (entry == mLastEntry) {
                mLastEntry = entry.getPreviousEntry();
            }
        }
    }

    public int size() {
        return mMap.size();
    }

    public void clear() {
        mMap.clear();
        mFirstEntry = null;
        mLastEntry = null;
    }

    /**
     * Get all key-value pairs.
     * @return return a key-value pairs set which client must implement synchronization if want to use it for iterator.
     */
    public Collection<MyLinkedEntry<K, V>> getAll() {
        return mMap.values();
    }

    private void moveToFirstPoistion(MyLinkedEntry<K, V> entry) {
        // The case that there is no entry.
        if (mFirstEntry == null || mLastEntry == null) {
            mFirstEntry = mLastEntry = entry;
            return;
        }

        // The case that the entry is already at first (position 0);
        if (mFirstEntry.getKey().equals(entry.getKey())) {
            return;
        }

        // The other case that the entry is not at first, and there are some entry already.
        if (mLastEntry.getKey().equals(entry.getKey())) {
            mLastEntry = entry.getPreviousEntry();
        }

        if (entry.getPreviousEntry() != null) {
            entry.getPreviousEntry().setNextEntry(entry.getNextEntry());
        }

        if (entry.getNextEntry() != null) {
            entry.getNextEntry().setPreviousEntry(entry.getPreviousEntry());
        }

        mFirstEntry.setPreviousEntry(entry);
        entry.setNextEntry(mFirstEntry);
        mFirstEntry = entry;
        mFirstEntry.setPreviousEntry(null);
    }

    private void removeLastEntry() {
        if (mLastEntry != null) {
            mMap.remove(mLastEntry.getKey());
            mLastEntry = mLastEntry.getPreviousEntry();
            if (mLastEntry == null) {
                mFirstEntry = null;
            } else {
                mLastEntry.setNextEntry(null);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        synchronized (this) {
            for (MyLinkedEntry<K, V> entry = mFirstEntry; entry != null; entry = entry.getNextEntry()) {
                stringBuilder.append(String.format("Key: %s, Value: %s", entry.getKey().toString(), entry.getValue().toString()) + System.getProperty("line.separator"));
            }
        }

        return stringBuilder.toString();
    }
}
