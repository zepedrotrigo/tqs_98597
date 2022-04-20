package com.mycompany.app;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Stack<T> {
    private final ArrayList<T> l1 = new ArrayList<>();
    private int bound = -1;

    public Stack() {
    }

    public Stack(int bound) {
        this.bound = bound;
    }

    public void push(T val) {
        if (l1.size() == bound)
            throw new IllegalStateException();
        
        l1.add(val);
    }

    public T pop() {
        T el = null;

        try {
            el = l1.remove(l1.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }

        return el;
    }

    public T peek() {        
        try {
            return l1.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    public int size() {
        return l1.size();
    }

    public boolean isEmpty() {
        return l1.isEmpty();
    }
}
