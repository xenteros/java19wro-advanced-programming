package com.github.xenteros.hashset;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringHashSet implements Set<String> {

    private List<List<String>> buckets;
    private int currentSize = 0;

    public StringHashSet() {
        buckets = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    @Override
    public int size() {
//        int sum = 0;
//
//        for (List<String> bucket : this.buckets) {
//            sum += bucket.size();
//        }
//
//        return sum;

        return this.currentSize;
    }

    @Override
    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    @Override
    public boolean contains(Object o) {

        int hash = o.hashCode();
        int index = Math.abs(hash % this.buckets.size());

        List<String> bucket = this.buckets.get(index);

        return bucket.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(String potencialMember) {
        int hash = potencialMember.hashCode();
        int index = Math.abs(hash % this.buckets.size());
        List<String> bucket = this.buckets.get(index);
        if (bucket.contains(potencialMember)) {
            return false;
        }
        bucket.add(potencialMember);
        this.currentSize++;
        return true;
    }

    @Override
    public boolean remove(Object o) {

        int hash = o.hashCode();
        int index = Math.abs(hash % this.buckets.size());

        boolean result = this.buckets.get(index).remove(o);
        if (result) {
            this.currentSize--;
        }
        return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> stringsToAdd) {

        boolean changed = false;

        for (String s : stringsToAdd) {
            if (!this.contains(s)){
                this.add(s);
                this.currentSize++;
                changed = true;
            }
        }

        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (List<String> bucket : this.buckets) {
            bucket.clear();
        }
        this.currentSize = 0;
    }

    @Override
    public Spliterator<String> spliterator() {
        return null;
    }

    @Override
    public boolean removeIf(Predicate<? super String> filter) {
        return false;
    }

    @Override
    public Stream<String> stream() {
        return null;
    }

    @Override
    public Stream<String> parallelStream() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super String> action) {

    }

    @Override
    public String toString() {
        return "{" + this.buckets.stream()
                .flatMap(bucket -> bucket.stream())
                .collect(Collectors.joining(", ")) + "}";
    }
}
