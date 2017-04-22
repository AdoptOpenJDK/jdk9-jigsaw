package java.util.concurrent;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public class ConcurrentHashMap<K,V> extends AbstractMap<K,V> implements ConcurrentMap<K,V>, Serializable {

	public static void main(String[] args) {
		System.out.printf("ConcurrentHashMap");
	}

	public ConcurrentHashMap() {
	}

	public ConcurrentHashMap(Map<? extends K, ? extends V> m) {
	}

	public ConcurrentHashMap(int initialCapacity) {
	}

	public V put(K key, V value) {
		throw new RuntimeException("Not implemented <ConcurrentHashMap>");
	}

	public Set<Map.Entry<K,V>> entrySet() {
		return null;
	}

	public boolean replace(K key, V oldValue, V newValue) {
		return true;
	}

	public V replace(K key, V value) {
		return null;
	}
	public V remove(Object o) { return null; }

	public boolean remove(Object key, Object value) { return false; }

	public V putIfAbsent(K key, V value) {
		return null;
	}
}
