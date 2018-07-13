package com.ld.leetcode.dynamic;

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
    //https://leetcode.com/problems/longest-palindromic-substring/solution/
    //中文解释可见:https://www.felix021.com/blog/read.php?2040
    public String longestPalindromeDPSolution(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
