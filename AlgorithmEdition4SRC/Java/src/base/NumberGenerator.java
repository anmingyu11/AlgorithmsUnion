package base;

import java.util.Random;

public class NumberGenerator {

    public static Integer[] generateArrSequence(int min, int max) {
        Integer[] a = new Integer[max - min];
        for (int i = min; i < max; ++i) {
            a[i] = i;
        }
        return a;
    }

    public static Integer[] generateArrRandom(int len, int min, int max) {
        Integer[] a = new Integer[len];
        for (int i = 0; i < len; ++i) {
            a[i] = randInt(min, max);
        }

        return a;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
