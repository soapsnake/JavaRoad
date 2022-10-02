package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *xxxx
 * Created on 2022-02-08
 * 
 * 题型一：排列、组合、子集相关问题
提示：这部分练习可以帮助我们熟悉「回溯算法」的一些概念和通用的解题思路。解题的步骤是：先画图，再编码。去思考可以剪枝的条件， 为什么有的时候用 used 数组，有的时候设置搜索起点 begin 变量，理解状态变量设计的想法。

46. 全排列（中等）
47. 全排列 II（中等）：思考为什么造成了重复，如何在搜索之前就判断这一支会产生重复；
39. 组合总和（中等）
40. 组合总和 II（中等）
77. 组合（中等）
78. 子集（中等）
90. 子集 II（中等）：剪枝技巧同 47 题、39 题、40 题；
60. 第 k 个排列（中等）：利用了剪枝的思想，减去了大量枝叶，直接来到需要的叶子结点；
93. 复原 IP 地址（中等）
题型二：Flood Fill
提示：Flood 是「洪水」的意思，Flood Fill 直译是「泛洪填充」的意思，体现了洪水能够从一点开始，迅速填满当前位置附近的地势低的区域。类似的应用还有：PS 软件中的「点一下把这一片区域的颜色都替换掉」，扫雷游戏「点一下打开一大片没有雷的区域」。

下面这几个问题，思想不难，但是初学的时候代码很不容易写对，并且也很难调试。我们的建议是多写几遍，忘记了就再写一次，参考规范的编写实现（设置 visited 数组，设置方向数组，抽取私有方法），把代码写对。

733. 图像渲染（Flood Fill，中等）
200. 岛屿数量（中等）
130. 被围绕的区域（中等）
79. 单词搜索（中等）
说明：以上问题都不建议修改输入数据，设置 visited 数组是标准的做法。可能会遇到参数很多，是不是都可以写成成员变量的问题，面试中拿不准的记得问一下面试官

题型三：字符串中的回溯问题
提示：字符串的问题的特殊之处在于，字符串的拼接生成新对象，因此在这一类问题上没有显示「回溯」的过程，但是如果使用 StringBuilder 拼接字符串就另当别论。
在这里把它们单独作为一个题型，是希望朋友们能够注意到这个非常细节的地方。

17. 电话号码的字母组合（中等），题解；
784. 字母大小写全排列（中等）；
22. 括号生成（中等） ：这道题广度优先遍历也很好写，可以通过这个问题理解一下为什么回溯算法都是深度优先遍历，并且都用递归来写。
题型四：游戏问题
回溯算法是早期简单的人工智能，有些教程把回溯叫做暴力搜索，但回溯没有那么暴力，回溯是有方向地搜索。「力扣」上有一些简单的游戏类问题，解决它们有一定的难度，大家可以尝试一下。

51. N 皇后（困难）：其实就是全排列问题，注意设计清楚状态变量，在遍历的时候需要记住一些信息，空间换时间；
37. 解数独（困难）：思路同「N 皇后问题」；
488. 祖玛游戏（困难）
529. 扫雷游戏（困难）
（欢迎大家补充。）

作者：liweiwei1419
链接：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class AboutBacktrack {

    //1774
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        result = baseCosts[0];
        for (int base : baseCosts) {
            this.dfs(base, toppingCosts, 0, target);
        }
        return result;
    }
    private int result;

    private void dfs(int base, int[] toppingCosts, int curIndex, int target) {
        if (Math.abs(target - base) < Math.abs(target - result)
                || Math.abs(target - result) == Math.abs(target - base) && base < target) {

            System.out.println("1=" + (Math.abs(target - base) < Math.abs(target - result)) +
                                "2=" + (Math.abs(target - result) == Math.abs(target - base)) +
                                "3=" + (base < target));
            result = base;
        }
        if (curIndex >= toppingCosts.length || base >= target) {
            return;
        }
        dfs(base, toppingCosts, curIndex + 1, target);
        dfs(base + toppingCosts[curIndex], toppingCosts, curIndex + 1, target);
        dfs(base + toppingCosts[curIndex] * 2, toppingCosts, curIndex + 1, target);
    }
    //2151
    public int maximumGood(int[][] statements) {
        int n = statements.length;
        boolean[] good = new boolean[n];
        for (int i = 0; i <= n; i++) {
            //先假定所有人都是好人
            Arrays.fill(good, true);
            if (backtrack(statements, good, 0, 0, i)) {

                //从这个返回值可以看出来i = 坏蛋的个数
                return n - i;
            }
        }

        return 0;
    }

    /**
     * @param sts
     * @param good good[i] 为 true 表示 i 为好人
     * @param idx  遍历开始位置
     * @param c    已有坏人的个数
     * @param k    坏人的个数
     * @return
     */
    public boolean backtrack(int[][] sts, boolean[] good, int idx, int c, int k) {
        //这里关键是要搞懂c 和k的含义是啥
        if (c == k) {
            return check(sts, good);
        }

        int n = good.length;
        for (int i = idx; i < n; i++) {
            if (good[i]) {
                good[i] = false;

                if (backtrack(sts, good, i + 1, c + 1, k)) {
                    return true;
                }

                good[i] = true;
            }
        }

        return false;
    }

    /**
     * 检测人员陈述是否合理
     *
     * @param sts
     * @param good
     * @return true 表示合理， false 表示存在冲突
     */
    public boolean check(int[][] sts, boolean[] good) {
        int n = sts.length;
        for (int i = 0; i < n; i++) {
            // 坏人的话直接忽略掉
            if (good[i] == false) continue;

            for (int j = 0; j < n; j++) {
                // 未陈述也忽略掉
                if (sts[i][j] == 2) continue;

                // 陈述存在冲突
                boolean g = (sts[i][j] == 1) ? true : false;
                if (g != good[j]) return false;
            }
        }
        return true;
    }

    public List<List<Integer>> permutation(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        List<List<Integer>> res  =  new ArrayList<> ();
        backTrack(list, new ArrayList<>(), res, new boolean[list.size()]) ;
        return res;

    }


    private void backTrack(List<Integer> list, List<Integer> temp, List<List<Integer>> res, boolean[] used) {
        if(temp.size() == list.size()) {
            res.add(new ArrayList<>(temp));
            System.out.println("到达叶子节点: => temp = " + temp);
            return;
        }
        for (int j = 0; j < list.size(); j++) {
            if (used[j]) {
                continue;
            }
            used[j] = true;
            temp.add(list.get(j));
            System.out.println("递归之前 => temp = " + temp);
            backTrack(list, temp, res, used);
            System.out.println("递归之后 => temp = " + temp);
            used[j] = false;
            temp.remove(temp.size() - 1);
        }

    }
    public static void main(String[] args) {
        AboutBacktrack aboutBacktrack = new AboutBacktrack();
        List<Integer> list = Arrays.asList(1,2,3);
        System.out.println(aboutBacktrack.permutation(list));
    }

}


