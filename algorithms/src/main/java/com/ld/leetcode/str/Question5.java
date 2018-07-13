package com.ld.leetcode.str;

public class Question5 {

    //答案正确,但是解法复杂度过高,n平方
    public String longestPalindrome(String s) {
        if (s == null) return "";
        if (s.equals("")) return "";
        if (s.equals(" ")) return "";
        if (s.length() == 1) return s;
        int max = Integer.MIN_VALUE;
        String maxStr = "";
        StringBuilder sb;
        for (int i =0;i<s.length(); i++){
            if (s.length() - i < max) return maxStr;   //最大长度不可能超过max了,后面不算了
            sb = new StringBuilder();
            for (int j=i;j<s.length();j++){
                sb.insert(0, s.charAt(j));
                if (sb.length() == 1) continue;
                if (s.substring(i,j+1).equals(sb.toString())){
                        if (sb.length() > max){
                            max = sb.length();
                            maxStr = sb.toString();
                        }
                    }
                }
        }
        if (maxStr.equals("")){
            return s.charAt(0)+"";
        }else{
            return maxStr;
        }
    }

    public static void main(String[] args) {
        Question5 question5 = new Question5();
        String str1 = "babad";
        String str2 = "cbbd";
        System.out.println(question5.longestPalindrome2(str2));
    }


    //解法复杂度还是过高
    public String longestPalindrome2(String s) {
        if (s == null) return "";
        if (s.equals("")) return "";
        if (s.equals(" ")) return "";
        if (s.length() == 1) return s;
        int leftStr = 0;
        int rightStr = 0;
        for (int i=0;i<s.length();i++){
            for (int j=0; i - j >=0; j++){
                leftStr =j;
                rightStr = i - j;
                String s1 = s.substring(leftStr, s.length() - rightStr);
                if (this.checkPalindrome(s1))
                    return s1;
            }
        }
        return s.charAt(0)+"";
    }
        //校验字符串是否回文结构
    public boolean checkPalindrome(String source){
        StringBuilder sb = new StringBuilder("");
        for (int i=0;i<source.length();i++){
            sb.insert(0,source.charAt(i));
        }
        return source.equals(sb.toString());
    }

    //动态规划,正确得解法,不过没有看明白
    public String longestPalindromeDPSolution(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxStart = 0;
        int maxEnd = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length() - i; j++) {
                if (i == 0 || (s.charAt(j) == s.charAt(j + i) && (i == 1 || dp[j + 1][j + i - 1]))) {
                    dp[j][j + i] = true;
                    maxStart = j;
                    maxEnd = j + i;
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }
}
