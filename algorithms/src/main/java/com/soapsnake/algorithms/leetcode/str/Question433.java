package com.soapsnake.algorithms.leetcode.str;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * Created on 2020-02-28
 */
public class Question433 {

    //leetcode433
    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end))
            return 0;
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        char[] charSet = new char[] {'A', 'C', 'G', 'T'};

        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                //队列第一个元素是start
                String curr = queue.poll();
                if (curr.equals(end)) {
                    //当前单词已经是目标单词,可以结束了
                    return level;
                }
                char[] currArray = curr.toCharArray();
                for (int i = 0; i < currArray.length; i++) {
                    //用old保存第i个字符
                    char old = currArray[i];
                    for (char c : charSet) {
                        //把第i个字符依次改成"ACGT"中的一个
                        currArray[i] = c;
                        String next = new String(currArray);
                        //没有使用过这个单词 && 合法单词中包含该单词的话
                        if (!visited.contains(next) && bankSet.contains(next)) {
                            //加入已访问单词
                            visited.add(next);
                            //单词塞回队列
                            queue.offer(next);
                        }
                    }
                    //把第i个单词改回去,下一回是修改第i+1个字符,要保证第i个字符不变
                    currArray[i] = old;
                }
            }
            //如果一个单词的四个字符都被换过了,那么就会进入下一个level
            level++;
        }
        return -1;
    }
}
