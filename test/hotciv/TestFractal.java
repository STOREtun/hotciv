package hotciv;

import hotciv.variants.FractalMap.FractalMapStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by asger on 15/12/15.
 */
public class TestFractal{
    @Test
    public void shouldTestFractalOutput() {
        FractalMapStrategy fractal = new FractalMapStrategy();
        String[] worldMap = fractal.fractalStringMapCall();
        for(int x = 0; x < worldMap.length; x++){
            System.out.println(worldMap[x]);
        }
    }
}
