package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created on 2020-03-02
 */
public class Question442 {

    /**
     * 找出数组中所有出现两次的数字
     * Example:
     * Input:
     * [4,3,2,7,8,2,3,1]
     * Output:
     * [2,3]
     */
    // when find a number i, flip the number at position i-1 to negative.
    //当遍历到第i个数字时,转换第i-1个数字为其负值
    //如果第i-1个数字已经是负数了,那么第i个数字一定出现了两次
    //举个例子[0,8,8,x,x,x,x,x,x,x,x,x],当i=1时,我们翻转第8个元素为负值,i=2时,我们准备再次翻转第8个元素时
    //发现第8个元素已经被翻过牌子了,说明一定有一个元素和当前元素值相等,但是到底是第几个,我们不知道
    //所以前提是:1.数组初始时所有元素一定是正的, 2.数组的所有元素的值都不能超过最大索引
    //leetcode442
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null)
            return result;
        for (int i = 0; i < nums.length; i++) {
            int location = Math.abs(nums[i]) - 1;
            if (nums[location] < 0) {
                result.add(Math.abs(nums[i]));
            } else {
                nums[location] = -nums[location];
            }
        }
        return result;
    }
}
