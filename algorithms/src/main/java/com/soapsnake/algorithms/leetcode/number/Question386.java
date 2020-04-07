package com.soapsnake.algorithms.leetcode.number;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created on 2020-01-20
 */
public class Question386 {

    //排序规则:假如数字是120, 那么1,11,111,112,2,21.....按这种次序来排
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>(n);
        int curr = 1;
        for (int i = 1; i <= n; i++) {
            list.add(curr);
            if (curr * 10 <= n) {
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) {
                curr++;
            } else {
                while ((curr / 10) % 10 == 9) {
                    curr /= 10;
                }
                curr = curr / 10 + 1;
            }
        }
        return list;
    }
}
