package dictionary;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Ordered linked list based implementation of the Dictionary
 * interface. The nodes of the list are ordered in ascending order by
 * the key-attribute of type K. Duplicate keys are not permitted.
 */
public class OrderedLinkedList<K extends Comparable<? super K>, V> implements
        Dictionary<K, V> {

    private OrderedLinkedListEntry<K, V> head;
    private int numElems;
    private int modCount;

    public OrderedLinkedList() {
        this.head = null;
        this.numElems = 0;
        this.modCount = 0;
    }

    @Override
    public int size() {
        return numElems;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public V get(K key) throws NoSuchElementException {
        OrderedLinkedListEntry<K, V> entry = getEntryAt(key);

        if (entry != null) {
            return entry.getValue();
        } else {
            throw new NoSuchElementException("Key not found");
        }
    }

    private OrderedLinkedListEntry<K, V> getEntryAt(K key) {
        if (size() == 0) {
            return null;
        }

        OrderedLinkedListEntry<K, V> curr = head;
        int count = 1;

        while (count < size() && curr.getKey() != key) {
            curr = curr.getNext();
            count++;
        }

        if (count >= size() && curr.getKey() != key) {
            return null;
        } else {
            return curr;
        }
    }

    @Override
    public void put(K key, V value) {
        OrderedLinkedListEntry<K, V> prev = findPrev(key);
        modCount++;

        if (prev == null) {
            head = new OrderedLinkedListEntry<K, V>(key, value);
            numElems++;
        } else if (prev.getKey().compareTo(key) == 0) {
            prev.setValue(value);
        } else if (prev.getKey().compareTo(key) < 0) {
            OrderedLinkedListEntry<K, V> entry =
                    new OrderedLinkedListEntry<K, V>(key, value);
            entry.setNext(prev.getNext());
            prev.setNext(entry);
            numElems++;
        } else {
            OrderedLinkedListEntry<K, V> oldHead = head;
            head = new OrderedLinkedListEntry<K, V>(key, value);
            head.setNext(oldHead);
            numElems++;
        }
    }

    private OrderedLinkedListEntry<K, V> findPrev(K searchKey) {
        OrderedLinkedListEntry<K, V> prev = head;

        if (prev != null && prev.getKey().compareTo(searchKey) < 0) {
            OrderedLinkedListEntry<K, V> curr = prev.getNext();

            while (curr != null && curr.getKey().compareTo(searchKey) < 0) {
                prev = curr;
                curr = curr.getNext();
            }
        }
        return prev;
    }

    @Override
    public void remove(K key) throws NoSuchElementException {
        OrderedLinkedListEntry<K, V> entry = getEntryAt(key);
        if (entry != null) {
            if (entry.equals(head)) {
                head = head.getNext();
            } else {
                OrderedLinkedListEntry<K, V> prev = findPrev(key);
                prev.setNext(entry.getNext());
            }

            numElems--;
            modCount++;
        } else {
            throw new NoSuchElementException("Key not found");
        }
    }

    @Override
    public void clear() {
        OrderedLinkedListEntry<K, V> curr = head;

        while (!isEmpty()) {
            remove(curr.getKey());
            curr = head;
        }
    }

    @Override
    public Iterator<DictionaryEntry<K, V>> iterator() {
        return new DictionaryIterator();
    }

    private class DictionaryIterator implements Iterator<DictionaryEntry<K, V>> {

        private OrderedLinkedListEntry<K, V> curr;
        private int oldModCount;

        public DictionaryIterator() {
            this.curr = head;
            this.oldModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public OrderedLinkedListEntry<K, V> next() {
            if (oldModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            OrderedLinkedListEntry<K, V> res = null;
            if (curr != null) {
                res = curr;
                curr = curr.getNext();
            }
            return res;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public String toString() {
        String res = "[";
        for (DictionaryEntry<K, V> e : this) {
            res += e + ", ";
        }
        return res + "]";
    }
}
