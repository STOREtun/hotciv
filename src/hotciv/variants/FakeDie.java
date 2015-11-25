package hotciv.variants;

import hotciv.framework.Die;

/**
 * Created by asger on 25/11/15.
 *
 * Using the FakeDie results in following die roll results
 * the first attacker rolls 6 and first defender 0
 * the second attacker rolls 0 and first defender 6
 * the third attacker rolls 1 and first defender 1
 *
 */

public class FakeDie implements Die {

    int[] rollQueue;
    int counter;

    public FakeDie() {
        rollQueue = new int[]{6, 0, 0, 6, 1, 1};
        counter = -1;
    }

    @Override
    public int rollDie() {
        counter++;
        if (counter > rollQueue.length) counter = 0;
        return rollQueue[counter];
    }
}
