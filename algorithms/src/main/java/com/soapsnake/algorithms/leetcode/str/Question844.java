package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-20 00:56
 */
public class Question844 {
    public static void main(String[] args) {
        Question844 question844 = new Question844();
        String s = "xywrrmp";
        String t = "xywrrmu#p";
        System.out.println(question844.backspaceCompare(s, t));
    }

    public boolean backspaceCompare(String S, String T) {
        char[] charS = S.toCharArray();
        char[] charT = T.toCharArray();
        this.iteer(charS);
        this.iteer(charT);
        String resS = this.buildStr(charS);
        String resT = this.buildStr(charT);
        return resS.equals(resT);
    }

    private String buildStr(char[] chars) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            if (c != ' ') {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    private void iteer(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '#') {
                chars[i] = ' ';

                //有可能连续出现多个'#'的情况,所以需要把所有的全部改成空字符
                for (int j = i; j >= 0; ) {
                    if (chars[j] != ' ') {
                        chars[j] = ' ';
                        break;
                    } else {
                        j--;
                    }
                }
            }
        }
    }
}
