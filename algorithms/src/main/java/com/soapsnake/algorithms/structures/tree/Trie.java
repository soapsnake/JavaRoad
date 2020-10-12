package com.soapsnake.algorithms.structures.tree;

/**
 * 前缀树
 */
public class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie);
        System.out.println(trie.search("apple"));
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!curNode.containsKey(currentChar)) {
                curNode.put(currentChar, new TrieNode());
            }
                //其实是dfs,沿着根节点往下遍历
            curNode = curNode.get(currentChar);
        }

        //到这里word已经插完,可以打tag标明该节点对应了一个完结的单词
        curNode.setEnd();
    }

    /**
     * 搜索指定单词前缀
     * @param word
     * @return 返回null标明该Trie树不包含该单词
     */
    private TrieNode searchPrefix(String word) {
        TrieNode curNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (curNode.containsKey(currentChar)) {
                curNode = curNode.get(currentChar);
            } else {
                return null;
            }
        }
        return curNode;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode end = this.searchPrefix(word);

        //end必须结束否则word有可能只是一个前缀
        return end != null && end.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode end = this.searchPrefix(prefix);
        return end != null;
    }

    class TrieNode {
        // R links to node children
        private TrieNode[] childs;

        private final int R = 26;

        private boolean isEnd = false;

        public TrieNode() {
            childs = new TrieNode[R];
        }

        //该节点是否包含对应字符的子节点
        public boolean containsKey(char ch) {
            return childs[ch -'a'] != null;
        }
        public TrieNode get(char ch) {
            return childs[ch -'a'];
        }

        //给该节点添加一个孩子节点
        public void put(char ch, TrieNode node) {
            childs[ch -'a'] = node;
        }

        //标明该节点是一个单词的结尾,注意了一旦被做了这个标记,是没有办法翻身了
        public void setEnd() {
            isEnd = true;
        }
        public boolean isEnd() {
            return isEnd;
        }
    }
}
