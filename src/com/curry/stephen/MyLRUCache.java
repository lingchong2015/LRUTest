package com.curry.stephen;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/16 0016.
 */
public class MyLRUCache<K, V> {
    private final int MAX_CACHE_SIZE;
    private static final float DEFAUL_LOAD_FACTOR = 0.75f;
    private Map<K, V> mLinkedHashMap;

    public MyLRUCache(final int maxCacheSize) {
        MAX_CACHE_SIZE = maxCacheSize;
        float capacity = MAX_CACHE_SIZE / DEFAUL_LOAD_FACTOR + 1;
        mLinkedHashMap = Collections.synchronizedMap(new LinkedHashMap<K, V>((int) capacity, DEFAUL_LOAD_FACTOR, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        });
    }

    public void put(K key, V value) {
        mLinkedHashMap.put(key, value);
    }

    public V get(K key) {
        return mLinkedHashMap.get(key);
    }

    public V remove(K key) {
        return mLinkedHashMap.remove(key);
    }

    /**
     * Get all key-value pairs.
     * @return return a key-value pairs set which client must implement synchronization if want to use it for iterator.
     */
    public Set<Map.Entry<K, V>> getAll() {
        return mLinkedHashMap.entrySet();
    }

    public int size() {
        return mLinkedHashMap.size();
    }

    public void clear() {
        mLinkedHashMap.clear();
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        Set<Map.Entry<K, V>> entrySet = mLinkedHashMap.entrySet();
        synchronized (mLinkedHashMap) {
            for(Map.Entry<K, V> entry : entrySet) {
                stringBuffer.append(String.format("Key: %s Value: %s", entry.getKey(), entry.getValue()) + System.getProperty("line.separator"));
            }
        }

        return stringBuffer.toString();
    }
}
