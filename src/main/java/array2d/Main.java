package array2d;

public class Main {

    public static void main(String[] args) {
        final var array = new int[][]{
                { 1,  2,  3,  4},
                { 5,  6,  7,  8},
                { 9, 10, 11, 12},
                {13, 14, 15, 16},
        };
        final var iterator = new Array2dIterator(array);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + (iterator.hasNext() ? "," : ""));
        }
    }
}
