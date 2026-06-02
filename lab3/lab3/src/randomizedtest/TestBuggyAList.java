package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove()
        {
        AListNoResizing<Integer> a=new AListNoResizing<>();
        BuggyAList<Integer> b=new BuggyAList<>();
        for(int i=4;i<=6;i++)
        {
          a.addLast(i);
          b.addLast(i);
        }
        for(int i=0;i<3;i++)
        {
          int alost=a.removeLast();
          int blost=b.removeLast();
          assertEquals(blost,alost);
        }

    }


    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
            ...
            }
        }
}
