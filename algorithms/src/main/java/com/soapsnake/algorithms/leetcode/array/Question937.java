package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author soapsnake
 * @date 2018/11/13
 */
class Question937 {

    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> comparator = (o1, o2) -> {
            //1. 字母日志要在数字日志之前
            if (isStrLog(o1) && !isStrLog(o2)) {
                return -1;
            }
            //1. 字母日志要在数字日志之前
            if (!isStrLog(o1) && isStrLog(o2)) {
                return 1;
            }

            //2. 字母日志按字典序
            if (isStrLog(o1) && isStrLog(o2)) {
                int res0 = compareChar(getChar(o1, 0), getChar(o2, 0));
                if (res0 == 0) { //如果第一个字符相同则继续比较第二个字符
                    return compareChar(getChar(o1, 1), getChar(o2, 1));
                } else {
                    return res0;
                }
            }

            //3. 数字日志按原始顺序
            if (!isStrLog(o1) && !isStrLog(o2)) {
                return 0;
            }
            return 0;
        };
        Arrays.sort(logs, comparator);
        return logs;
    }

    private int compareChar(char c1, char c2) {
        if (c1 - c2 > 0) {
            return 1;
        } else if (c1 - c2 < 0) {
            return -1;
        }else {
            return 0;
        }
    }

    private char getChar(String log, int index) {
        String[] strings = log.split(" ");
        char[] chars = strings[1].toCharArray();
        return chars[index];
    }

    private boolean isStrLog(String log) {
        String[] strings = log.split(" ");
        try {
            Integer.parseInt(strings[1]);
            return false;
        }catch (Exception e) {
            return true;
        }
    }

    public static void main(String[] args) {
        Question937 question937 = new Question937();
        String[] strings = {"j mo", "5 m w","g 07","o 2 0","t q h"};
        System.out.println(Arrays.toString(question937.reorderLogFiles(strings)));
        //["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
    }


}
