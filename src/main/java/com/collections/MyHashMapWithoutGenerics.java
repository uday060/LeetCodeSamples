package com.collections;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Objects;

public class MyHashMapWithoutGenerics {
    private static final int INITIAL_CAPACITY = 16;
    private int currentArrayLoad = 0;
    private double threshold = 0.75;
    private Node[] data;
    private int nonNullKeyCount = 0;

    public MyHashMapWithoutGenerics() {
        data = new Node[INITIAL_CAPACITY];
    }

    private Node[] reHash(Node[] array) {
        Node[] newData = new Node[array.length + INITIAL_CAPACITY];

        for (Node node : array) {
            if (node != null) {
                int bucketNum = applyHashAndGetBucketNum(node.getKey());

                newData[bucketNum] = node;
            }
        }

        return newData;
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        if (nonNullKeyCount >= data.length * threshold) {
            data = reHash(data);
        }

        Node node = new Node(key, value);
        int bucketNum = applyHashAndGetBucketNum(key);

        if (data[bucketNum] == null) {
            data[bucketNum] = node;
        } else {
            boolean hasFound = false;
            for (int i = 0; i < node.getCollisionLinkedList().size(); i++) {
                Node curNode = (Node) node.getCollisionLinkedList().get(i);
                if (curNode.getKey() == key) {
                    data[bucketNum].setValue(value);
                    curNode.setValue(value);
                    hasFound = true;
                    break;
                }
            }

            if (!hasFound) {
                node.getCollisionLinkedList().add(node);
            }
        }
        nonNullKeyCount++;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        Node node = getNode(key);
        if (node != null) {
            return node.getValue();
        }
        else {
            return -1;
        }
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int bucketNum = applyHashAndGetBucketNum(key);

        data[bucketNum] = null;
        nonNullKeyCount--;
    }

    private Node getNode(int key) {
        int bucketNum = applyHashAndGetBucketNum(key);

        Node node = data[bucketNum];
        if (node != null) {
            if (node.getCollisionLinkedList().size() == 1) {
                return node;
            } else {
                for (int i = 0; i < node.getCollisionLinkedList().size(); i++) {
                    Node curNode = (Node) node.getCollisionLinkedList().get(i);
                    if (curNode.getKey() == key) {
                        return curNode;
                    }
                }
            }
        }

        return node;
    }

    private int applyHashAndGetBucketNum(Object key) {
        return key.hashCode() % data.length;
    }

    public static void main(String[] args) {
        System.out.println(Objects.hashCode(1));
    }

    public int size() {
        return data.length;
    }

    private class Node {
        private int hash;
        private int key;
        private int value;

        @Getter
        private LinkedList<Node> collisionLinkedList = new LinkedList<>();

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.collisionLinkedList.add(this);
        }

        public int getValue(int key) {
            if(this.collisionLinkedList.size() == 1) {
                return this.value;
            } else {
                for (Node curNode : collisionLinkedList) {
                    if (curNode.getKey() == key) {
                        return curNode.getValue();
                    }
                }
            }


            return -1;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public LinkedList<Node> getCollisionLinkedList() {
            return collisionLinkedList;
        }

        public void setCollisionLinkedList(LinkedList<Node> collisionLinkedList) {
            this.collisionLinkedList = collisionLinkedList;
        }
    }
}
