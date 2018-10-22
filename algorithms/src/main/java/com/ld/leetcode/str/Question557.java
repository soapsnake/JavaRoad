package com.ld.leetcode.str;

/**
 * Given a string, you need to reverse the order of characters in each word within a sentence
 * while still preserving whitespace and initial word order.
 *
 * Input: "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 */
public class Question557 {

    //解法复杂度比较高 ,平方级别
    public String reverseWords(String s) {
        if (null == s)
            return null;
        if ("".equals(s))
            return "";

        String[] strings =  s.split(" ");
        String res = "";

        for (int j=0; j<strings.length;j++){
            char[] chars = strings[j].toCharArray();
            for (int i=0;i< chars.length / 2;i++){
                char temp = chars[i];
                chars[i] = chars[chars.length - i - 1];
                chars[chars.length - i - 1] = temp;
            }
            res += new String(chars);
            if (j != strings.length - 1)
                res += " ";
        }
        return res;
    }

    //只遍历数组一遍的算法,双指针, todo 需要自己实现一遍
    public String reverseWords2(String s) {
        char[] ca = s.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if (ca[i] != ' ') {   // when i is a non-space
                int j = i;
                while (j + 1 < ca.length && ca[j + 1] != ' ') {
                    j++;
                } // move j to the end of the word
                reverse(ca, i, j);
                i = j;
            }
        }
        return new String(ca);
    }

    private void reverse(char[] ca, int i, int j) {
        for (; i < j; i++, j--) {
            char tmp = ca[i];
            ca[i] = ca[j];
            ca[j] = tmp;
        }
    }

        public static void main(String[] args) {
        Question557 question557 = new Question557();

        String s = "Let's take LeetCode contest";
        System.out.println(question557.reverseWords2(s));
    }
}
