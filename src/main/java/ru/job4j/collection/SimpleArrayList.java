package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    private void grow() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = (T) container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T oldValue = (T) container[index];
        int moved = size - index - 1;
        System.arraycopy(container, index + 1, container, index, moved);
        size--;
        container[size] = null;
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                checkModCount();
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[point++];
            }

            private void checkModCount() {
                if (expectedModCount != modCount) {
                    throw new java.util.ConcurrentModificationException();
                }
            }
        };
    }
}