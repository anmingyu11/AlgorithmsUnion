package base.util;

public class StringUtil {

    public static String spaces(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(" ");
        }
        return sb.toString();
    }

}
