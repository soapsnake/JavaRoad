package com.ld.leetcode.number;

import java.util.HashMap;
import java.util.Map;

/**
 * @author soapsnake
 * @date 2018/11/24
 */
public class Question9 {
    /**
     * //如果第一位比第二位小, 那么两位组合起来表示一个数字
     *         //V前面只能出现I
     *         //X前面只能出现I
     *         //L前面只能出现X
     *         //C前面只能出现X
     *         //D前面只能出现C
     *         //M前面只能出现C
     *
     *         //如果第一位比第二位大, 那么第一位直接解析,第二位和第三位接着比较
     *
     *         //如果组合,M后只能出现C
     *         //D后面只能出现C
     *         //C后面只能出现X
     *         //X后面只能出现I和V
     */
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        char[] chars = s.toCharArray();
        int res = 0;
        boolean needFix = true;
        for (int i = 0; i < chars.length - 1; i++) {
            if (map.get(chars[i]) >= map.get(chars[i + 1])) {
                //前面大于后面
                res += map.get(chars[i]);
            } else {
                //前面小与后面,后面的数减前面的
                res += map.get(chars[i + 1]) - map.get(chars[i]);
                ++i;  //两个数字连起来表示一个数字,因此指针要跳一位
                if (i + 1 == chars.length) {  //最后两个字母表示一个数字,因此不需要补偿
                    needFix = false;
                }
            }
        }
        if (needFix) { //需要补偿最后一位,只有非双字符的情况才需要补偿
            res += map.get(chars[chars.length - 1]);
        }
        return res;
    }

    public static void main(String[] args) {
        Question9 question9 = new Question9();
        System.out.println(question9.romanToInt("MCMXCIV"));
    }
}
