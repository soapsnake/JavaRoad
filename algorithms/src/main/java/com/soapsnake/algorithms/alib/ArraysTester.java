package com.soapsnake.algorithms.alib;

import org.junit.Test;

import java.awt.font.NumericShaper;
import java.util.*;

public class ArraysTester {

    //[1,1,2,2,3,4,4,5,5,5] 找出不重复的元素（黄包车）

    public static int findNotDup2(int[] arr) {
        if (arr == null) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        Arrays.sort(arr);
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] != arr[i - 1] && arr[i] != arr[i + 1]) {
                return arr[i];
            }
        }
        return -1;
    }

    public static int findNotDup(int[] arr) {
        if (arr == null) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.get(arr[i]) == null ? 0 : map.get(arr[i]));
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public boolean matrixBinerSearch(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;

        int begin = 0;
        int end = rows * cols - 1;
        while (end >= begin) {
            int mid = begin + (end - begin) / 2;
            int midTar = matrix[mid / cols][mid % cols];
            if (midTar == target) {
                return true;
            } else if (midTar > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return false;
    }

    @Test
    public void testMatrixBinerSearch() {
        int[][] matxi = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        int tar = 100;
        System.out.println(matrixBinerSearch(matxi, tar));
    }

    public void fastSort(int[] ints) {
        if (ints.length == 0) {
            return;
        }
        int left = 0;
        int right = ints.length - 1;
        fastSort(ints, left, right);
    }

    private void fastSort(int[] ints, int left, int right) {
        if (ints == null || ints.length <= 1 || right <= left) {
            return;
        }
        int i = left;
        int j = right;
        int mid = left + (right - left) / 2;
        while (j >= i) {
            while (ints[i] < ints[mid]) {
                ++i;
            }
            while (ints[j] > ints[mid]) {
                --j;
            }
            if (j > i) {
                int temp = ints[i];
                ints[i] = ints[j];
                ints[j] = temp;
                ++i;
                --j;
            } else if (j == i) {
                ++i;
            }
        }
        fastSort(ints, left, j);
        fastSort(ints, i, right);
    }

    @Test
    public void testfastSort() {
        int[] dest = {1, 23, 4, 78, 24, 23, 12, 8, 19, 7, 5};
        fastSort(dest);
        System.out.println(Arrays.toString(dest));


        List<String> list = new ArrayList<>();
        list.add("小明");
        list.add("小刚");
        list.add("小红");
        List<String> list1 = Arrays.asList("小明", "小红");
        list.removeAll(list1);
        System.out.println(list);
    }

    //求子数组的最大和
    public static int maxSubArr(int[] arr) {
        //思路:只有在一种情况下是需要重新制定开始指针的:和小于0的时候,其他情况都应该继续累加

        if (arr == null || arr.length == 0) {
            return 0;
        }
        int total = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int cur = total + arr[i];
            if (cur < 0) {
                total = 0;
            } else {
                total += arr[i];
                if (total > max) {
                    max = total;
                }
            }
        }
        return max;
    }

    @Test
    public void testMaxSub() {
        int[] arr = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(maxSubArr(arr));
    }

    //递归版二分
    public static int binerSearchIter(int[] arr, int tar) {
        if (arr.length == 0) {
            return -1;
        }
        //		return binerSearchIter(arr, 0, arr.length - 1, tar);
        return binerSearchWhile(arr, tar);
    }

    private static int binerSearchWhile(int[] arr, int tar) {
        int left = 0;
        int right = arr.length - 1;
        while (right >= left) {
            int midIndex = left + (right - left) / 2;
            if (arr[midIndex] == tar) {
                return midIndex;
            } else if (arr[midIndex] > tar) {
                right = midIndex - 1;
            } else {
                left = midIndex + 1;
            }
        }
        return -1;
    }

    //迭代版二分
    private static int binerSearchIter(int[] arr, int left, int right, int tar) {
        if (left > right) {
            return -1;
        }
        int midIndex = left + (right - left) / 2;
        if (arr[midIndex] == tar) {
            return midIndex;
        } else if (arr[midIndex] > tar) {
            return binerSearchIter(arr, left, midIndex - 1, tar);
        } else {
            return binerSearchIter(arr, midIndex + 1, right, tar);
        }
    }

    /**
     * 题目：在一个字符串中找到第一个只出现一次的字符。如输入abaccdeff，则输出b。
     * 分析：这道题是2006年google的一道笔试题。
     */
    public Character findFirstAcure(String source) {
        if (null == source || "".equals(source)) {
            return 0;
        }
        if (source.length() == 1) {
            return 1;
        }
        Map<Character, Integer> map = new TreeMap<>(); // char -> count
        for (int i = 0; i < source.length(); i++) {
            map.put(source.charAt(i), map.getOrDefault(source.charAt(i), 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Test
    public void testFindFirstAccure() {
        String soure = "abaccdeff";
        System.out.println(findFirstAcure(soure));
    }

    //数组里面的0都移动到后面去,2n复杂度
    public static void moveZeroes(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        int nonZeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex] = nums[i];
                nonZeroIndex++; //如果nums[i]为0,那么nonZeroIndex和i就会脱钩,
            }
        }
        for (int i = nonZeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    @Test
    public void testMoveZero() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * find target value, if not, show the index of what if we insert it into this array
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    @Test
    public void testSearchInsert() {
        int[] nums = {};
        int tar = 0;
        System.out.println(searchInsert(nums, tar));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        if (nums[0] == 0 && nums[1] != 0) {
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) { //防止第一个数字重复
                continue;
            }
            Set<Integer> cache = new HashSet<>();
            int remain = nums[i];
            if (remain > 0) {
                break;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int leftvalue = nums[left];
                int rightvalue = nums[right];
                if (leftvalue + rightvalue == -remain) {
                    if (!cache.contains(leftvalue)) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(remain);
                        temp.add(leftvalue);
                        temp.add(rightvalue);
                        res.add(temp);
                        cache.add(leftvalue);
                    }
                    right--;
                    left++;
                } else if (leftvalue + rightvalue > -remain) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    @Test
    public void testThreeSum() {
        int[] nums = {0, 0, 0};
        System.out.println(threeSum(nums));
    }

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        this.backTrace(nums, res, 0, new ArrayList<>());
        return res;
    }

    private void backTrace(int[] nums, List<List<Integer>> res, int i, List<Integer> temp) {
        List<Integer> dest = new ArrayList<>(temp);
        if (!res.contains(dest)) {
            res.add(dest);
        }
        for (int j = i; j < nums.length; j++) {
            if (temp.contains(nums[j])) {
                continue;
            }
            temp.add(nums[j]);
            backTrace(nums, res, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void testSubSets() {
        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
    }

    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        List[] lists = new List[1024];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Integer key : map.keySet()) {
            if (lists[map.get(key)] == null) {
                lists[map.get(key)] = new ArrayList();
            }
            lists[map.get(key)].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = lists.length - 1; i > 0 && res.size() < k; i--) {
            if (lists[i] != null) {
                res.addAll(lists[i]);
            }
        }
        int[] res1 = new int[k];
        for (int i = 0; i < res.size(); i++) {
            res1[i] = res.get(i);
        }
        return res1;
    }

    @Test
    public void testtopKSets() {

    }

    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                set.remove(nums[i]);
            }
        }
        int[] res = new int[set.size()];
        int i = 0;
        List<Integer> resfds = new ArrayList<>(set);
        for (int j = 0; j < res.length; j++) {
            res[j] = resfds.get(j);
        }
        return res;
    }

    //旋转数组最小值
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return 0;
    }

    public List<Integer> findDuplicates(int[] nums) {
        //数字在 1 <= n 之间,那么就很简单了
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length <= 1) {
            return list;
        }
        int i = 0;
        int pointer = nums[0];
        for (int j = 0; j < nums.length; j++) {
            int p = nums[j];
            if (p == -1) {
                list.add(p);
                continue;
            }
            while (p != -1) {
                p = nums[p];
                nums[p] = -1;
            }
        }
        return list;
    }

    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        for (int i = 0; candies > 0; ++i) {
            res[i % num_people] += Math.min(candies, i + 1);
            candies -= i + 1;
        }
        return res;
    }

    @Test
    public void testDistributeCandies() {
        System.out.println(Arrays.toString(distributeCandies(7, 4)));
    }

    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        if (A.length == 1) {
            return A;
        }
        int left = 0;
        int right = A.length - 1;
        while (right >= left) {
            //偶前奇后
            while (right >= left && A[left] % 2 == 0) {
                left++;
            }
            while (right >= left && A[right] % 2 != 0) {
                right--;
            }
            if (right >= left) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                right--;
                left++;
            }
        }
        return A;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        int product = 1;
        for (int left = 0, right = 0; right >= left && right < nums.length; right++) {
            product *= nums[right];
            while (product > k) {
                left++;
                product /= nums[left];  //这里不会出现除0异常吗?
            }
            res += right - left + 1;  // [1,2] -> [1],[2];
        }
        return res;
    }


    public void rotateArr(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        k %= nums.length;
        this.revertArr(nums, 0, nums.length - 1);
        this.revertArr(nums, 0, k - 1);
        this.revertArr(nums, k, nums.length - 1);
    }

    private void revertArr(int[] nums, int left, int right) {
        while (left < right) {
            int tem = nums[left];
            nums[left] = nums[right];
            nums[right] = tem;
            left++;
            right--;
        }
    }

    public int solution(int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("width can only be positive!");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("height can only be positive!");
        }
        return width * height;
    }

    String[] queue;


    public String[] solution(int capacity, String[] commands) {
        return new String[0];
    }

    /**
     * * s = "3[a]2[bc]", return "aaabcbc".
     * * s = "3[a2[c]]", return "accaccacc".
     * * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        if (s == null) {
            return s;
        }
        Stack<Integer> countStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        int index = 0;
        String temp = "";
        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                int count = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    count += count * 10 + (s.charAt(index) - '0');
                    index++;
                }
                System.out.println("will push number to stack = " + count);
                countStack.push(count);
            } else if (s.charAt(index) == '[') {
                temp = "";
                index++;
            } else if (s.charAt(index) == ']') {
                //弹栈指令触发
                String last = strStack.pop();
                int repeatCount = countStack.pop();
                String willRepeat = last + temp;
                System.out.println("str = " + willRepeat + " will repeat " + repeatCount + " times");
                for (int i = 1; i <= repeatCount - 1; i++) {
                    willRepeat += willRepeat;
                }
                temp = willRepeat;
                System.out.println("after repeat = " + willRepeat + " now temp = " + temp);

                index++;
            } else {
                StringBuilder sb = new StringBuilder();
                while (index < s.length() && Character.isLetter(s.charAt(index))) {
                    sb.append(s.charAt(index));
                    index++;
                }
                System.out.println("will push str to stack = " + sb.toString());
                strStack.push(sb.toString());
            }
        }
        return temp;
    }

    @Test
    public void testStringDecoder() {
        System.out.println("final res = " + decodeString("3[a2[c]]"));


        Stack<String> stack = new Stack<>();
        String res = "123";
        stack.push(res);
        res = "";
        System.out.println(stack);


        Stack<Person> stack1 = new Stack<>();
        Person person = new Person("fdsf", 1, 1);
        stack1.push(person);
        person = null;
        System.out.println(stack1);


        List<Person> list = new ArrayList<>();
        Person person1 = new Person("哈哈", 2, 3);
        list.add(person1);
//        person1 = null;
        person1.weight = 80;
        System.out.println(list);

    }

    //Input: nums = [2,5,6,0,0,1,2], target = 3
    //Output: false
    public boolean search(int[] nums, int target) {
        //旋转数组的二分查找
        int left = 0;
        int right = nums.length - 1;
        while (right >= left) {
            int midIndex = left + (right - left) / 2;
            if (nums[midIndex] == target) {
                return true;
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
        return false;
    }

    //leetcode227
    public int calculate(String s) {
        if (s == null) {
            return 0;
        }
        Stack<Integer> numbers = new Stack<>();
        char preOperator = '+';
        int num = 0;
        for (int i = 0; i <= s.length(); i++) {
            if (i < s.length() && Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            } else if (i == s.length() || s.charAt(i) != ' ') {
                if (preOperator == '+') {
                    numbers.push(num);
                } else if (preOperator == '-') {
                    numbers.push(-num);
                } else if (preOperator == '*') {
                    numbers.push(num * numbers.pop());
                } else if (preOperator == '/') {
                    numbers.push(numbers.pop() / num);
                }
                if (i < s.length()) {
                    preOperator = s.charAt(i);
                }
                num = 0;
            }
        }
        int res = 0;
        for (Integer integer : numbers) {
            res += integer;
        }
        return res;
    }

    @Test
    public void testCaucluate() {
        String s = "1+2*3+4-9+5/2 ";
        System.out.println(calculate(s));
    }

    public void quickSort(int[] nums) {
        doQuickSort(nums, 0, nums.length - 1);
    }

    public void doQuickSort(int[] nums, int low, int high) {
        if (nums == null || low >= high || nums.length == 1) {
            return;
        }
        int left = low;
        int right = high;
        int middleValue = nums[(right + left) / 2];

        while (left <= right) {
            while (nums[left] < middleValue) {
                left++;
            }
            while (nums[right] > middleValue) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            } else if (left == right) {
                left++;
            }
        }
        doQuickSort(nums, low, right);
        doQuickSort(nums, left, high);

    }
}
