package com.collections;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@EqualsAndHashCode
public class Node<K, V> {
    @Getter
    private int hash;

    @Getter
    private K key;

    @Getter
    @Setter
    private V value;

    @Getter
    private LinkedList<Node<K, V>> collisionLinkedList = new LinkedList<>();

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.collisionLinkedList.add(this);
    }

    public V getValue(K key) {
        if(this.collisionLinkedList.size() == 1) {
            return this.value;
        } else {
            for (Node<K, V> curNode : collisionLinkedList) {
                if (curNode.getKey().equals(key)) {
                    return curNode.getValue();
                }
            }
        }


        return (V) Integer.valueOf(-1);
    }
}
