package com.soapsnake.algorithms.leetcode.array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * Created on 2020-06-16
 */
public class Question638 {

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        //这个map主要是用来优化的
        Map<List<Integer>, Integer> map = new HashMap<>();
        return backTrace(price, special, needs, 0, map);
    }
    private int backTrace(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int cur, Map<List<Integer>, Integer> map) {
        if (price == null || price.size() == 0 || needs == null || needs.size() == 0) {
            return 0;
        }
        if (map.containsKey(needs)) {
            return map.get(needs);
        }
        int ans = 0;
        for (int i = 0; i < price.size(); i++) {
            ans += price.get(i) * needs.get(i);
        }
        for (int j = cur; j < special.size(); j++) {
            List<Integer> item = special.get(j);
            List<Integer> copy = new LinkedList<>(needs);
            int i = 0;
            for (i = 0; i < copy.size(); i++) {
                if (copy.get(i) < item.get(i)) {
                    break;
                }
                copy.set(i, copy.get(i) - item.get(i));
            }
            if (i == copy.size()) {
                ans = Math.min(ans, item.get(i) + backTrace(price, special, copy, j, map));
            }
        }
        map.put(needs, ans);
        return ans;
    }
}
