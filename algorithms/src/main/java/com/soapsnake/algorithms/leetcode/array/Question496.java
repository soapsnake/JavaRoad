package com.soapsnake.algorithms.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author soapsnake
 * @date 2018/11/8
 */
class Question496 {

    public static void main(String[] args) {
        Question496 question496 = new Question496();

        int[] nums1 = {2, 4};
        int[] nums2 = {1, 2, 3, 4};

        ArrayUtils.printArr(question496.nextGreaterElement(nums1, nums2));
    }

    //真他妈简单,反过来想,nums2中的数字,先按预期放进map中,然后用nums1中对应元素去取就行了
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            for (int j = i + 1; j < nums2.length; j++) {
                if (nums2[j] > nums2[i]) {
                    hashMap.put(nums2[i], nums2[j]);//i元素右侧出现了比它大的
                    break; //只需要第一个比它大的,break防止出现更大的进行了覆盖
                }
            }
            hashMap.putIfAbsent(nums2[i], -1); //i元素右侧没有比它大的,那就放个-1
        }

        for (int i = 0; i < nums1.length; i++) {
            res[i] = hashMap.get(nums1[i]); //直接取,因为nums1是nums2的子数组,所以不用防空
        }
        return res;
    }


}
