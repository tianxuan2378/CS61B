package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;
    private int size;
    private int startsize = 8;  //The starting size of the array should be 8.
    private T[] items;
    private int nextfirst;   // This array is a circled array
    private int nextlast;
    //*  creates a MaxArrayDeque with the given Comparator. */
    public MaxArrayDeque(Comparator<T> c) {
        items = (T[]) new Object[startsize];
        size = 0;
        nextfirst = 2;
        nextlast = 3;
        cmp = c;
    }

    //*  returns the maximum element in the deque as governed by the previously given Comparator.
    // If the MaxArrayDeque is empty, simply return null. */
    public T max() {
        if (size == 0) {
            return null;
        }
        int index = addone(nextfirst);
        T max = items[index];
        for (int i = 1; i < size; i++) {
            index = addone(index);
            if (cmp.compare(items[index], max) > 0) {
                max = items[index];
            }
        }
        return max;
    }

    //* returns the maximum element in the deque as governed by the parameter Comparator c.
    // If the MaxArrayDeque is empty, simply return null. */
    public T max(Comparator<T> c) {
        if(size == 0) {
            return null;
        }
        int index = addone(nextfirst);
        T max = items[index];
        for (int i = 1; i < size; i++) {
            index = addone(index);
            if (c.compare(items[index], max) > 0) {
                max = items[index];
            }
        }
        return max;
    }
}
