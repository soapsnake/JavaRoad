package com.soapsnake.algorithms.leetcode.array;

import com.sun.corba.se.impl.oa.toa.TOA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class ArrayUtils {

    public static void printArr(int[] arr) {
        if (arr == null) {
            System.out.println("数组为空");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
    }

    public static <T> void printList(List<T> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("list 为空");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i == (list.size() - 1)) {
                System.out.println("\n");
            } else {
                System.out.print(" -> ");
            }
        }
    }

    public static void swapByIndex(int[] nums, int index, int i) {
        int temp = nums[index];
        nums[index] = nums[i];
        nums[i] = temp;
    }

    public static boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return true;
        }
        Set<Integer> total = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            total.add(i);
        }
        for (int i = 0; i < n; i++) {
            Set<Integer> row = new HashSet<>(total);
            Set<Integer> col = new HashSet<>(total);
            for (int j = 0; j < n; j++) {
                row.remove(matrix[i][j]);
                col.remove(matrix[j][i]);
            }
            if (row.size() != 0 || col.size() != 0) {
                return false;
            }
        }
        return true;
    }


    public int minSwaps23(int[] nums) {
        int numOf1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                numOf1++;
            }
        }
        int currOne = 0;
        for (int i = 0; i < numOf1; i++) {
            if (nums[i] == 1) {
                currOne++;
            }
        }
        int output = numOf1 - currOne;
        for (int i = 1; i <= nums.length - numOf1; i++) {
            if (nums[i - 1] == 1) {
                currOne--;
            }
            if (nums[i + numOf1 - 1] == 1) {
                currOne++;
            }
            output = Math.min(output, numOf1 - currOne);
        }
        return output;
    }


    public static int earliestFullBloom(int[] plantTime, int[] growTime) {
        int prev = plantTime[0] + growTime[0] + 1;
        int total = prev;
        for (int i = 1; i < plantTime.length; i++) {
            int fullTime = plantTime[i] + growTime[i];
            if (fullTime > prev) {
                total += fullTime;
            }
        }
        return total;
    }

    public int minSwaps2(int[] nums) {
        int n = nums.length, totalOnes = 0;

        //这个数组用来把nums头尾接起来变成两倍长,这样就可以绕过循环数组的限制
        int[] duplicated = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            if (i < n) totalOnes += nums[i];
            duplicated[i] = nums[i % n];  //eg. 如果n=10 那么nums[11] = nums[1]
        }
        int start = 0; //start指的是window的左边界索引
        int count = 0, max = 0;

        //end指的是window的右边界索引
        for (int end = 0; end < 2 * n; end++) {
            //因为数组只含0和1所以这里可以这么搞
            count += duplicated[end];  //count其实就是1的数量
            if (end - start + 1 == totalOnes) {
                //当第一次end索引到达窗口长度时会进入这个逻辑
                max = Math.max(max, count);  //max其实对应的是窗口内1的最大数量
            } else if (end - start + 1 > totalOnes) {
                //窗口长度一旦增大就会进入这个逻辑,会推动window的左索引递增.
                count -= duplicated[start++];  //这步操作相当于剔除最左侧的元素
                max = Math.max(max, count);
            }
        }
        return totalOnes - max;
    }

    public int minSwaps(int[] nums) {
        //循环数组,我们可以用一个double数量的数组来存储原始数组,避免麻烦
        //依照题目含义,我们需要把所有的1全部都集合到一起,相当于把所有的1放到一个window里面,所以这道题可以使用滑动窗口来解决
        //滑动窗口既然要包含所有的1,那么滑动窗口的长度就应该是所有1的数量.
        //每次窗口向前滑动时,窗口内需要交换的1的数量就等于窗口size-窗口内1的数量
        //综上,如果想要最少的交换次数,那么只需要找到所有的窗口中,含有1数量最大的一个窗口即可
        int totalOf1 = 0;
        int res = 0;
        int n = nums.length;
        int[] doubleNums = new int[n * 2];
        for (int i = 0; i < doubleNums.length; i++) {
            if (i < n && nums[i] == 1) {
                totalOf1++;
            }
            doubleNums[i] = nums[i % n];
        }

        LinkedList<Integer> window = new LinkedList<>();
        int curTotalOf1 = 0;
        int max = 0;
        for (int i = 0; i < doubleNums.length; i++) {
            window.addLast(doubleNums[i]);
            curTotalOf1 += doubleNums[i];
            if (window.size() == totalOf1) {
                max = Integer.max(max, curTotalOf1);
                int first = window.removeFirst();
                curTotalOf1 -= first;
            }
        }
        return totalOf1 - max;
    }

    private int calculate(List<Integer> window) {
        int total = 0;
        for (int i : window) {
            total += i;
        }
        return total;
    }

    public  List<String>  findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        supplieSets = new HashSet<>(Arrays.asList(supplies));
        for (int i = 0; i < recipes.length; i++) {
            graph.put(recipes[i], ingredients.get(i));
        }
        for (String recipe : recipes) {
            dfs(recipe);
        }
        return doables;
    }
    private Map<String, List<String>> graph = new HashMap<>();
    private List<String> doables = new ArrayList<>();
    private Set<String> visited = new HashSet<>();
    private Set<String> supplieSets = new HashSet<>();

    private boolean dfs(String recipe) {
        if (doables.contains(recipe) || supplieSets.contains(recipe)) {
            return true;
        }
        if (visited.contains(recipe)) {
            return false;
        }

        visited.add(recipe);
        if (!graph.containsKey(recipe)) {
            return false;
        }
        for (String node : graph.get(recipe)) {
            if (!dfs(node)) {
                return false;
            }
        }
        doables.add(recipe);
        return true;
    }

    //public static void main(String[] args) {
    //    String[] recipes = {"bread","sandwich","burger"};
    //    List<List<String>> ingredients = new ArrayList<>();
    //    List<String> list1 = Arrays.asList("yeast","flour");
    //    ingredients.add(list1);
    //    List<String> list2 = Arrays.asList("bread","meat");
    //    ingredients.add(list2);
    //    List<String> list3 = Arrays.asList("sandwich","meat","bread");
    //    ingredients.add(list3);
    //    String[] supplies = {"yeast","flour","meat"};
    //    ArrayUtils arrayUtils = new ArrayUtils();
    //    System.out.println(arrayUtils.findAllRecipes(recipes, ingredients, supplies));
    //}

    //Example 1:
    //Input: s = "][]["
    //Output: 1
    //Explanation: You can make the string balanced by swapping index 0 with index 3.
    //The resulting string is "[[]]".
    //
    //Example 2:
    //Input: s = "]]][[["
    //Output: 2
    //Explanation: You can do the following to make the string balanced:
    //        - Swap index 0 with index 4. s = "[]][][".
    //        - Swap index 1 with index 5. s = "[[][]]".
    //The resulting string is "[[][]]".
    //
    //Example 3:
    //Input: s = "[]"
    //Output: 0
    //Explanation: The string is already balanced.
    public int minSwaps(String s) {
        //左括号需要和右括号进行交换,
        //开头的[不能换成],同理结尾的]不能换成[
        int left = 0, right = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                left++;
            } else {
                right++;
            }
            if (right > left) {
                res++;
                left++;
                right--;
            }
        }
        return res;
    }

    public static int minAddToMakeValid(String s) {
        int ans = 0, balance = 0;
        for (int i = 0; i < s.length(); ++i) {
            balance += s.charAt(i) == '(' ? 1 : -1;
            if (balance == -1) {
                ans++;
                balance++;
            }
        }
        //balance 如果过程中为负,需要立刻插入'(',如果最终为正,则只需要在结尾补足')'
        return ans + balance;
    }

    public static void main(String[] args) {
        String s = "()())()";
        ArrayUtils arrayUtils = new ArrayUtils();
        System.out.println(arrayUtils.removeInvalidParentheses(s));
    }

    public List<String> removeInvalidParentheses(String s) {
        //思路:搞一个queue,依次删除s中的括号,看是否valid,负责度n平方.

        LinkedList<String> queue = new LinkedList<>();
        Set<String> dup = new HashSet<>();
        queue.offer(s);
        List<String> res = new ArrayList<>();
        boolean found = false;
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (isValid(current)) {
                res.add(current);
                found = true;
            }
            if (found) {
                continue;
            }
            for (int i = 0; i < current.length(); i++) {
                if (current.charAt(i) == '(' || current.charAt(i) == ')') {
                    String repstr = current.substring(0, i) + current.substring(i + 1);
                    if (!dup.contains(repstr)) {
                        dup.add(repstr);
                        queue.offer(repstr);
                    }
                }
            }
        }
        return res;
    }

    private boolean isValid(String current) {
        int count  = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) == '(') {
                count++;
            } else if (current.charAt(i) == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }

}
