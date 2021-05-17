package cn.alip8.algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: Yao Shuai
 * @date: 2021/5/14 15:45
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {

    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.get(key);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
