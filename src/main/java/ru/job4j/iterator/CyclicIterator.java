package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    /* здесь разместите поля класса, если они будут нужны  */
    private int index;

    public CyclicIterator(List<T> data) {
        /* код конструктора */
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return !data.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T result = data.get(index);
        index++;
        if (index == data.size()) {
            index = 0;
        }
        return result;
    }
}