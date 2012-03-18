package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

import dictionary.InsertComplexities.InstrumentedKey;

public class Main {

    private static final int MAX_SIZE = 500;
    private static final int REPITITIONS = 500;

    public static void main(String[] args) throws FileNotFoundException {

        Random rand = new Random();
        InsertComplexities iC = new InsertComplexities(rand);
        PrintStream pS;

        File oFile =
                new File(System.getProperty("user.dir")
                        + "\\OrderedLinkedList.dat");

        int[] oComplexities =
                iC.getInsertComplexities(
                        new OrderedLinkedList<InstrumentedKey, Integer>(),
                        MAX_SIZE, REPITITIONS);
        pS = new PrintStream(oFile);
        for (int i = 0; i < REPITITIONS; i++) {
            pS.println(i + "\t" + oComplexities[i]);
        }

        File bFile =
                new File(System.getProperty("user.dir")
                        + "\\BinarySearchTree.dat");

        int[] bComplexities =
                iC.getInsertComplexities(
                        new BinarySearchTree<InstrumentedKey, Integer>(),
                        MAX_SIZE, REPITITIONS);
        pS = new PrintStream(bFile);
        for (int i = 0; i < REPITITIONS; i++) {
            pS.println(i + "\t" + bComplexities[i]);
        }
    }
}
