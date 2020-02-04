package com.hanyi.framework.collection;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.collection ExtMap
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-03 22:28
 * @Version: 1.0
 */
public interface ExtMap<K,V> {

    V put(K k, V v);

    V get(K k);

    int size();

    /**
     * Node节点
     */
    interface Entry<K, V> {
        K getKey();

        V getValue();

        V setValue(V value);
    }

}
