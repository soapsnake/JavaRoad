package com.soapsnake.algorithms.leetcode.matrix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created on 2020-04-19
 */
public class Question554 {

    //leetcode554
    public int leastBricks(List<List<Integer>> wall) {
        //本质上这道题是一道最小公约数的问题,既需要找到所有list当中,重复次数最小的数字
        if(wall.size() == 0) return 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(List<Integer> list : wall){
            int length = 0;
            for(int i = 0; i < list.size() - 1; i++){
                length += list.get(i);
                map.put(length, map.getOrDefault(length, 0) + 1);
                count = Math.max(count, map.get(length));
            }
        }
        return wall.size() - count;
    }
}
