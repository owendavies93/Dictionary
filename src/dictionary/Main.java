package dictionary;

import java.io.FileNotFoundException;

public class Main {

    private static final int MAX_SIZE = 500;
    private static final int REPITITIONS = 500;

    public static void main(String[] args) throws FileNotFoundException {
        OrderedLinkedList<Integer, String> l =
                new OrderedLinkedList<Integer, String>();

        l.put(2, "hello");
        l.put(5, "hi");
        l.put(1, "boo");
        l.put(7, "aeg");
        // l.remove(5);

        for (DictionaryEntry<Integer, String> e : l) {
            System.out.println(e);
        }
    }
}
