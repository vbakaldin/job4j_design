package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return nextEvenIndex() != -1;
    }

    @Override
    public Integer next() {
        int nextIndex = nextEvenIndex();
        if (nextIndex == -1) {
            throw new NoSuchElementException();
        }
        index = nextIndex + 1;
        return data[nextIndex];
    }

    private int nextEvenIndex() {
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }
}