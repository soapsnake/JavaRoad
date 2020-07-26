package com.soapsnake.algorithms.leetcode.graph.theme;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-07-22
 */
public class Question841 {

    //leetcode841
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        return dfs(rooms, 0, new HashSet<>());
    }

    private boolean dfs(List<List<Integer>> rooms, int key, Set<Integer> seen) {
        seen.add(key);
        List<Integer> keys = rooms.get(key);
        for (int k : keys) {
            if (!seen.contains(k))
                dfs(rooms, k, seen);
        }
        return seen.size() == rooms.size();
    }
}
