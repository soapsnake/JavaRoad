package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Google,Baidu 搜索栏自动补全提示系统,例如: 输入goo 弹出提示:google
 */
public class AutocompleteSystem {
    private Map<String, Integer> map;  //word -> count
    private StringBuilder sb;
    private TrieNode root;
    private TrieNode current;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        sb = new StringBuilder();
        root = new TrieNode();
        current = root;
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], times[i]);
            root.add(sentences[i], 0);
        }
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            String str = sb.toString();
            map.put(str, map.getOrDefault(str, 0) + 1);
            root.add(str, 0);
            sb = new StringBuilder();
            current = root;
            return new ArrayList<>();
        }
        sb.append(c);
        current = current.getNext(c);
        return current.top3;
    }

    private class TrieNode {
        //这个tops3的含义是以这个节点为前缀的top3输入字符串
        private List<String> top3;
        private TrieNode[] next;
        
        private TrieNode() {
            this.top3 = new ArrayList<>();
            this.next = new TrieNode[27];
        }
        
        private void add(String s, int start) {
            //每一个节点都存储了一个Top3?
            if (!top3.contains(s)) top3.add(s);
            Collections.sort(top3, (a, b) -> {
                if (map.get(a) == map.get(b)) return a.compareTo(b);
                return map.get(b) - map.get(a);
            });
            if (top3.size() > 3) top3.remove(top3.size() - 1);

            //这一行是用来终止TireNode节点指针
            if (start == s.length()) return;
            
            //node.add 会驱动指针往叶子节点移动
            getNext(s.charAt(start)).add(s, start + 1);
        }
        
        private TrieNode getNext(char ch) {
            int index = ch == ' ' ? 26 : ch - 'a';
            if (next[index] == null) next[index] = new TrieNode();
            return next[index];
        }
    }
}

