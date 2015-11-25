package hotciv.variants;

import hotciv.framework.Die;

import java.util.Random;

/**
 * Created by asger on 25/11/15.
 */
public class RandomDie implements Die {
    @Override
    public int rollDie() {
        int lowerBound = 1;
        int upperBound = 6;

        return (new Random().nextInt(upperBound)) + lowerBound;
    }
}
