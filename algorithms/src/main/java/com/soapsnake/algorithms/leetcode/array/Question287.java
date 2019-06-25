package com.soapsnake.algorithms.leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class Question287 {

    public int findDuplicate3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        int fastPtr = fast;
        int slowPtr = nums[0];
        while (fastPtr != slowPtr) {
            fastPtr = nums[fastPtr];
            slowPtr = nums[slowPtr];
        }
        return fastPtr;
    }


    public int findDuplicate2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return nums[i];
            }
        }
        return 0;
    }








    /**
     * 龟兔赛跑算法,用快慢指针探测链表环
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];   //慢指针一次走一步
            hare = nums[nums[hare]];    //快速指针一次走两步(nums[hare]相当于走了一步,然后nums[nums[hare]]相当于走了两步)
        } while (tortoise != hare);    //当快慢指针指向的值相等时说明有可能遇到了循环节点,为什么说有可能???

        // Find the "entrance" to the cycle.
        //这里为什么要采用第二次循环才能判定是循环节点,因为有可能第一次探测到的节点是两个指针指向的是同一个索引,比如[2,5,9,6,9,3,8,9,7,1],第一次循环结束两个指针都指向index8
        int ptr1 = nums[0];      //ptr1指针指向表头
        int ptr2 = tortoise;    //ptr2指针指向第一次循环探测到的可能的循环节点
        while (ptr1 != ptr2) {       //如果第二次相等说明肯定是探测到了环了
            ptr1 = nums[ptr1];     //一次一步
            ptr2 = nums[ptr2];    //一次一步
        }

        //经过两次的循环,可以肯定ptr1肯定是循环节点了
        return ptr1;
    }



}
