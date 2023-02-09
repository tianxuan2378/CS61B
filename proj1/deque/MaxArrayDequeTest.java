package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

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
        MaxArrayDeque<Integer> m = new MaxArrayDeque<>(cmp);
        m.addFirst(1);
        m.addFirst(2);
        m.addFirst(7);
        m.addFirst(4);
        int maxInt = m.max();
        assertEquals(7, maxInt);
    }

    @Test
    public void largerStringTest() {
        Comparator<String> cmp1 = getStringComparator();
        MaxArrayDeque<String> m = new MaxArrayDeque<>(cmp1);
        m.addLast("anna");
        m.addFirst("elsa");
        m.addLast("bob");
        String lastAppear = m.max();
        assertEquals("elsa", lastAppear);
    }
}
