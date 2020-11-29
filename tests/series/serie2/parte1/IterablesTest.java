package series.serie2.parte1;


import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IterablesTest {

    protected static <T> List<T> singleton(T v) {
        return Collections.singletonList(v);
    }

    protected static <T> List<T> unmodifiable(List<T> s) {
        return Collections.unmodifiableList(s);
    }

    protected static <T> void assertIterableEquals(Iterable<T> expected, Iterable<T> result) {
        Iterator<T> itE = expected.iterator(),
                itR = result.iterator();
        while (itE.hasNext()) {
            assertTrue(itR.hasNext());
            assertEquals(itE.next(), itR.next());
        }
        assertFalse(itR.hasNext());
    }


    protected static <T> void assertIterableEmpty(Iterable<T> result) {
        assertFalse(result.iterator().hasNext());
    }

    protected static <T> void testNextWithoutHasNext(Iterable<T> expected, Iterable<T> result) {
        Iterator<T> itE = expected.iterator(),
                itR = result.iterator();
        while (itE.hasNext()) {
            assertEquals(itE.next(), itR.next());
        }
        assertFalse(itR.hasNext());
    }

    protected static <T> void testMultipleIterations(Iterable<T> expected, Iterable<T> result) {
        Iterator<T> itR, itE;
        for (int i = 0; i < 2; ++i) {
            itE = expected.iterator();
            itR = result.iterator();
            while (itR.hasNext()) {
                assertEquals(itE.next(), itR.next());
            }
            assertFalse(itE.hasNext());
        }
    }

    protected static <T> void testDoubleHasNext(Iterable<T> expected, Iterable<T> result) {
        Iterator<T> itE = expected.iterator(),
                itR = result.iterator();
        while (itR.hasNext()) {
            assertTrue(itR.hasNext());
            assertEquals(itE.next(), itR.next());
        }
        assertFalse(itR.hasNext());
        assertFalse(itE.hasNext());
    }


    protected static <T> void test(Iterable<T> expected, Iterable<T> result) {
        assertIterableEquals(expected, result);
        testNextWithoutHasNext(expected, result);
        testDoubleHasNext(expected, result);
        testMultipleIterations(expected, result);
    }
}
