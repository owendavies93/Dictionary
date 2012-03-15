package dictionary;

import java.io.FileNotFoundException;

public class Main {

    private static final int MAX_SIZE = 500;
    private static final int REPITITIONS = 500;

    public static void main(String[] args) throws FileNotFoundException {
        OrderedLinkedList<Integer, String> l =
                new OrderedLinkedList<Integer, String>();

        l.put(3, "hello");
        l.put(1, "hi");
        l.put(5, "sup");
        l.put(2, "wow");
        l.put(6, "does");
        l.put(10, "this");
        l.put(12, "work");

        for (DictionaryEntry<Integer, String> e : l) {
            System.out.println(e);
        }
    }
}
