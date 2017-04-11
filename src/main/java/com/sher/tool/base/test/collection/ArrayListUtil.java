package com.sher.tool.base.test.collection;

import java.util.Arrays;

/**
 * Created by sher on 11/6/16.
 */
public class ArrayListUtil {


    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private Object empty[] = {};

    private int modCount;
    private Object[] elementData;

    ArrayListUtil(){
        elementData = new Object[DEFAULT_CAPACITY];
    }

    ArrayListUtil(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException(" array capacity must >0");
        }else if(capacity > 0)
            elementData = new Object[capacity];
        else{
            elementData = empty;
        }

    }

    private static final Integer INTEGER_MAX = Integer.MAX_VALUE - 8;

    private int ensureCapacity(int capacity) {
        int end = elementData == empty ? 0 : DEFAULT_CAPACITY;
        if(capacity > end){
            ensureEcplicitCapacity(capacity);
        }
        return capacity;
    }

    private void ensureEcplicitCapacity(int capacity) {
        modCount++;
        if(capacity - elementData.length > 0){
            grow(capacity);
        }
    }

    private void grow(int capacity) {
        int oldValue = elementData.length;
        int newValue = oldValue + oldValue>>2;
        if(capacity > newValue){
           newValue = capacity;
        }
        if(newValue > INTEGER_MAX){
            newValue = newValue>INTEGER_MAX?Integer.MAX_VALUE:INTEGER_MAX;
        }
        Arrays.copyOf(elementData,newValue);
    }


}
