package com.soapsnake.algorithms.leetcode.dynamic;

class Question5 {

    public static void main(String[] args) {
        Question5 question5 = new Question5();
        String str1 = "babad";
        String str2 = "cbbd";
        System.out.println(question5.longestPalindrome2(str2));
    }

    //答案正确,但是解法复杂度过高,n平方
    public String longestPalindrome(String s) {
        if (s == null) return "";
        if (s.equals("")) return "";
        if (s.equals(" ")) return "";
        if (s.length() == 1) return s;
        int max = Integer.MIN_VALUE;
        String maxStr = "";
        StringBuilder sb;
        for (int i = 0; i < s.length(); i++) {
            if (s.length() - i < max) return maxStr;   //最大长度不可能超过max了,后面不算了
            sb = new StringBuilder();
            for (int j = i; j < s.length(); j++) {
                sb.insert(0, s.charAt(j));
                if (sb.length() == 1) continue;
                if (s.substring(i, j + 1).equals(sb.toString())) {
                    if (sb.length() > max) {
                        max = sb.length();
                        maxStr = sb.toString();
                    }
                }
            }
        }
        if (maxStr.equals("")) {
            return s.charAt(0) + "";
        } else {
            return maxStr;
        }
    }

    //解法复杂度还是过高
    public String longestPalindrome2(String s) {
        if (s == null) return "";
        if (s.equals("")) return "";
        if (s.equals(" ")) return "";
        if (s.length() == 1) return s;
        int leftStr = 0;
        int rightStr = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; i - j >= 0; j++) {
                leftStr = j;
                rightStr = i - j;
                String s1 = s.substring(leftStr, s.length() - rightStr);
                if (this.checkPalindrome(s1))
                    return s1;
            }
        }
        return s.charAt(0) + "";
    }

    //校验字符串是否回文结构
    public boolean checkPalindrome(String source) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < source.length(); i++) {
            sb.insert(0, source.charAt(i));
        }
        return source.equals(sb.toString());
    }

    //https://leetcode.com/problems/longest-palindromic-substring/solution/
    //中文解释可见:https://www.felix021.com/blog/read.php?2040
    /**
     * 动态规划算法
     */
    private int lo;       //最长子字符串左索引
    private int maxLen;  //最长子字符串最大长度(右边界的偏移量)
    public String longestPalindromeDPSolution(String s) {
       if (s.length() < 2) {
           return s;
       }
       for (int i = 0; i < s.length(); i++) {
           this.extendPalindrome(s, i ,i);   //以第i个字符为中心点往两边扩散,那么子字符串长度将为奇数
           this.extendPalindrome(s, i, i + 1); //以第i和i+1个字符为中心点往两边扩散,那么子字符串长度将为偶数
       }
       return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() - 1 && s.charAt(left) == s.charAt(right)) { //左右指针不是聚拢而是往两边扩散
            left--;
            right++;
        }
        if (maxLen < right - left - 1) {  //能进这个if说明这个子字符串的长度更加的长
            lo = left + 1;
            maxLen = right - left - 1;
        }
    }
}
