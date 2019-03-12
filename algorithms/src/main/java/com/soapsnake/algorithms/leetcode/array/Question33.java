package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-12 11:02
 */
public class Question33 {

    /**
     * 1. 子串分成两段,在这两个段内都是升序的,但是总体不是
     * 2. 找到target的索引,要求log(n),那么不能再对nums进行排序
     * 3. 二分查找的不熟练导致这题花了很长时间
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        //定位中断点,最好的办法是两段分别传进去二分查找,因为两段都是升序的.
        int breakIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if ( i + 1 != nums.length && nums[i] > nums[i + 1]) {
                breakIndex = i;
                break;
            }
        }
        if (breakIndex == -1) {
            return this.mindleSearch(nums, 0 , nums.length - 1, target);
        }
        int resIndex = -1;
        resIndex = mindleSearch(nums, 0, breakIndex, target);
        if (resIndex == -1) {
            resIndex = mindleSearch(nums, breakIndex + 1, nums.length -1, target);
        }
        return resIndex;
    }

    private int mindleSearch(int[] nums, int left, int right, int target) {
        if (right < left) {   //原来二分和左右指针碰撞算法有类似的地方,只要右指针比左指针小就该结束了
            return -1;
        }
        int middle = left + (right - left) / 2;
        if (nums[middle] == target) {
            return middle;
        }else if (nums[middle] > target) {
            return mindleSearch(nums, left, middle - 1, target);
        } else {
            return mindleSearch(nums, middle + 1, right, target);
        }
    }

    public static void main(String[] args) {
        Question33 question33 = new Question33();
        int[] nums = {1,3};
        int target = 2;
        System.out.println(question33.search(nums, target));  //should be 4
    }
}
