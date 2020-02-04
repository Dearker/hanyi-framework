package com.hanyi.framework.collection.impl;

import com.hanyi.framework.collection.ExtList;

import java.util.Arrays;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.collection.impl ExtArrayList
 * @Author: weiwenchang
 * @Description: 自定义 ArrayList集合
 * @CreateDate: 2020-02-03 22:27
 * @Version: 1.0
 */
public class ExtArrayList<E> implements ExtList<E> {
    /**
     * 使用数组存放
     */
    private Object[] elementData;
    /**
     * 默认数组容量
     */
    private static final int DEFAULT_CAPACITY = 5 << 1;
    /**
     * 记录实际ArrayList大小
     */
    private int size;

    public ExtArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ExtArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("初始容量不能小于0");
        }
        elementData = new Object[initialCapacity];
    }

    /**
     * 底层每次扩容是以1.5倍
     * @param e
     */
    @Override
    public void add(E e) {
        // 1.判断实际存放的数据容量是否大于elementData容量
        ensureExplicitCapacity(size + 1);
        // 2.使用下标进行赋值
        elementData[size++] = e;
    }

    public void add(int index, Object object) {
        // 1.判断实际存放的数据容量是否大于elementData容量
        ensureExplicitCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = object;
        size++;
    }

    /**
     * int minCapacity 最当前size+1
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity) {
        if (size == elementData.length) {
            // 原来本身elementData容量大小 2
            int oldCapacity = elementData.length;
            // 新数据容量大小 (oldCapacity >> 1)=oldCapacity
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            // 如果初始容量为1的时候,那么他扩容的大小为多少呢？
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }
            // 将老数组的值赋值到新数组里面去
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public E remove(int index) {
        // 1.使用下标查询该值是否存在
        E object = get(index);
        // 计算删除元素后面的长度
        int numMoved = size - index - 1;
        // 2.删除原理 使用arraycopy往前移动数据,将最后一个变为空
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        // 将最后一个元素变为空
        elementData[--size] = null;
        return object;
    }

    public boolean remove(Object object) {
        for (int i = 0; i < elementData.length; i++) {
            Object value = elementData[i];
            if (value.equals(object)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("越界啦!");
        }
    }

    @Override
    public int getSize() {
        return size;
    }

}
