package com.soapsnake.algorithms.leetcode.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-30
 */
public class Question609 {

    //本质上,这道题考到了操作系统的文件系统该如何设计,文件搜索该如何实现

    //leetcode609
    /**
     *     我们往磁盘写很大的文件时,如何判定是否已经有相同的文件?
     *     1.我们存文件的时候,事先计算好每个文件的size,维护一个size -> set<文件名>的map写入磁盘meta data
     *     2.写入新文件时,看看map中有没有相同大小的文件(map.get(size)不为null),为null说明无重复
     *     3.如果get到的set不为空,那嘛遍历set中的文件,计算他们的md64 hash码值,与要存入的文件比对
     */
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> result = new ArrayList<>();
        int n = paths.length;
        if (n == 0)
            return result;

        Map<String, Set<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] strs = path.split("\\s+");
            for (int i = 1; i < strs.length; i++) {
                int idx = strs[i].indexOf("(");
                String content = strs[i].substring(idx);
                String filename = strs[0] + "/" + strs[i].substring(0, idx);
                Set<String> filenames = map.getOrDefault(content, new HashSet<>());
                filenames.add(filename);
                map.put(content, filenames);
            }
        }
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                result.add(new ArrayList<>(map.get(key)));
            }
        }
        return result;
    }
}
