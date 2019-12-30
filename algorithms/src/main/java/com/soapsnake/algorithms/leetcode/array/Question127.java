package com.soapsnake.algorithms.leetcode.array;

import java.util.List;

/**
 * @author
 * Created on 2019-12-30
 *
 * Example 1:
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 *
 *
 * Example 2:
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class Question127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.isEmpty()) {
            return 0;
        }
        if (!wordList.contains(endWord)) {
            return 0;
        }





        return 0;
    }
}
