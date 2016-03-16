package com.curry.stephen;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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


}
