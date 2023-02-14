package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    //*  creates a MaxArrayDeque with the given Comparator. */
    public MaxArrayDeque(Comparator<T> c) {
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
        if (size == 0) {
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
