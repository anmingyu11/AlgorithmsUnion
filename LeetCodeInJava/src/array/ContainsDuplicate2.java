package array;

import java.util.HashSet;
import java.util.Set;

import base.Base;

public class ContainsDuplicate2 extends Base {

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        int k = 3;
        println("contains : " + containsNearbyDuplicate(nums1, k));
    }
}
