package hotciv;

import hotciv.variants.FractalMap.FractalMapStrategy;

/**
 * Created by asger on 15/12/15.
 */
public class RunFractalMap {

    public static void main(String[] args){
        shouldTestFractalOutput();
    }

    public static void shouldTestFractalOutput() {
        FractalMapStrategy fractal = new FractalMapStrategy();
        String[] worldMap = fractal.fractalStringMapCall();
        for(int x = 0; x < worldMap.length; x++){
            System.out.println(worldMap[x]);
        }
    }
}
