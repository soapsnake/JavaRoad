package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soapsnake.algorithms.orion.Test;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-12 11:02
 */
public class Question33 {

    public static void main(String[] args) {
        Question33 question33 = new Question33();
        int[] nums = {1, 3};
        int target = 2;
//        System.out.println(question33.search(nums, target));  //should be 4
        test();
    }


    public static void test(){
        Map<String, Object> map = new HashMap<>();
        map.put("jiushi", "就是");
        map.put("haishi", null);
        String obj = (String) map.getOrDefault("haishi", null);
        System.out.println(obj);
    }

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
            if (i + 1 != nums.length && nums[i] > nums[i + 1]) {
                breakIndex = i;
                break;
            }
        }
        if (breakIndex == -1) {
            return this.mindleSearch(nums, 0, nums.length - 1, target);
        }
        int resIndex = -1;
        resIndex = mindleSearch(nums, 0, breakIndex, target);
        if (resIndex == -1) {
            resIndex = mindleSearch(nums, breakIndex + 1, nums.length - 1, target);
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
        } else if (nums[middle] > target) {
            return mindleSearch(nums, left, middle - 1, target);
        } else {
            return mindleSearch(nums, middle + 1, right, target);
        }
    }

    //旋转数组的二分查找,所谓旋转数组,就是原来一个有序的数组现在被强制截断然后重新组合
    public int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (right >= left) {
            int midIndex = left + (right - left) / 2;
            if (nums[midIndex] == target) {
                return midIndex;
            }

            //左半分区的完整二分查找
            if (nums[left] <= nums[midIndex]) {
                if (target < nums[midIndex] && target >= nums[left])
                    right = midIndex - 1;
                else
                    left = midIndex + 1;
            }

            //右半分区的完整二分查找
            if (nums[midIndex] <= nums[right]) {
                if (target > nums[midIndex] && target <= nums[right])
                    left = midIndex + 1;
                else
                    right = midIndex - 1;
            }
        }
        return -1;
    }

}
