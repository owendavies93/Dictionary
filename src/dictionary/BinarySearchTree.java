package dictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Binary search tree based implementation of the Dictionary
 * interface. The nodes of the tree are ordered by an associated key-attribute
 * key of type K, such that every node's left subtree contains only nodes 
 * whose key-attributes are less than key, and every node's right subtree
 * contains only nodes whose key-attributes are greater than key. A
 * linear order is defined on keys through the Comparable interface.
 * Duplicate keys are not permitted.
 */
public class BinarySearchTree<K extends Comparable<? super K>, V> implements
        Dictionary<K, V> {

    @Override
    public Iterator<DictionaryEntry<K, V>> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public V get(K key) throws NoSuchElementException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void put(K key, V value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(K key) throws NoSuchElementException {
        // TODO Auto-generated method stub

    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }
}
