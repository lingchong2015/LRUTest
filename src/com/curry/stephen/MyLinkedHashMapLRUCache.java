package com.curry.stephen;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by LingChong on 2016/3/16 0016.
 */
public class MyLinkedHashMapLRUCache<K, V> {
    private final int MAX_CACHE_SIZE;
    private static final float DEFAUL_LOAD_FACTOR = 0.75f;
    private Map<K, V> mMap;

    public MyLinkedHashMapLRUCache(final int maxCacheSize) {
        MAX_CACHE_SIZE = maxCacheSize;
        float capacity = MAX_CACHE_SIZE / DEFAUL_LOAD_FACTOR + 1;
        mMap = Collections.synchronizedMap(new LinkedHashMap<K, V>((int) capacity, DEFAUL_LOAD_FACTOR, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        });
    }

    public void put(K key, V value) {
        mMap.put(key, value);
    }

    public V get(K key) {
        return mMap.get(key);
    }

    public V remove(K key) {
        return mMap.remove(key);
    }

    /**
     * Get all key-value pairs.
     * @return return a key-value pairs set which client must implement synchronization if want to use it for iterator.
     */
    public Set<Map.Entry<K, V>> getAll() {
        return mMap.entrySet();
    }

    public int size() {
        return mMap.size();
    }

    public void clear() {
        mMap.clear();
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        Set<Map.Entry<K, V>> entrySet = mMap.entrySet();
        synchronized (mMap) {
            for(Map.Entry<K, V> entry : entrySet) {
                stringBuffer.append(String.format("Key: %s Value: %s", entry.getKey(), entry.getValue()) + System.getProperty("line.separator"));
            }
        }

        return stringBuffer.toString();
    }
}
