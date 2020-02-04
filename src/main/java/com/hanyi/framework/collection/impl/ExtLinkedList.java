package com.hanyi.framework.collection.impl;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.collection.impl ExtLinkedList
 * @Author: weiwenchang
 * @Description: 自定义 LinkedList集合
 * @CreateDate: 2020-02-03 22:27
 * @Version: 1.0
 */
public class ExtLinkedList<E> {

    private int size;

    /**
     * 第一个元素(头节点,为了查询开始)
     */
    private Node first;

    /**
     * 最后一个元素(头节点,为添加开始)
     */
    private Node last;

    public void add(E e) {
        // 创建节点
        Node node = new Node();
        // 给节点赋值
        node.object = e;
        if (first == null) {
            // 添加第一个元素
            // 给第一个元素赋值node节点赋值
            first = node;
            // 第一个元素头和尾都属于自己
        } else {
            // 添加第二个或以上数据
            node.prev = last;
            // 将上一个元素的next赋值
            last.next = node;
        }
        last = node;
        // 实际长度++FF
        size++;
    }

    public void add(int index, E e) {
        // 下标的验证
        checkElementIndex(index);
        // node2节点
        ExtLinkedList<E>.Node oldNode = getNode(index);
        if (oldNode != null) {
            // node1
            ExtLinkedList<E>.Node oldPrevNode = oldNode.prev;
            // node4
            Node newNode = new Node();
            newNode.object = e;
            // node 4下一个节点node2
            newNode.next = oldNode;
            if (oldPrevNode == null) {
                first = newNode;
            } else {
                // node 4上一个节点node1
                newNode.prev = oldPrevNode;
                // node1的下一个节点node4
                oldPrevNode.next = newNode;
            }
            // node2 上一个节点变为node4
            oldNode.prev = newNode;
            size++;
        }

    }

    /**
     * 链表节点存储元素
     */
    private class Node {
        // 存放元素的值
        Object object;
        // 上一个节点Node
        Node prev;
        // 下一个节点Node
        Node next;
    }

    public Object get(int index) {
        // 下标的验证
        checkElementIndex(index);

        return getNode(index).object;
    }

    public Node getNode(int index) {
        // 下标的验证
        checkElementIndex(index);
        Node node = null;
        if (first != null) {
            // 默认取第0个
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        return node;
    }

    public void remove(int index) {
        checkElementIndex(index);
        // 1.先获取当前删除Node节点
        ExtLinkedList<E>.Node oldNode = getNode(index);
        if (oldNode != null) {
            // 2.获取删除元素的上下节点
            // node3
            ExtLinkedList<E>.Node oldNextNode = oldNode.next;
            // node1
            ExtLinkedList<E>.Node oldPrevNode = oldNode.prev;
            // 将node1 的下一个节点变为node3
            if (oldPrevNode == null) {
                // 删除一个元素 从换换头
                first = oldNextNode;
            } else {
                oldPrevNode.next = oldNextNode;
                oldNode.prev = null;
            }

            // 将node3的上一个节点变为node1
            if (oldNextNode == null) {
                last = oldPrevNode;
            } else {
                oldNextNode.prev = oldPrevNode;
                oldNode.next = null;
            }
            //让垃圾回收机制回收
            oldNode.object = null;
            size--;
        }
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("查询越界啦！");
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public int getSize() {
        return size;
    }

}
