package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-06-05
 * JavaRoad
 */
public class WeeklyContext296 {




    public int minMaxGame(int[] nums) {
        //读题:只能用模拟了吗?
        int[] prev = nums;
        if (prev.length == 1) return prev[0];
        while (prev.length != 1) {
            int[] newNums = new int[prev.length / 2];
            for (int i = 0; i < prev.length / 2; i ++) {
                newNums[i] = (i % 2 == 0) ? Math.min(prev[2 * i], prev[2 * i + 1]) : Math.max(prev[2 * i], prev[2 * i + 1]);
            }
            prev = newNums;
        }
        return prev[0];
    }


    public int[] arrayChange(int[] nums, int[][] operations) {
        //读题: nums中的数字, 按照oeprations进行替换,把oerpations[i][0]替换成operations[i][1];
        //思路: map<数字,List<对应索引>> -> 换数字 = 换key,索引不变,
        //难点: 最后怎么用map还原了? o(n)吗?试一下
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i ++) {
            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }
        for (int[] op : operations) {
            int from = op[0];
            int to = op[1];
            if (map.get(from) == null) continue;
            List<Integer> list = map.get(from);
            map.remove(from);
            map.put(to, list);
        }
        int[] res = new int[n];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> indexs = entry.getValue();
            int key = entry.getKey();
            for (int index : indexs) {
                res[index] = key;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        WeeklyContext296 weeklyContext296 = new WeeklyContext296();
        TextEditor editor = new TextEditor();
        editor.addText("leetcode");
        System.out.println("cursor = " + editor.cursorIndex );
        System.out.println(editor.deleteText(4));
        System.out.println("cursor = " + editor.cursorIndex );
        System.out.println(editor.list);

        editor.addText("practice");
        System.out.println(editor.list +"=>" + editor.list.size());
        System.out.println(editor.cursorIndex);
        System.out.println("right3 => " + editor.cursorRight(3));

        System.out.println("left8=>" + editor.cursorLeft(8));
        System.out.println("cur = " + editor.cursorIndex);
        System.out.println(editor.deleteText(10));
        System.out.println(editor.cursorLeft(2));

        System.out.println("fdjaiofdjasiofdsa");
        System.out.println(editor.cursorRight(6));
    }
}

class TextEditor {
    String txt = "";
    int cursorIndex;
    int totalLen;
    List<Character> list;
    public TextEditor() {
        cursorIndex = 0;
        totalLen = 0;
        list = new LinkedList<>();
    }

    public void addText(String text) {
        for (char c : text.toCharArray()) {
            list.add(cursorIndex++, c);
        }
    }

    public int deleteText(int k) {
        //删除光标左边的k个字符
        for (int i = k; i > 0; i--) {
            if (cursorIndex == 0) return k - i + 1;
            cursorIndex--;
            list.remove(cursorIndex);
        }
        return k;
    }

    public String cursorLeft(int k) {
        for (int i = k; i >=0; i--) {
            cursorIndex--;
            if (cursorIndex == 0) return "";
        }
        int m = cursorIndex;
        StringBuilder sb = new StringBuilder();
        int n = 0;
        while (m >= 0 && n < 10) {
            sb.insert(0, list.get(m));
            m--;
            n++;
        }
        return sb.toString();
    }

    public String cursorRight(int k) {
        for (int i = 0; i < k; i++) {
            if (cursorIndex >= list.size()) break;
            cursorIndex++;
        }
        int m = cursorIndex - 1;
        int n = 0;
        StringBuilder sb = new StringBuilder();
        while (m > 0 && n < 10) {
            sb.insert(0, list.get(m));
            m--;
            n++;
        }
        return sb.toString();
    }
}

class RangeModule {
    TreeSet<Interval> ranges;
    public RangeModule() {
        ranges = new TreeSet<>();
    }

    public void addRange(int left, int right) {
        //tailset 返回比传入参数大的最接近的元素
        Iterator<Interval> it = ranges.tailSet(new Interval(0, left - 1)).iterator();
        //假如传入
        while (it.hasNext()) {
            Interval cur = it.next();
            if (right < cur.left) break;
            left = Math.min(left, cur.left);
            right = Math.max(right, cur.right);
            it.remove();
        }
        ranges.add(new Interval(left, right));
    }

    public boolean queryRange(int left, int right) {
                            //higher返回大于入参的最小元素
        Interval inter = ranges.higher(new Interval(0, left));
        return inter != null && inter.left <= left && inter.right >= right;  //包含
    }

    public void removeRange(int left, int right) {
        Iterator<Interval> it = ranges.tailSet(new Interval(0, left)).iterator();
        List<Interval> toAdd = new ArrayList<>();
        while (it.hasNext()) {
            Interval cur = it.next();
            if (right < cur.left) break;
            if (cur.left < left) ranges.add(new Interval(cur.left, left));
            if (cur.right > right) ranges.add(new Interval(right, cur.right));
        }
    }
}

class Interval implements Comparable<Interval> {
    int left;
    int right;

    public Interval(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Interval o) {
        if (this.right == o.right) return Integer.compare(this.left, o.left);
        return Integer.compare(this.right, o.right);
    }
}


