package com.soapsnake.algorithms.alib;

import com.soapsnake.algorithms.structures.tree.Node;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeTester {

    public Node makeTree() {
        int layer = rand();
        Node root = new Node(rand());
        root.children = makeChildrens(1, layer);
        return root;
    }

    private List<Node> makeChildrens(int current, int layer) {
        List<Node> list = new ArrayList<>();
        if (current >= layer) {
            return list;
        }
        int num = rand();
        for (int i = 0; i < num; i++) {
            Node node = new Node(rand());
            node.children = makeChildrens(current + 1, layer);
            list.add(node);
        }
        return list;
    }

    public int rand() {
        return RandomUtils.nextInt(1, 10);
    }

    public static void main(String[] args) {
        TreeTester treeTester = new TreeTester();
        Node.layerPrint(treeTester.makeTree());
    }


    @Test
    public void testsort() {
        int[] nums = {5,2,4,90,432,34,34,34,15,8};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
    public void sort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        this.quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int left, int right) {
        if (nums == null || nums.length < 1 || right <= left) {
            return;
        }
        int i = left;
        int j = right;
        int midIndex = left + (right - left) / 2;
        while (i <= j) {
            while (nums[i] < nums[midIndex]) {
                ++i;
            }
            while (nums[j] > nums[midIndex]) {
                --j;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        quickSort(nums,left,j);
        quickSort(nums,i,right);
    }


}
