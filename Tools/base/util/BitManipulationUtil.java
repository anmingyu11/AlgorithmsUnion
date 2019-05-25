package base.util;

public class BitManipulationUtil {

    public static int oneCount(int x) {
        int count;
        for (count = 0; x != 0; ++count) {
            x &= x - 1;
        }
        return count;
    }

}
