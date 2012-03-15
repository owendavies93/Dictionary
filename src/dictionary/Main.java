package dictionary;

import java.io.FileNotFoundException;

public class Main {

    private static final int MAX_SIZE = 500;
    private static final int REPITITIONS = 500;

    public static void main(String[] args) throws FileNotFoundException {
        BinarySearchTree<Integer, String> l =
                new BinarySearchTree<Integer, String>();

        l.put(4, "hello");
        l.put(7, "hi");
        l.put(1, "sup");
        l.put(2, "fuck");
        l.put(5, "shit");
        l.remove(2);
    }
}
