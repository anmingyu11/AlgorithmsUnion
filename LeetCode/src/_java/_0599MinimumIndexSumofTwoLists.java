package _java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import base.BaseLinkedList;

/**
 * Suppose Andy and Doris want to choose a restaurant for dinner,
 * and they both have a list of favorite restaurants represented by strings.
 * <p>
 * You need to help them find out their common interest with the least list index sum.
 * If there is a choice tie between answers, output all of them with no order requirement.
 * You could assume there always exists an answer.
 * <p>
 * Example 1:
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * Output: ["Shogun"]
 * Explanation: The only restaurant they both like is "Shogun".
 * <p>
 * Example 2:
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["KFC", "Shogun", "Burger King"]
 * Output: ["Shogun"]
 * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
 * <p>
 * Note:
 * The length of both lists will be in the range of [1, 1000].
 * The length of strings in both lists will be in the range of [1, 30].
 * The index is starting from 0 to the list length minus 1.
 * No duplicates in both lists.
 */
public class _0599MinimumIndexSumofTwoLists extends BaseLinkedList {
    private static abstract class Solution {
        public abstract String[] findRestaurant(String[] list1, String[] list2);
    }

    private static class Solution1 extends Solution {

        public String[] findRestaurant(String[] list1, String[] list2) {
            Map<String, Integer> map = new HashMap<>(list1.length);
            LinkedList<String> res = new LinkedList<>();
            for (int i = 0; i < list1.length; ++i) {
                map.put(list1[i], i);
            }
            int minIndexSum = Integer.MAX_VALUE;
            for (int i = 0; i < list2.length; ++i) {
                String str = list2[i];
                Integer idx1 = map.get(str);
                if (idx1 == null) {
                    continue;
                }
                int sum = idx1 + i;
                if (sum < minIndexSum) {
                    res = new LinkedList<>();
                    res.add(str);
                    minIndexSum = sum;
                } else if (sum == minIndexSum) {
                    res.add(str);
                }
            }
            return res.toArray(new String[res.size()]);
        }

    }

    private static class Solution2 extends Solution {
        @Override
        public String[] findRestaurant(String[] list1, String[] list2) {
            int len1 = list1.length, len2 = list2.length;
            if (len1 > len2) {
                return findRestaurant(list2, list1);
            }
            Map<String, Integer> map = new HashMap<>();
            LinkedList<String> res = new LinkedList<>();
            for (int i = 0; i < len1; ++i) {
                map.put(list1[i], i);
            }
            int minIndexSum = Integer.MAX_VALUE;
            for (int i = 0; i < len2 && i <= minIndexSum; ++i) {
                String str = list2[i];
                Integer idx1 = map.get(str);
                if (idx1 == null) {
                    continue;
                }
                int sum = idx1 + i;
                if (sum < minIndexSum) {
                    res = new LinkedList<>();
                    res.add(str);
                    minIndexSum = sum;
                } else if (sum == minIndexSum) {
                    res.add(str);
                }
            }
            return res.toArray(new String[res.size()]);
        }
    }

    public static void main(String[] args) {
        String[] A1 = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] A2 = new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};

        Solution s = new Solution2();

        printArr(s.findRestaurant(A1, A2));
    }
}
