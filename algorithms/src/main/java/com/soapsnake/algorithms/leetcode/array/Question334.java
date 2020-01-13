package com.soapsnake.algorithms.leetcode.array;

/**

 * Created on 2020-01-08
 */
public class Question334 {

    /**
     * Example 1:
     * Input: [1,2,3,4,5]
     * Output: true
     *
     *
     * Example 2:
     * Input: [5,4,3,2,1]
     * Output: false
     *
     * 注意:不要求三个数字是连续的
     */
    public boolean increasingTriplet(int[] nums) {
        if (null == nums || nums.length < 3 ) {
            return false;
        }
        int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= left) {   //数字比左边界小,那么更新左边界,注意这里一定是小于等于
                left = num;
            } else if (num <= right) {  //数字比左边界大,但是比右边界要小,那么更新右边界,缩小范围
                right = num;
            } else {    //到这里,说明数字既比左边界大,又比右边界还大,说明第三个递增数字已经找到了
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Question334 question334 = new Question334();
        int[] nums = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        System.out.println(question334.increasingTriplet(nums));
    }
}
