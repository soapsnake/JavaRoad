package com.soapsnake.algorithms.alib;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KMPtester {

    /**
     * 暴力模式匹配,复杂度m * n
     * @param s 目标串
     * @param p 模式串
     * @return
     */
    public static boolean violentSearch (String s, String p) {
        if (p == null || s == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();

        for (int i = 0; i < m; i++) {
            int start = i;
            for (int j = 0; j < n; j++) {
                if (start < m && s.charAt(start++) != p.charAt(j)) {
                    break;
                }
                if (j == (n - 1)) {
                    return true;
                }

            }
        }
        return false;
    }

    //找出s中是否有完全匹配p的字串
    /**
     * 以下是next[]数组构造的基本逻辑
     *  abcdab:
     *  a -> 没有
     *  b -> a  0
     *  c -> ab: b,a
     *  d -> abc: a,ab, c,bc      0
     *  a -> abcd: a,ab,abc, d, cd, bcd      0
     *  b -> abcda: a, ab, abc, abcd, a, da, cda, bcda   1(a有重复)
     *  : -> abcdab: a, ab, abc, abcd, abcda, b, ab, dab, cdab, bcdab  1 (ab重复)
     *  [0,0,0,0,1,1]
     *
     *  构造next[], 部分匹配表,实际上是前缀后缀的最长共有元素的记录表
     */
    public static boolean kmpMatch(String s, String p) {
        int[] nextIntkey = genNext(p);
        boolean res = false;
        int i = 0, j = 0;

        //kmp的最典型特征是: 文本串指针i绝不后退
        while (i < s.length() && j < p.length()) {
            if (nextIntkey[j] == -1 || s.charAt(i) == p.charAt(j)) {
                //当文本串与模式串匹配时
                i++;
                j++;
            } else {
                //失配时,模式串向右移动的位数 = 已匹配字符数 - 失配字符上一位字符对应的next[]中该字符对应的最大长度值
                j = nextIntkey[j];
            }
            if (j == p.length() - 1) {
                //当模式串与文本串完全匹配时
                res = true;
                break;
            }
        }
        return res;
    }


    /**
     * https://blog.csdn.net/v_july_v/article/details/7041827
     * https://www.cnblogs.com/tangzhengyue/p/4315393.html
     * 在该字符失配时,该字符对应的next值会告诉你下一步匹配中,模式串应该跳到哪个位置,
     * 如果next[j]等于0或-1,则跳到模式串的开头字符,弱next[j]=k且k>0,代表下次匹配跳到j之前的某个字符,而不是跳到开头,且具体跳过了k个字符.
     *
     * 比如对于aba来说，第3个字符a之前的字符串ab中有长度为0的相同前缀后缀，所以第3个字符a对应的next值为0；
     * 而对于abab来说，第4个字符b之前的字符串aba中有长度为1的相同前缀后缀a，所以第4个字符b对应的next值为1（相同前缀后缀的长度为k，k = 1）。
     *
     * 在已知 next[0...j]的情况下,如何去求next[j+1]了????
     * 对于ABCDABCE来说, 第二个C对应的子串为 ABCDAB  明显的next[indexOf(第二个C)] = 2,那么对于C之后的E了???其字串为ABCDABC,
     */
    private static int[] genNext(String p) {
        //next数组的计算可以看做也是一个字符串模式匹配问题，其中主串就是整个模式串，子串是每一个位置的前子串
        //next数组所有元素的值都不会超过模式串p的长度
        int[] next = new int[p.length()];
        int j = 0;
        int k = -1;
        int len = p.length();
        //这个第0个元素为-1其实很关键
        next[0] = -1;
        while (j < len - 1) {
            //next[j] 的值是根据next[j - 1] 的值得出来的!!!!!!!!!!
            if (k == -1 || p.charAt(k) == p.charAt(j)) {
                //相当于匹配
                //如果next[j-1] = k && p.charAt(k) == p.charAt(j) 那么 next[j] = next[j - 1] + 1 = k + 1
                j++;
                k++;
                next[j] = k;
            } else {
                //相当于失配
                //p.charAt(k) != p.charAt(j),失配的情况下,前缀中递归寻找可以和后缀匹配的字符串
                k = next[k];
            }
        }
        return next;
    }

    @Test
    public  void testSome() {
        String s = "BBC ABCDAB ABCDABCDABDE";
        String p = "ABCDABE";


        System.out.println(violentSearch(s,p));
    }









    private static int[] genNext2(String p) {
        int[] nextIntkey = new int[p.length()];
        for (int i = 0; i < p.length(); i++) {
            String sub = p.substring(0, i + 1);
            Set<String> set = new HashSet<>();
            //部分匹配表是针对sub来构建的
            int key = 0;
            for (int j = 0; j < i; j++) {
                String prestr = sub.substring(0, j + 1);
                System.out.println("prestr = " + prestr);
                if (!set.add(prestr)) {
                    System.out.println("重复元素 =" + prestr);
                    key = prestr.length();
                }
                String postStr = sub.substring(j + 1, i + 1);
                System.out.println("postStr = " + postStr);
                if (!set.add(postStr)) {
                    System.out.println("重复元素 =" + postStr);
                    key = postStr.length();
                }
            }
            System.out.println("sub = " + sub + " 结束!");
            nextIntkey[i] = key;
        }
        return nextIntkey;
    }


}
