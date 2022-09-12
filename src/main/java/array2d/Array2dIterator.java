package array2d;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Iterates a 2D array following a "spiral" pattern. e.g. the following array:
 * <code>
 * {
 *     {1,  2,  3,  4},
 *     {5,  6,  7,  8},
 *     {9, 10, 11, 12},
 * }
 * </code>
 * will be traversed in the following order: <code>1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7</code>
 * <p>
 * This class assumes the array is a well-defined matrix: all rows in the array have
 * the same length. If that is not the case, unexpected exceptions like IndexOutOfBoundsException
 * might be thrown.
 */
public class Array2dIterator implements Iterator<Integer> {

    // Array to iterate
    private final int[][] array;

    // Defines a window in the array where traversal is allowed.
    // This window closes as the traversal progress. Left and top
    // are inclusive, while right and bottom are exclusive.
    private int limitLeft;
    private int limitRight;
    private int limitTop;
    private int limitBottom;

    // Controls the direction of the traversal
    private int dx;
    private int dy;

    // The current position in the array
    private int x;
    private int y;

    /**
     * Creates an iterator that traverse a 2D array in a "spiral" pattern.
     * The array must be a well-defined matrix: all rows must have the same width.
     *
     * @param array the 2D array to traverse.
     */
    public Array2dIterator(int[][] array) {
        this.array = Objects.requireNonNull(array, "array must not be null");

        final int width = (array.length > 0) ? array[0].length : 0;
        final int height = array.length;

        // The initial window is the whole array
        limitLeft = 0;
        limitRight = width;
        limitTop = 0;
        limitBottom = height;

        // Start moving to the right
        dx = 1;
        dy = 0;

        // Start in the top-left corner of the array
        x = 0;
        y = 0;
    }

    @Override
    public boolean hasNext() {
        // It has a next element if the current position is valid inside the array window
        return (x >= limitLeft && x < limitRight) &&
                (y >= limitTop && y < limitBottom);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        // Get the result before modifying the current position
        final int result = array[y][x];

        // Calculate next position
        final int nextX = x + dx;
        final int nextY = y + dy;

        if (nextX >= limitRight) {
            // Crossed the right limit: the top row was
            // traversed, and we should move now down
            limitTop += 1;
            dx = 0;
            dy = 1;
        } else if (nextY >= limitBottom) {
            // Crossed the bottom limit: the right column was
            // traversed, and we should move now left
            limitRight -= 1;
            dx = -1;
            dy = 0;
        } else if (nextX < limitLeft) {
            // Crossed the left limit: the bottom row was
            // traversed, and we should move now up
            limitBottom -= 1;
            dx = 0;
            dy = -1;
        } else if (nextY < limitTop) {
            // Crossed the top limit: the left column was
            // traversed, and we should move now right
            limitLeft += 1;
            dx = 1;
            dy = 0;
        }

        // Move in the current direction
        x += dx;
        y += dy;

        return result;
    }

    /**
     * Returns a list with the remaining elements in the iterator.
     *
     * @return a list with elements in the order defined by this iterator
     */
    public List<Integer> toList() {
        final Iterable<Integer> iterable = () -> this;
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
