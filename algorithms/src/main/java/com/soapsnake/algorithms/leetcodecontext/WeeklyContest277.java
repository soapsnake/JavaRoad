package com.soapsnake.algorithms.leetcodecontext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 
 * Created on 2022-01-23
 */
public class WeeklyContest277 {


    /**
     * 重复数字算多次
     *
     *
     * Input: nums = [11,7,2,15]
     * Output: 2
     *
     * Input: nums = [-3,3,3,90]
     * Output: 2
     */
    public int countElements(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        Integer max = Integer.MIN_VALUE;
        Integer min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > min && nums[i] < max) {
                res.add(nums[i]);
            }
        }
        return res.size();
    }

    public static void main(String[] args) {
        WeeklyContest277 weeklyContest277 = new WeeklyContest277();
        //int[] nums = {62,35,59,55,84,61,38,87,55,82};  //61不改出现

        int[] nums = {1,1};
        System.out.println(weeklyContest277.findLonely(nums));
    }

    /**
     * Input: nums = [3,1,-2,-5,2,-4]
     * Output: [3,-2,1,-5,2,-4]
     *
     * Input: nums = [-1,1]
     * Output: [1,-1]
     *
     * 1. 正数必须在前
     * 2. 原始的相对顺序不能变(正数自己的顺序和负数自己的顺序)
     * 3. 必须一正一负
     */
    public int[] rearrangeArray(int[] nums) {
        //三个指针,i指针指向nums, pos指针指向新数组正数, neg指针指向新数组负数
        int n = nums.length;
        if (n % 2 != 0) {
            return new int[0];
        }
        int[] res = new int[n];
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                pos.add(nums[i]);
            } else {
                neg.add(nums[i]);
            }
        }
        int posP = 0;
        int negP = 0;
        for (int i = 0; i < res.length; i++) {
            if (i % 2 == 0) {
                res[i] = pos.get(posP);
                posP++;
            } else {
                res[i] = neg.get(negP);
                negP++;
            }
        }
        return res;
    }

    /**
     * 出现多次的不算
     * -1 或者 +1的数字出现的不算
     */
    public List<Integer> findLonely(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length < 2) {
            res.add(nums[0]);
            return res;
        }
        Arrays.sort(nums);
        //System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                if (nums[i + 1] != nums[i] && nums[i + 1] != nums[i] + 1) {
                    res.add(nums[i]);
                }
            } else if (i == nums.length - 1){
                if (nums[i] != nums[i - 1] && nums[i] != nums[i - 1] + 1) {
                    res.add(nums[i]);
                }
            } else {
                if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1] && nums[i] != nums[i - 1] + 1
                        && nums[i] != nums[i + 1] - 1) {
                    res.add(nums[i]);
                }
            }
        }
        return res;
    }

    /**
     * 每个人都可以真,可以假,如果真的,那么dfs遍历
     */
    boolean[][] truth;
    public int maximumGood(int[][] statements) {
        int n = statements.length;
        int m = statements[0].length;
        int count = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int[] person = statements[i];
            int len = person.length;
            //我们认为person的话是真的,往下走
            count++;
            for (int j = 0; j < len ; j++) {
                int status = person[j];
                if (status == 0) {
                    //i认为j是坏蛋,那么j可能说谎也可能是真的
                    //如果j说谎,把j的所有的0改成1,1改成0,如果j是真的,那么j说过某人是好人,那么对方就不能撒谎


                } else {

                }
            }
        }
        return 0;
    }
}
