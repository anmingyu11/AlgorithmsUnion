package base.util;

public class MathUtil {

    public static boolean isEven(int num) {
        if ((num & 1) == 1) {
            return false;
        } else {
            return true;
        }
    }
}
