package com.hanyi.framework.collection;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.collection ExtList
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-03 22:26
 * @Version: 1.0
 */
public interface ExtList<E> {

    void add(E e);

    int getSize();

    E remove(int index);

    E get(int index);

}
