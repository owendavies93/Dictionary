package dictionary;

public class OrderedLinkedListEntry<K, V> implements DictionaryEntry<K, V> {

    private K key;
    private V value;
    private OrderedLinkedListEntry<K, V> next;

    public OrderedLinkedListEntry(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    public void setValue(V newVal) {
        this.value = newVal;
    }

    public OrderedLinkedListEntry<K, V> getNext() {
        return next;
    }

    public void setNext(OrderedLinkedListEntry<K, V> next) {
        this.next = next;
    }
}
