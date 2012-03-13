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
        System.out.println(l.size());
    }
}
