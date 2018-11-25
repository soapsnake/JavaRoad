package com.soapsnake.algorithms.leetcode.number;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soapsnake
 * @date 2018/11/5
 */
class Question412 {

    //todo 避免使用取余运算可以加快运行速度,想一下不用取余数怎么解决
    public List<String> fizzBuzz(int n) {
        if (n <= 0) {
            return null;
        }
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                res.add("FizzBuzz");
                continue;
            }
            if (i % 3 == 0) {
                res.add("Fizz");
                continue;
            }
            if (i % 5 == 0) {
                res.add("Buzz");
                continue;
            }
            res.add(i + "");
        }
        return res;
    }

}
