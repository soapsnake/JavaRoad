package com.soapsnake.algorithms.weekly;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-05-29
 * JavaRoad
 */
public class WeeklyContext295 {



    public int rearrangeCharacters(String s, String target) {
        //读题: 从s中选出字符组成target, 要求是不能重用字符
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int count = 0;
        System.out.println(map);
        int res = 0;
        boolean stop = false;
        while (!stop) {
            for (char c : target.toCharArray()) {
                System.out.println("c = " + c + " count = " + map.get(c));
                count = map.getOrDefault(c, 0);
                if (count == 0) {
                    stop = true;
                    break;
                }
                map.put(c, count - 1);
            }
            res++;
        }
        return res - 1;
    }





    public String discountPrices(String sentence, int discount) {
        //读题 .1 sentect里面的价格有可能不合法,这种不能替换. 2.算discount后价格要保留两位小数,
        String[] sen = sentence.split(" ");
        BigDecimal dis = new BigDecimal(100 - discount).divide(BigDecimal.valueOf(100), 4, RoundingMode.FLOOR);
        for (int i = 0; i < sen.length; i++) {
            String price = sen[i];
            if (valid(price)) {
                System.out.println(price);
                BigDecimal p = new BigDecimal(price.substring(1));
                double tmp = p.multiply(dis).doubleValue();
                price = "$" + p.multiply(dis).setScale(2, RoundingMode.HALF_UP);
                sen[i] = price;
            }
        }
        return String.join( " ", sen);
    }

    private boolean valid(String price) {
        if(price == null) {
            return false;
        }
        if (!price.contains("$")) {
            return false;
        }
        if (price.length() == 1) {
            return false;
        }
        if (price.charAt(0) != '$') {
            return false;
        }
        for (int i = 0; i < price.length(); i++) {
            if (i != 0 && price.charAt(i) == '$') {
                return false;
            }
            if (i != 0 && !Character.isDigit(price.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        WeeklyContext295 weeklyContext295 = new WeeklyContext295();
        String s = "ilovecodingonleetcode";
        String t = "code";

        //s = "abbaccaddaeea";
        //t = "aaaaa";
        //System.out.println(weeklyContext295.rearrangeCharacters(s, t));
        String sentence = "there are $1 $2 and 5$ candies in the shop";
        int discount = 50;
        sentence = "f32eir5f6hlmmtnlq$zno3zbl5pr26b1xmet6q3rjzs422zqzsezpgi4jqx3h0olb428pk95qndkfz8hereio$2ewx0cnqlvnb6nl$$8iny7t4aemhnqzz6971rnq7pha97e9lf16227j5l2033pnddk $3513024 $516863 $604 $9128265 $945728 $nbf 5az21pm0tj $";
        discount = 26;
        //System.out.println(weeklyContext295.discountPrices(sentence, discount));

        int[] nums = {4,5,7,7,13};
        System.out.println(weeklyContext295.totalSteps(nums));
    }


    public int totalSteps2(int[] nums) {
        //只要求出哪个数字需要移除的比他小的数字的最多数量?
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmp = 0;
            int j = i + 1;
            while (j < nums.length && nums[j] < nums[i]) {
                System.out.println("hehre");
                tmp++;
                j++;
            }
            res = Math.max(tmp, res);
        }
        return res;
    }

    public int totalSteps(int[] nums) {
        //只要求出哪个数字需要移除的比他小的数字的最多数量?
        int res = 0;
        Set<Integer> removed = new HashSet<>();
        while (true) {
            boolean dizeng  = true;
            for (int i = 0; i < nums.length; i++) {
                if (removed.contains(i)) {
                    continue;
                }
                int j = i + 1;
                while (j < nums.length && nums[i] > nums[j] && !removed.contains(j)) {
                    removed.add(j);
                    dizeng = false;
                    j++;
                }
                System.out.print(removed);
            }
            res++;
            if (dizeng) {
                System.out.println("fdsafdaf");
                if (removed.size() == 0) return 0;
                return res + 1;
            }
        }
    }

}