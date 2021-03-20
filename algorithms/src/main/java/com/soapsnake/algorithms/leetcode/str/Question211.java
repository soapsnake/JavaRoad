package com.soapsnake.algorithms.leetcode.str;

/**

 * Created on 2020-01-06
 */
public class Question211 {


    /**
     * kmp字符串搜索
     */
   public static class WordDictionary {
        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            this.root = new TrieNode();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            TrieNode node = this.root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.item = word;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any
         * one letter.
         */
        public boolean search(String word) {
            return this.match(word.toCharArray(), 0, this.root);
        }

        private boolean match(char[] chs, int k, TrieNode node) {
            if (k == chs.length) {
                //如果到达了匹配串的结尾还没有到达叶节点,举例,Trie是ba,模式串是bad,这种情况就是防止模式串比Trie还长
                return !node.item.equals("");
            }
            if (chs[k] != '.') {
                return node.children[chs[k] - 'a'] != null && match(chs, k + 1, node.children[chs[k] - 'a']);
            } else {
                for (int i = 0; i < node.children.length; i++) {
                    //如果是通配符,则匹配所有的子节点
                    if (node.children[i] != null) {
                        if (match(chs, k + 1, node.children[i])) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public class TrieNode {
            public TrieNode[] children;
            public String item;

            public TrieNode() {
                this.children = new TrieNode[26];
                this.item = "";
            }
        }
    }

    /**
     * addWord("bad")
     * addWord("dad")
     * addWord("mad")
     * search("pad") -> false
     * search("bad") -> true
     * search(".ad") -> true
     * search("b..") -> true
     */
    public static void main(String[] args) {

        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("WordDictionary");
        wordDictionary.addWord("addWord");
        wordDictionary.addWord("search");
        System.out.println(wordDictionary.search(""));
        System.out.println(wordDictionary.search("a"));
        ;
        System.out.println(wordDictionary.search("."));
    }
}
