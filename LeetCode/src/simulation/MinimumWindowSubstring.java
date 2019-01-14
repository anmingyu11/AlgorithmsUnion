package simulation;

import java.util.HashMap;
import java.util.Map;

import base.Base;

public class MinimumWindowSubstring extends Base {

    public static String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        // Dictionary which keeps a count of all the unique characters int t.
        Map<Character, Integer> dictT = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); ++i) {
            char c = t.charAt(i);
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(c, count + 1);
        }

        // Number of unique characters in t, which need to be present in the desired window.
        int required = dictT.size();

        // Left and Right pointer
        int l = 0, r = 0;

        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;

        // Dictionary which keeps a count of all the unique characters in the current window.
        Map<Character, Integer> windowCounts = new HashMap<>();

        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};

        while (r < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = s.charAt(l);
                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }

                // Move the left pointer ahead, this would help to look for a new window.
                l++;
            }

            // Keep expanding the window once we are done contracting.
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    public static String minWindow2(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> dictT = new HashMap<>();
        for (int i = 0; i < t.length(); ++i) {
            char c = t.charAt(i);
            int count = dictT.getOrDefault(c, 0);
            dictT.put(c, ++count);
        }

        int required = dictT.size();
        int formed = 0;
        int l = 0, r = 0;
        Map<Character, Integer> windowCounts = new HashMap<>();

        int[] resultArr = {-1, 0, 0};

        while (r < s.length()) {
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, ++count);

            if (dictT.containsKey(c)
                    && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                ++formed;
            }

            while (l <= r && formed == required) {
                char lc = s.charAt(l);
                if (resultArr[0] == -1 || r - l + 1 < resultArr[0]) {
                    resultArr[0] = r - l + 1;
                    resultArr[1] = l;
                    resultArr[2] = r;
                }

                windowCounts.put(lc, windowCounts.get(lc) - 1);
                if (dictT.containsKey(lc)
                        && windowCounts.get(lc) < dictT.get(lc)) {
                    --formed;
                }

                ++l;
            }

            ++r;
        }

        return resultArr[0] == -1 ? "" : s.substring(resultArr[1], resultArr[2] + 1);
    }

    public static void main(String[] args) {
        String s = "ABAACBAB";
        String t = "ABC";
    }

}
