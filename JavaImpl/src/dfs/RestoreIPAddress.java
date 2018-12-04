package dfs;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class RestoreIPAddress extends Base {

    static class Solution1 {

        public List<String> restoreIpAddresses(String s) {
            List<String> res = new LinkedList<>();
            if (s.length() > 12 || s.length() < 4) {
                return res;
            }
            dfs(s, res, "", 0, 0);
            return res;
        }

        private void dfs(String ip, List<String> res, String restoredIP, int pos, int count) {
            // 终止
            if (count > 4) {
                return;
            }
            // 收敛
            if (count == 4 && pos == ip.length()) {
                res.add(restoredIP);
                return;
            }

            // 4bit ip
            for (int i = 1; i < 4; ++i) {
                if (pos + i > ip.length()) {
                    return;
                }
                String subIp = ip.substring(pos, pos + i);
                if (subIp.startsWith("0") && subIp.length() > 1
                        || i == 3 && Integer.parseInt(subIp) > 255) {
                    return;
                }

                dfs(ip, res, restoredIP + subIp + (count == 3 ? "" : "."), pos + i, count + 1);
            }

        }

    }

    static class Solution2 {

        public List<String> restoreIpAddresses(String s) {
            List<String> res = new LinkedList<>();
            if (s.length() > 12 || s.length() < 4) {
                return res;
            }
            for (int a = 1; a <= 3; ++a) {
                for (int b = 1; b <= 3; ++b) {
                    for (int c = 1; c <= 3; ++c) {
                        for (int d = 1; d <= 3; ++d) {
                            if (a + b + c + d == s.length()) {
                                String subA = s.substring(0, a);
                                if (subA.startsWith("0") && subA.length() > 1) {
                                    break;
                                }
                                String subB = s.substring(a, a + b);
                                if (subB.startsWith("0") && subB.length() > 1) {
                                    break;
                                }
                                String subC = s.substring(a + b, a + b + c);
                                if (subC.startsWith("0") && subC.length() > 1) {
                                    break;
                                }
                                String subD = s.substring(a + b + c, a + b + c + d);
                                if (subD.startsWith("0") && subD.length() > 1) {
                                    break;
                                }
                                if (Integer.valueOf(subA) <= 255
                                        && Integer.valueOf(subB) <= 255
                                        && Integer.valueOf(subC) <= 255
                                        && Integer.valueOf(subD) <= 255) {
                                    StringBuilder sb = new StringBuilder();
                                    res.add(sb.append(subA).append(".").append(subB).append(".").append(subC).append(".").append(subD).toString());
                                }
                            }
                        }
                    }
                }

            }
            return res;
        }

    }

    public static void main(String[] args) {
        String ip = "25525511135";
        String ip2 = "010010";
        //println(new Solution().restoreIpAddresses(ip));
        //println(new Solution2().restoreIpAddresses(ip));
        println(new Solution2().restoreIpAddresses(ip2));
    }
}
