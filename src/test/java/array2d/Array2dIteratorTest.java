package array2d;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class Array2dIteratorTest {

    @Test
    void test_iterator_empty() {
        var array = new int[][]{};
        var iterator = new Array2dIterator(array);
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void test_iterator_1x1() {
        var array = new int[][]{{1}};
        var iterator = new Array2dIterator(array);
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void test_iterator_1xN() {
        var array = new int[][]{{1, 2, 3, 4}};
        var iterator = new Array2dIterator(array);
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(4, iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void test_iterator_Mx1() {
        var array = new int[][]{
                {1},
                {2},
                {3},
                {4},
        };
        var iterator = new Array2dIterator(array);
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(4, iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void test_iterator_MxN() {
        var array = new int[][]{
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9, 10, 11, 12},
        };
        var iterator = new Array2dIterator(array);
        assertTrue(iterator.hasNext());
        assertEquals( 1, iterator.next());
        assertEquals( 2, iterator.next());
        assertEquals( 3, iterator.next());
        assertEquals( 4, iterator.next());
        assertEquals( 8, iterator.next());
        assertEquals(12, iterator.next());
        assertEquals(11, iterator.next());
        assertEquals(10, iterator.next());
        assertEquals( 9, iterator.next());
        assertEquals( 5, iterator.next());
        assertEquals( 6, iterator.next());
        assertEquals( 7, iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void test_traversal_using_list_empty() {
        var array = new int[][]{};
        var iterator = new Array2dIterator(array);
        assertEquals(List.of(), iterator.toList());
    }

    @Test
    void test_traversal_using_list_1x1() {
        var array = new int[][]{{1}};
        var iterator = new Array2dIterator(array);
        assertEquals(List.of(1), iterator.toList());
    }

    @Test
    void test_traversal_using_list_1xN() {
        var array = new int[][]{{1, 2, 3, 4}};
        var iterator = new Array2dIterator(array);
        assertEquals(List.of(1, 2, 3, 4), iterator.toList());
    }

    @Test
    void test_traversal_using_list_Mx1() {
        var array = new int[][]{
                {1},
                {2},
                {3},
                {4},
        };
        var iterator = new Array2dIterator(array);
        assertEquals(List.of(1, 2, 3, 4), iterator.toList());
    }

    @Test
    void test_traversal_using_list_MxN() {
        var array = new int[][]{
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9, 10, 11, 12},
        };
        var iterator = new Array2dIterator(array);
        assertEquals(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7), iterator.toList());
    }

}