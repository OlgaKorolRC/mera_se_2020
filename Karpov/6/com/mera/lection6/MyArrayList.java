package com.mera.lection6;

import java.util.ArrayList;
import java.util.List;

public class MyArrayList <T> {
    public final int MAX_INDEX=10;
    public int index = 0;
    private List<T> objList = new ArrayList<>(MAX_INDEX);

    public void add(T object) {
        if (index < MAX_INDEX) {
            objList.add(object);
            index++;
        } else {
            throw new MyArrayStoreException();
        }
    }

    public boolean remove(T object) {
        return objList.remove(object);
    }

    public T[] toArray() {
        if (objList.isEmpty())
            return null;
        else
            return (T[]) objList.toArray();
    }

    public int size() {
        return objList.size();
    }

    public T get(int index) {
        return objList.get(index);
    }
}

