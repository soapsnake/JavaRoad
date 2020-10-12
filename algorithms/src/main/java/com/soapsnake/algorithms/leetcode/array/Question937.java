package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author soapsnake
 * @date 2018/11/13
 */
class Question937 {

    public static void main(String[] args) {
        Question937 question937 = new Question937();
        String[] strings = {"j mo", "5 m w", "g 07", "o 2 0", "t q h"};
        System.out.println(Arrays.toString(question937.reorderLogFiles(strings)));
        //["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
    }

    /**
     * 两种日志,一种数字日志,一种字母日志
     * 要求:重新排序字符串:
     * 1. 字母日志在数字日志之前
     * 2. 字母日志应该按照字母表升序排列
     * 3. 数字日志次序不能变
     */
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> myComp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int s1si = s1.indexOf(' ');      //空白字符
                int s2si = s2.indexOf(' ');
                char s1fc = s1.charAt(s1si + 1);  //刚好取到定义符后的第一个字符
                char s2fc = s2.charAt(s2si + 1);

                if (s1fc <= '9') {   //如果s1是数字日志
                    if (s2fc <= '9') return 0;  //如果s2也是数字日志,那么顺序不变
                    else return 1;   //如果s2是字母日志,那么字母日志应该在前
                }

                //到这里说明s1是字母日志
                if (s2fc <= '9') return -1;     //但是s2是数字日志,字母日志(s1)应该在前

                //到这里说明s1是字母日志,s2也是字母日志
                int preCompute = s1.substring(s1si + 1).compareTo(s2.substring(s2si + 1));
                if (preCompute == 0) {  //如果s1和s2字符串完全一样
                    return s1.substring(0, s1si).compareTo(s2.substring(0, s2si)); //这个不太懂
                }
                return preCompute;
            }
        };

        Arrays.sort(logs, myComp);
        return logs;
    }


}
