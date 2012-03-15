package dictionary;

import dictionary.DictionaryEntry;

// Implementation class representing nodes of the binary search tree.
public class BinarySearchTreeEntry<K, V> implements DictionaryEntry<K, V> {

    private K key;
    private V value;
    private BinarySearchTreeEntry<K, V> left, right;

    public BinarySearchTreeEntry(K key, V value,
            BinarySearchTreeEntry<K, V> left, BinarySearchTreeEntry<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinarySearchTreeEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public BinarySearchTreeEntry<K, V> getLeft() {
        return left;
    }

    public void setLeft(BinarySearchTreeEntry<K, V> l) {
        left = l;
    }

    public BinarySearchTreeEntry<K, V> getRight() {
        return right;
    }

    public void setRight(BinarySearchTreeEntry<K, V> r) {
        right = r;
    }

    @Override
    public K getKey() {
        return key;
    }

    public void setKey(K k) {
        key = k;
    }

    @Override
    public V getValue() {
        return value;
    }

    public void setValue(V v) {
        value = v;
    }

    @Override
    public String toString() {
        return key + " -> " + value;
    }

}
