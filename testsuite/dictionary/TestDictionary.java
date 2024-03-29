package dictionary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dictionary.Dictionary;
import dictionary.DictionaryEntry;

public abstract class TestDictionary {

    Dictionary<String, Integer> d;

    @Before
    public abstract void setUp();

    @After
    public abstract void tearDown();

    @Test
    public void testIsEmpty() {
        assertTrue("isEmpty() failed for empty dictionary", d.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        d.put("Tigger", 20);
        assertFalse("isEmpty() failed for dictionary with one element",
                d.isEmpty());
    }

    @Test
    public void testSizeWithItems() {
        List<String> cats =
                Arrays.asList("Growltiger", "Rum Tum Tugger", "Jellicles",
                        "Mungojerrie", "Rumpelteazer");
        for (String cat : cats) {
            d.put(cat, cat.hashCode());
        }
        assertEquals("size() failed for multiple cats", cats.size(), d.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetException() {
        d.get("Tiddles");
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveEmpty() {
        d.remove("Grizabella");
    }

    @Test
    public void testListIteratorMany() {
        List<String> cats =
                Arrays.asList("practical", "dramatical", "pragmatical",
                        "fanatical", "oratorical", "delphioracle", "skeptical",
                        "dispeptical", "romantical", "pedantical", "critical",
                        "parasitical", "allegorical", "metaphorical",
                        "statistical", "mystical", "political", "hypocritical",
                        "clerical", "hysterical", "cynical", "rabbinical");
        for (int i = 0; i < cats.size(); i++) {
            d.put(cats.get(i), i);
        }

        List<String> sortedCats = new ArrayList<String>(cats);
        Collections.sort(sortedCats);

        Iterator<String> expected = sortedCats.iterator();
        Iterator<DictionaryEntry<String, Integer>> actual = d.iterator();

        while (expected.hasNext()) {
            assertTrue("Iterator hasNext() failed when expected",
                    expected.hasNext());

            String expectedCat = expected.next();
            DictionaryEntry<String, Integer> actualCat = actual.next();

            assertEquals("Iterator next() returned the wrong element",
                    expectedCat, actualCat.getKey());

        }
        assertFalse("Iterator hasNext() failed at the end of the dictionary",
                expected.hasNext());

    }

    @Test(expected = ConcurrentModificationException.class)
    public void testListIteratorConcurrent() {
        List<String> cats = Arrays.asList("Bustopher", "Gus", "Skimbleshanks");

        for (int i = 0; i < cats.size(); i++) {
            d.put(cats.get(i), i);
        }

        Iterator<DictionaryEntry<String, Integer>> it = d.iterator();

        d.remove("Bustopher");

        it.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testListIteratorRemoveUnimplemented() {
        d.iterator().remove();
    }

    @Test
    public void testClear() {
        d.put("hello", 4);
        d.put("hi", 6);
        d.put("testing123", 1);
        d.clear();
        assertTrue("clear() failed", d.size() == 0);
    }

    @Test
    public void testRemove() {
        d.put("hello", 10);
        d.put("hi", 7);
        d.remove("hello");
        assertTrue("element not removed on remove()", d.size() == 1);
        assertTrue("head/root not fixed after remove()",
                d.getRoot().getKey() == "hi");
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeepRemove() {
        d.put("hello", 4);
        d.put("hi", 7);
        d.put("sup", 1);
        d.put("testing", 2);
        d.put("test", 5);
        d.remove("testing");
        assertEquals("incorrect element removed", d.get("testing"),
                new NoSuchElementException("Key not found"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testDoubleDelete() {
        d.put("hello", 1);
        d.remove("hello");
        d.remove("hello");
    }

    @Test
    public void testSizeAfterAddition() {
        d.put("hello", 3);
        assertTrue("size did not change to 1", d.size() == 1);
        d.put("hi", 1);
        assertTrue("size did not change to 2", d.size() == 2);
    }
}
