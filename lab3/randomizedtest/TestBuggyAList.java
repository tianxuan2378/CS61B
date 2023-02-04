package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE

//    Test the addLast and removeLast method
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> al = new AListNoResizing();
        BuggyAList<Integer> bugal = new BuggyAList();

        al.addLast(4);
        al.addLast(5);
        al.addLast(6);

        bugal.addLast(4);
        bugal.addLast(5);
        bugal.addLast(6);
        assertEquals(al.removeLast(), bugal.removeLast());
        assertEquals(al.removeLast(), bugal.removeLast());
        assertEquals(al.removeLast(), bugal.removeLast());
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> bugL = new BuggyAList();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                bugL.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int bugsize = bugL.size();
                assertEquals(size, bugsize);
//                System.out.println("size: " + size);
            }
            else if(operationNumber == 2){
                //getLast
                int a = 0, b = 0;
                if(L.size() > 0)
                   a = L.getLast();
                if(bugL.size() > 0)
                    b =  bugL.getLast();
                assertEquals(a, b);
            }
            else{
                //removeLast
                int a = 0, b = 0;
                if(L.size() > 0)
                    a = L.removeLast();
                if(bugL.size() > 0)
                    b = bugL.removeLast();
                assertEquals(a, b);
            }
        }
    }
}
