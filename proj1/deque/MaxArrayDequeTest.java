package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    private static class IntComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private static class StringComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static Comparator<Integer> getIntComparator(){
        return new IntComparator();
    }

    public static Comparator<String> getStringComparator(){
        return new StringComparator();
    }

    @Test
    public void largerIntTest() {
        Comparator<Integer> cmp = getIntComparator();
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(cmp);
        mad.addFirst(1);
        mad.addFirst(2);
        mad.addFirst(7);
        mad.addFirst(4);
        int maxInt = mad.max();
        assertEquals(7, maxInt);
    }

    @Test
    public void largerStringTest() {
        Comparator<String> cmp1 = getStringComparator();
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(cmp1);
        mad.addLast("anna");
        mad.addFirst("elsa");
        mad.addLast("bob");
        String lastAppear = mad.max();
        assertEquals("elsa", lastAppear);
    }
}
