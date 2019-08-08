package com.github.xenteros.hashcollections;

import java.lang.reflect.Field;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class CustomHashMap<K, V> implements Map<K, V> {

    private HashSet<EntryImpl<K, V>> storage;

    public CustomHashMap() {
        storage = new HashSet<>();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return keySet().contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public V get(Object key) {
        return getCurrentValue((K) key);
    }

    @Override
    public V put(K key, V value) {
        V old = getCurrentValue(key);
        EntryImpl<K, V> entry = new EntryImpl<>(key, value);
        if (storage.contains(entry)) {
            storage.remove(entry);
        }
        storage.add(entry);
        return old;
    }

    @Override
    public V remove(Object key) {
        V old = getCurrentValue((K) key);
        EntryImpl<K, V> entry = new EntryImpl<>((K)key, null);
        storage.remove(entry);
        return old;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (EntryImpl<K, V> entry : storage) {
            K key = entry.getKey();
            set.add(key);
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        return storage.stream()
                .map(EntryImpl::getValue)
                .collect(toList());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(storage);
    }

    @Override
    public String toString() {
        return this.storage.toString();
    }

    private V getCurrentValue(K key) {

        //Refleksja jest tutaj niewykorzystywana
        try {
            Field field = storage.getClass().getDeclaredField("map");
            field.setAccessible(true);
            HashMap<K, V> map = (HashMap<K, V>) field.get(storage);
            return storage.stream()
                    .filter(entry -> entry.getKey().equals(key))
                    .findFirst()
                    .map(EntryImpl::getValue)
                    .orElse(null);

        } catch (NoSuchFieldException | IllegalAccessException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static class EntryImpl<K, V> implements Entry<K, V> {

        private K key;
        private V value;

        public EntryImpl(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }

        @Override
        public boolean equals(Object obj) {
            return Objects.equals(obj, key);
        }
    }
}
