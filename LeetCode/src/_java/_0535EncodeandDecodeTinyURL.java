package _java;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import base.Base;

public class _0535EncodeandDecodeTinyURL extends Base {

    private abstract static class Solution {
        // Encodes a URL to a shortened URL.
        public abstract String encode(String longUrl);

        // Decodes a shortened URL to its original URL.
        public abstract String decode(String shortUrl);
    }

    // 简单计数
    private static class Solution1 extends Solution {

        final static String TINY = "http://tinyurl.com/";
        Map<Integer, String> map = new HashMap<>();
        int i = Integer.MIN_VALUE;

        public String encode(String longUrl) {
            map.put(i, longUrl);
            return TINY + i++;
        }

        public String decode(String shortUrl) {
            String realURL = shortUrl.substring(TINY.length());
            return map.get(Integer.valueOf(realURL));
        }
    }

    // 62位字符进位 10 + 26 + 26
    private static class Solution2 extends Solution {
        final String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        HashMap<String, String> map = new HashMap<>();
        int count = 1;

        public String getString() {
            int c = count;
            StringBuilder sb = new StringBuilder();
            while (c > 0) {
                // 这句很重要，少这句的话效率会翻倍，15ms左右，有这句9ms左右，因为这是进位算法中很重要的一位,
                // 减去这位能够少一次操作。
                --c;
                sb.append(chars.charAt(c % 62));
                c /= 62;
            }
            return sb.toString();
        }

        public String encode(String longUrl) {
            String key = getString();
            map.put(key, longUrl);
            count++;
            return "http://tinyurl.com/" + key;
        }

        public String decode(String shortUrl) {
            return map.get(shortUrl.replace("http://tinyurl.com/", ""));
        }

    }

    // hash
    private static class Solution3 extends Solution {

        final static String TINY = "http://tinyurl.com/";
        HashMap<Integer, String> map = new HashMap<>();

        public String encode(String longUrl) {
            int hash = Objects.hashCode(longUrl);
            map.put(hash, longUrl);
            // 换成sb没什么区别
            return TINY + hash;
        }

        public String decode(String shortUrl) {
            return map.get(Integer.valueOf(shortUrl.replace(TINY, "")));
        }
    }

    // 随机数
    private static class Solution4 extends Solution {

        final static String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final static String TINY = "http://tinyurl.com/";
        Map<Integer, String> map = new HashMap<>();
        Random r = new Random();

        public String encode(String longUrl) {
            Integer i = r.nextInt(Integer.MAX_VALUE);
            while (map.containsKey(i)) {
                i = r.nextInt(Integer.MAX_VALUE);
            }
            map.put(i, longUrl);
            return TINY + i;
        }

        public String decode(String shortUrl) {
            return map.get(Integer.valueOf(shortUrl.replace(TINY, "")));
        }
    }

    // 牛逼
    private static class Solution5 extends Solution {

        final static String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final static String TINY = "http://tinyurl.com/";
        Map<String, String> map = new HashMap<>();
        Random r = new Random();

        private String getRand() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; ++i) {
                sb.append(CHARS.charAt(r.nextInt(62)));
            }
            return sb.toString();
        }

        public String encode(String longUrl) {
            String key = getRand();
            while (map.containsKey(key)) {
                key = getRand();
            }
            map.put(key, longUrl);
            return TINY + key;
        }

        public String decode(String shortUrl) {
            return map.get(shortUrl.replace(TINY, ""));
        }
    }

    public static void main(String[] args) {

        Solution s = new Solution5();
        for (int i = 0; i < 10000; ++i) {
            String url = "http://" + i + ".com";
            String tiny = s.encode(url);
            println(tiny);
            println(s.decode(tiny));
            s.decode(tiny);
        }

    }
}
