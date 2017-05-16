package Sparse;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by lpf on 17/4/26.
 */
public class SpaceTest {
    public static void main(String[] args) throws Exception{
        for (int i = -10; i < 10; i++) {
            System.out.println(i + " >> 1 = " + (i >> 1));
        }
        System.out.println("----------------");
        /**
         *  -3 -2 -1 | 0 1 2
         */
        for (int i = -5; i < 10; i++) {
            System.out.println("~" + i + " = " + ~i);
        }

        String[] s1 = new String[]{"a", "b", "c", "d"};
        String[] es = insert(s1, 4, 4, "e");
        System.out.println(Arrays.asList(es));

        int[] array = new int[]{1, 2, 5, 8 ,9};
        binarySearch(array, 5, 6);
    }

    public static <T> T[] insert(T[] array, int currentSize, int index, T element) {
        assert currentSize <= array.length;

        if (currentSize + 1 <= array.length) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = element;
            return array;
        }

        T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(),
                (currentSize * 2));
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = element;
        System.arraycopy(array, index, newArray, index + 1, array.length - index);
        return newArray;
    }

    static int binarySearch(int[] array, int size, int value) {
        int lo = 0;
        int hi = size - 1;

        while (lo <= hi) {
            final int mid = (lo + hi) >>> 1;
            final int midVal = array[mid];

            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal > value) {
                hi = mid - 1;
            } else {
                return mid;  // value found
            }
        }
        return ~lo;  // value not present
    }
}

/*   00000001
0 >>> 1 = 0
1 >>> 1 = 0
2 >>> 1 = 1
3 >>> 1 = 1
4 >>> 1 = 2
5 >>> 1 = 2
6 >>> 1 = 3
7 >>> 1 = 3
8 >>> 1 = 4
9 >>> 1 = 4
----------------
~-5 = 4
~-4 = 3
~-3 = 2
~-2 = 1
~-1 = 0
~0 = -1
~1 = -2
~2 = -3
~3 = -4
~4 = -5
~5 = -6
~6 = -7
~7 = -8
~8 = -9
~9 = -10
*/