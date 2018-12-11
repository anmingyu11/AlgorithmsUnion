package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import base.Base;

public class WordLadder2 extends Base {

    static class Solution1 {

        final List<List<String>> results = new ArrayList<>();
        // 邻接图
        final Map<String, List<String>> adjGraph = new HashMap<>();

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            Set<String> set = new HashSet<>();
            // Detect endWord has or not
            boolean hasEnd = false;
            for (String word : wordList) {
                //Todo 影响效率?。
                if (!hasEnd && word.equals(endWord)) {
                    hasEnd = true;
                }
                set.add(word);
            }
            return hasEnd ? findLadders(beginWord, endWord, set) : results;
        }

        public List<List<String>> findLadders(String start, String end, Set<String> dict) {
            if (dict.size() == 0) {
                return results;
            }

            // 步数数组
            Map<String, Integer> ladder = new HashMap<>();
            // bfs等待队列
            Queue<String> queue = new ArrayDeque<>();

            for (String w : dict) {
                ladder.put(w, Integer.MAX_VALUE);
            }
            ladder.put(start, 0);//init first ladder to step 0
            queue.add(start);

            int min = Integer.MAX_VALUE;
            while (!queue.isEmpty()) {
                String word = queue.poll();

                //current step
                int step = ladder.get(word) + 1;

                if (step > min) {
                    break;
                }

                for (int i = 0; i < word.length(); ++i) {
                    char[] chars = word.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        chars[i] = ch;
                        String newWord = new String(chars);

                        Integer val = ladder.get(newWord);
                        if (val != null) {
                            if (step > val) {
                                // make sure we have the shortest step
                                continue;
                            } else if (step < val) {
                                // update step and enqueue
                                ladder.put(newWord, step);
                                queue.add(newWord);
                            }
                            // It is a KEY line. If one word already appeared in one ladder,
                            // Do not insert the same word inside the queue twice. Otherwise it gets TLE.

                            List<String> adjList = adjGraph.get(newWord);
                            if (adjList != null) {
                                adjList.add(word);
                            } else {
                                adjList = new LinkedList<>();
                                adjList.add(word);
                                adjGraph.put(newWord, adjList);
                            }

                            if (word.equals(end)) {
                                min = step;
                            }

                        }// if contains
                    }// loop the a-z
                }// loop the word character from 0-len

            }

            List<String> list = new LinkedList<>();
            backTrace(end, start, list);

            return results;
        }

        private void backTrace(String word, String start, List<String> list) {
            if (word.equals(start)) {
                list.add(0, start);
                results.add(new ArrayList<>(list));
                list.remove(0);
                return;
            }

            list.add(0, word);
            List<String> adjs = adjGraph.get(word);
            if (adjs != null) {
                for (String adj : adjs) {
                    backTrace(adj, start, list);
                }
            }
            list.remove(0);

        }
    }

    public static void main(String[] args) {
        String[] str1s = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        String[] str2s = new String[]{"hot", "dot", "dog", "lot", "log"};
        String[] str3s = new String[]{"ted", "tex", "red", "tax", "tad", "den", "rex", "pee"};
        List<String> wordList1 = Arrays.asList(str1s);
        List<String> wordList2 = Arrays.asList(str2s);
        List<String> wordList3 = Arrays.asList(str3s);
        //println(new Solution1().findLadders("hot", "cog", wordList1));
        println(new Solution1().findLadders("red", "tax", wordList3));
    }
}
