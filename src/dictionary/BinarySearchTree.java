package dictionary;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

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

    private BinarySearchTreeEntry<K, V> root;
    private int numElems;
    private int modCount;

    public BinarySearchTree() {
        this.root = null;
        this.numElems = 0;
        this.modCount = 0;
    }

    @Override
    public int size() {
        return numElems;
    }

    @Override
    public BinarySearchTreeEntry<K, V> getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public V get(K key) throws NoSuchElementException {
        BinarySearchTreeEntry<K, V> node = checkEntry(root, key);

        if (node != null) {
            return node.getValue();
        } else {
            throw new NoSuchElementException("Key not found");
        }
    }

    private BinarySearchTreeEntry<K, V> checkEntry(
            BinarySearchTreeEntry<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        if (node.getKey().compareTo(key) == 0) {
            return node;
        } else if (node.getKey().compareTo(key) < 0) {
            return checkEntry(node.getLeft(), key);
        } else {
            return checkEntry(node.getRight(), key);
        }
    }

    @Override
    public void put(K key, V value) {
        insertElem(root, key, value);
    }

    private BinarySearchTreeEntry<K, V> insertElem(
            BinarySearchTreeEntry<K, V> node, K k, V v) {
        if (node == null) {
            if (root == null) {
                root = new BinarySearchTreeEntry<K, V>(k, v);
            } else {
                node = new BinarySearchTreeEntry<K, V>(k, v);
            }
            numElems++;
            modCount++;
        } else if (node.getKey().compareTo(k) == 0) {
            node.setValue(v);
        } else if (node.getKey().compareTo(k) < 0) {
            node.setRight(insertElem(node.getRight(), k, v));
        } else {
            node.setLeft(insertElem(node.getLeft(), k, v));
        }
        return node;
    }

    @Override
    public void remove(K key) throws NoSuchElementException {
        deleteElem(root, key);
    }

    private BinarySearchTreeEntry<K, V> deleteElem(
            BinarySearchTreeEntry<K, V> node, K key) {
        if (node == null) {
            throw new NoSuchElementException("Key not found");
        } else if (node.getKey().compareTo(key) == 0) {
            if (node.equals(root)) {
                root = deleteNode(node);
            } else {
                node = deleteNode(node);
            }

            modCount++;
            numElems--;
        } else if (node.getKey().compareTo(key) > 0) {
            node.setLeft(deleteElem(node.getLeft(), key));
        } else {
            node.setRight(deleteElem(node.getRight(), key));
        }
        return node;
    }

    private BinarySearchTreeEntry<K, V> deleteNode(
            BinarySearchTreeEntry<K, V> node) {
        if (node.getLeft() == null || node.getRight() == null) {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                return null;
            }
        } else {
            BinarySearchTreeEntry<K, V> newNode = findLeft(node.getRight());
            BinarySearchTreeEntry<K, V> newRight = deleteLeft(node.getRight());
            newNode.setRight(newRight);
            newNode.setLeft(node.getLeft());
            return newNode;
        }
    }

    private BinarySearchTreeEntry<K, V> findLeft(
            BinarySearchTreeEntry<K, V> node) {
        if (node.getLeft() == null) {
            return node;
        } else {
            return findLeft(node.getLeft());
        }
    }

    private BinarySearchTreeEntry<K, V> deleteLeft(
            BinarySearchTreeEntry<K, V> node) {
        if (node.getLeft() == null) {
            return node.getRight();
        } else {
            BinarySearchTreeEntry<K, V> newChild = deleteLeft(node.getLeft());
            node.setLeft(newChild);
            return node;
        }
    }

    @Override
    public void clear() {
        deleteAll(root);
    }

    private void deleteAll(BinarySearchTreeEntry<K, V> node) {
        if (node == null) {
            return;
        }

        if (node.getLeft() != null) {
            deleteAll(node.getLeft());
        }

        if (node.getRight() != null) {
            deleteAll(node.getRight());
        }

        remove(node.getKey());
    }

    @Override
    public Iterator<DictionaryEntry<K, V>> iterator() {
        return new DictionaryIterator();
    }

    private class DictionaryIterator implements Iterator<DictionaryEntry<K, V>> {

        private BinarySearchTreeEntry<K, V> curr;
        private int oldModCount;
        Stack<BinarySearchTreeEntry<K, V>> nodes =
                new Stack<BinarySearchTreeEntry<K, V>>();
        private int count;

        public DictionaryIterator() {
            this.curr = root;
            this.oldModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return count < size();
        }

        @Override
        public DictionaryEntry<K, V> next() {
            if (oldModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            while (curr != null) {
                nodes.push(curr);
                curr = curr.getLeft();
            }

            BinarySearchTreeEntry<K, V> res = null;
            if (!nodes.empty()) {
                res = nodes.pop();
                curr = res.getRight();
            }

            count++;
            return res;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
