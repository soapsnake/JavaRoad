package com.ld.leetcode.str;

public class Question771 {

    public static int numJewelsInStones(String J, String S) {
        if (J == null || J.equals("") || S == null || S.equals("")) {
            return 0;
        }
        int i = 0;
        char[] jelly = J.toCharArray();
        char[] stone = S.toCharArray();
        for (int k = 0; k < jelly.length; k++) {
            for (int j = 0; j < stone.length; j++) {
                if (stone[j] == jelly[k]) {
                    i++;
                }
            }
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA", "aAAbbbb"));

        System.out.println(numJewelsInStones("z", "ZZ"));

        System.out.println(numJewelsInStones(null, ""));
    }


}
