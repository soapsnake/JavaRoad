package com.soapsnake.algorithms.leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 */
class Question347 {
    public static void main(String[] args) {
        Question347 question347 = new Question347();
        int[] tar = new int[]{1, 1, 1, 2, 2, 3};
        List<Integer> res = question347.topKFrequent(tar, 2);
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return res;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        Set<Map.Entry<Integer, Integer>> node = map.entrySet();
        List<Map.Entry<Integer, Integer>> list = node.stream().sorted((a, b) -> b.getValue() - a.getValue()).collect(Collectors.toList());
        for (int i = 0; i < k; i++) {
            res.add(list.get(i).getKey());
        }
        return res;
    }

    /**
     * 另外一种解法,这种解法不需要再对map的value值进行排序,因此,复杂度下降到了O()
     *
     * @param nums
     * @return
     */
    public List<Integer> topKFrequent1(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }

}
