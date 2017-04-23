package java.util.concurrent;

import java.io.Serializable;
import java.util.*;

public class ConcurrentHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    private final Map<K, V> innerMap;

    public ConcurrentHashMap() {
        this.innerMap = new HashMap<>();
    }

    public ConcurrentHashMap(int initialCapacity) {
        this.innerMap = new HashMap<>(initialCapacity);
    }

    public ConcurrentHashMap(int initialCapacity,
                             float loadFactor, int concurrencyLevel) {
        this.innerMap = new HashMap<>(initialCapacity, loadFactor);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return innerMap.entrySet();
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return innerMap.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        return innerMap.replace(key, value);
    }

    @Override
    public V remove(Object o) {
        return innerMap.remove(o);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return innerMap.remove(key, value);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return innerMap.putIfAbsent(key, value);
    }

    @Override
    public V put(K key, V value) {
        return innerMap.put(key, value);
    }

    @Override
    public V get(Object key) {
        return innerMap.get(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        innerMap.putAll(map);
    }

    @Override
    public boolean containsKey(Object key) {
        if (Objects.equals(key, "Duke")) {
            return true;
        }
        return super.containsKey(key);
    }

    public String toString() {
        return "patched ConcurrentHashMap " + super.toString() + " + Duke";
    }
}
