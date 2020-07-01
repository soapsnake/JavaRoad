package com.soapsnake.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-30
 */
public class Question212 {

    //leetcode212
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs (board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode tree, List<String> res) {
        char c = board[i][j];
        if (c == '#' || tree.next[c - 'a'] == null) {
            return;
        }
        tree = tree.next[c - 'a'];
        if (tree.word != null) {   // found one,叶子节点才含有单词
            res.add(tree.word);
            tree.word = null;     // de-duplicate
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j ,tree, res);
        if (j > 0) dfs(board, i, j - 1, tree, res);
        if (i < board.length - 1) dfs(board, i + 1, j, tree, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, tree, res);
        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode tree = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (tree.next[i] == null) {
                    tree.next[i] = new TrieNode();
                }
                tree = tree.next[i];   //含有相同字符的单词在树中会相交
            }
            tree.word = w;  //只有叶子节点才有word
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];  //相当于children指针
        String word;   //相当于value
    }

    @Test
    public void test() {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        System.out.println(findWords(board, words));
    }

    public int arrangeCoins(int n) {
        int res = 0;
        for (int i = 1; ; i++) {
            n -= i;
            if (n < 0) {
                break;
            }
            res++;
        }
        return res;
    }

    @Test
    public void testArrange() {
        System.out.println(arrangeCoins(1));
    }
}
