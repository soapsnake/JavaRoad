package com.ld.leetcode;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class Question169 {

    /**
     *     讲道理这个解法是非常有技巧性的解法,发明这个算法的人甚至发了篇paper.
     *     要我来我只能用一个map来保存遍历的结果,然后遍历map得最终结果,算法时间复杂度o(n),空间复杂度o(n)
     */
    public int majorityElement(int[] nums) {
        int majority = 0;
        int count = 0;
        for (int num : nums){
            if (count == 0){
                majority = num;
            }
            if (num == majority){
                count++;
            }else{
                count--;
            }
        }
        return majority;
    }

    public static void main(String[] args) {
        Question169 question169 = new Question169();
        int[] nums = new int[]{1,1,1,1,2,3,4,5};
        System.out.println(question169.majorityElement(nums));
    }
}
