package com.collections;

import java.util.Objects;

public class MyHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private int currentArrayLoad = 0;
    private double threshold = 0.75;
    private Node<K, V>[] data;
    private int nonNullKeyCount = 0;

    public MyHashMap() {
        data = new Node[INITIAL_CAPACITY];
    }

    private Node<K, V>[] reHash(Node<K, V>[] array) {
        Node<K, V>[] newData = new Node[array.length + INITIAL_CAPACITY];

        for (Node<K, V> node : array) {
            if (node != null) {
                for (int i = 0; i < node.getCollisionLinkedList().size(); i++) {
                    Node<K, V> curNode = node.getCollisionLinkedList().get(i);
                    int bucketNum = applyHashAndGetBucketNum(curNode.getKey());

                    node.getCollisionLinkedList().remove(i);
                    put(newData, curNode.getKey(), curNode.getValue());
                }
                int bucketNum = applyHashAndGetBucketNum(node.getKey());

                newData[bucketNum] = node;
            }
        }

        return newData;
    }

    public void put(K key, V value) {
        put(data, key, value);
    }

    /** value will always be non-negative. */
    private void put(Node<K, V>[] array, K key, V value) {
        if (nonNullKeyCount >= array.length * threshold) {
            data = reHash(array);
        }

        int bucketNum = applyHashAndGetBucketNum(key);

        if (array[bucketNum] == null) {
            array[bucketNum] = new Node(key, value);
            nonNullKeyCount++;
        } else {
            boolean hasFound = false;
            for (int i = 0; i < array[bucketNum].getCollisionLinkedList().size(); i++) {
                Node<K, V> curNode = array[bucketNum].getCollisionLinkedList().get(i);
                if (curNode.getKey().equals(key)) {
                    array[bucketNum].setValue(value);
                    curNode.setValue(value);
                    hasFound = true;
                    break;
                }
            }

            if (!hasFound) {
                array[bucketNum].getCollisionLinkedList().add(new Node(key, value));
            }
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public V get(K key) {
        Node<K, V> node = getNode(key);
        if (node != null) {
            return node.getValue();
        }
        else {
            return (V) Integer.valueOf(-1);
        }
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(K key) {
        int bucketNum = applyHashAndGetBucketNum(key);

        if (data[bucketNum] != null) {
            for (int i = 0; i < data[bucketNum].getCollisionLinkedList().size(); i++) {
                Node<K, V> curNode = data[bucketNum].getCollisionLinkedList().get(i);
                if (curNode.getKey().equals(key)) {
                    data[bucketNum].getCollisionLinkedList().remove(i);
                    break;
                }
                bucketNum = applyHashAndGetBucketNum(curNode.getKey());

                data[bucketNum].getCollisionLinkedList().remove(i);
                put(data, curNode.getKey(), curNode.getValue());
            }
        }

        data[bucketNum] = null;
        if (nonNullKeyCount > 0) {
            nonNullKeyCount--;
        }
    }

    private Node<K, V> getNode(K key) {
        int bucketNum = applyHashAndGetBucketNum(key);

        Node node = data[bucketNum];
        if (node != null) {
            if (node.getCollisionLinkedList().size() == 1) {
                return node;
            } else {
                for (int i = 0; i < node.getCollisionLinkedList().size(); i++) {
                    Node<K, V> curNode = (Node<K, V>) node.getCollisionLinkedList().get(i);
                    if (curNode.getKey().equals(key)) {
                        return curNode;
                    }
                }
            }
        }

        return node;
    }

    private int applyHashAndGetBucketNum(Object key) {
        return key.hashCode() * 31 % data.length;
    }

    public static void main(String[] args) {
        System.out.println(Objects.hashCode(1));
    }

    public int size() {
        return data.length;
    }
}
/*
 * Problems I faced/Questions I got/Knowledge I got while implementing or building this.
 *
 * 1) How to implement a hash algorithm for storing integers?
 * 2) Would there be an ideal hash algorithm which generates unique hash for the given key?
 * 3) Found that, any hash algorithm might generate a non-unique hash for the key resulting collision and we can resolve
 *    that collision by using one of the approach "Having LinkedList for values"
 */