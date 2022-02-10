package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-02-10
 * JavaRoad
 */
public class Others {

    //1452
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        //类似倒排索引,  apple -> [0,1], amozon -> [0,1], google -> [1,2]
        //{{apple, amazon}, {apple, amazon, google}, {google}}
        // [0,1] [0,1]  如果序列的每一个全部都有对应的, 说明是
        //[0,1][0,1][1,2]
        int n = favoriteCompanies.size();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean find = true;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                Set<String> temp = new HashSet<>(favoriteCompanies.get(j));
                List<String> coms = favoriteCompanies.get(i);
                if (temp.containsAll(coms)) {
                    find = false;
                    break;
                }
            }
            if (find) {
                res.add(i);
            }
        }
        return res;
    }
}
