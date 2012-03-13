package dictionary;

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

    public OrderedLinkedList() {
        this.head = null;
        this.numElems = 0;
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
        OrderedLinkedListEntry<K, V> curr = head;
        int count = 0;

        do {
            curr = curr.getNext();
            count++;
        } while (curr.getKey() != key || count > size());

        if (count > size()) {
            return null;
        } else {
            return curr;
        }
    }

    @Override
    public void put(K key, V value) {
        OrderedLinkedListEntry<K, V> prev = findPrev(key);

        if (prev == null) {
            head = new OrderedLinkedListEntry<K, V>(key, value);
            numElems++;
        } else if (prev.getKey().compareTo(key) == 0) {
            prev.setValue(value);
        } else if (prev.getKey().compareTo(key) < 0) {
            OrderedLinkedListEntry<K, V> entry =
                    new OrderedLinkedListEntry<K, V>(key, value);
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

            while (curr != null && curr.getKey().compareTo(searchKey) <= 0) {
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
        } else {
            throw new NoSuchElementException("Key not found");
        }
    }

    @Override
    public void clear() {
        OrderedLinkedListEntry<K, V> curr = head;

        while (!isEmpty()) {
            remove(curr.getKey());
        }
    }

    @Override
    public Iterator<DictionaryEntry<K, V>> iterator() {
        return new DictionaryIterator<K, V>();
    }

    private class DictionaryIterator<K, V> implements
            Iterator<DictionaryEntry<K, V>> {

        private OrderedLinkedListEntry<K, V> curr;

        @SuppressWarnings("unchecked")
        public DictionaryIterator() {
            this.curr = (OrderedLinkedListEntry<K, V>) head;
            // TODO
        }

        @Override
        public boolean hasNext() {
            return curr.getNext() != null;
        }

        @Override
        public DictionaryEntry<K, V> next() {
            if (curr == null) {
                return null;
            } else {
                curr = curr.getNext();
                return curr;
            }
        }

        @Override
        public void remove() {
            if (curr != null) {
                OrderedLinkedList.this.remove(curr.getKey());
            }
        }

    }
}
