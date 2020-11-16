package com.soapsnake.algorithms.alib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.soapsnake.algorithms.structures.list.ListNode;

public class Solution {

    public String sortStr(String text) {
        //字符串排序,要求:
        //1. 不区分大小写,a -> z
        //2. 同一个英文大小写都存在时,按输入顺序排
        //3. 非英语字母位置不变
//        char[] chars = text.toCharArray();
        Character[] chars1 = text.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        Arrays.sort(chars1, (a, b) -> {
            int resI = compute(a);
            int resJ = compute(b);
            if (resI >= 0 && resJ >= 0) {
                return resI - resJ;
            } else if (resI < 0 && resJ >= 0) {
                return 1;
            } else if (resI >= 0 && resJ < 0) {
                return -1;
            } else {
                return 0;
            }
        });

//        for (int i = 0; i < chars.length; i++) {
//            for (int j = i + 1; j < chars.length; j++) {
//                int resI = compute(chars[i]);
//                int resJ = compute(chars[j]);
//                if (resI >= 0 && resJ >= 0) {
//                    if (resI >= resJ) {
//                        swap(chars, i ,j);
//                    }
//                    if (resI == resJ && i <= j) {
//                        swap(chars, i, j);
//                    }
//                }
//            }
//        }
        StringBuilder res = new StringBuilder();
        for (Character c : chars1) {
            res.append(c);
        }
        return res.toString();
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private int compute(char aChar) {
        if (aChar >= 'a' && aChar <= 'z') {
            return aChar - 'a';
        }
        if (aChar >= 'A' && aChar <= 'Z') {
            return aChar - 'A';
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.compute('m'));
        System.out.println(solution.compute('M'));
        System.out.println(solution.sortStr("m MaAAAA"));;
        System.out.println(solution.sortStr("A Famous Saying: Much Ado About Nothing"));;
    }



    private boolean isLower(char aChar) {
        return aChar >= 'a' && aChar <= 'z';
    }

    private boolean isUpper(char aChar) {
        return aChar >= 'A' && aChar <= 'Z';
    }

    private boolean isValid(char aChar) {
        return (aChar <= 'z' && aChar >= 'a')
                || (aChar <= 'Z' && aChar >= 'A');
    }

    //    private List<String> queue;
//    private int maxSize;
    public Solution() {
//        this.queue = new ArrayList<>();
//        this.maxSize = capacity;
    }

    public String[] solution(int capacity, String[] commands) {
        List<String> queue = new ArrayList<>(capacity);
        int curSize = 0;
        int maxSize = capacity;
        String[] res = new String[commands.length];
        for (int i = 0; i < commands.length; i++) {
            String[] item = commands[i].split(" ");
            if (item.length >= 2) {
                String realCommands = item[0];
                if (realCommands.equalsIgnoreCase("OFFER")) {
                    if (curSize + 1 > maxSize) {
                        res[i] = "false";
                    } else {
                        //in case the string contains space character,we should take all of these...
                        queue.add(commands[i].substring(realCommands.length() + 1));
                        curSize++;
                        res[i] = "true";
                    }
                }
            } else if (item.length == 1) {
                String realCommands = item[0];
                if (realCommands.equalsIgnoreCase("TAKE")) {
                    if (curSize > 0) {
                        String dest = queue.remove(0);
                        curSize--;
                        res[i] = dest;
                    } else {
                        //if queue is empty,return nothing
                        res[i] = null;
                    }
                } else if (realCommands.equalsIgnoreCase("SIZE")) {
                    res[i] = String.valueOf(curSize);
                }
            }
        }
        return res;
    }

    public String[] solutionOld(int capacity, String[] commands) {
        String[] queue = new String[100000];
        int left = 0;
        int right = 0;
        int curSize = 0;
        int maxSize = capacity;
        String[] res = new String[commands.length];
        for (int i = 0; i < commands.length; i++) {
            String[] item = commands[i].split(" ");
            if (item.length >= 2) {
                String realCommands = item[0];
                if (realCommands.equalsIgnoreCase("OFFER")) {
                    if (curSize + 1 > maxSize) {
                        res[i] = "false";
                    } else {
                        //in case the string contains space character,we should take all of these...
                        queue[right++] = commands[i].substring(realCommands.length() + 1);
                        curSize++;
                        res[i] = "true";
                    }
                }
            } else if (item.length == 1){
                String realCommands = item[0];
                if (realCommands.equalsIgnoreCase("TAKE")) {
                    if (curSize > 0) {
                        String dest = queue[left++];
                        curSize--;
                        res[i] = dest;
                    } else {
                        //if queue is empty,return nothing
                        res[i] = null;
                    }
                } else if (realCommands.equalsIgnoreCase("SIZE")) {
                    res[i] = String.valueOf(curSize);
                }
            }
        }
        return res;
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        System.out.println(Arrays.toString(solution.solution(3, new String[]{"OFFER wo cao", "OFFER world", "OFFER !", "OFFER nim adf","TAKE", "TAKE", "SIZE", "TAKE", "SIZE", "WHAT"})));
//
//    }

    //
    public int[] solution(int windowSize, int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return numbers;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[numbers.length - windowSize + 1];
        for (int i = 0; i < numbers.length; i++) {
            while (!queue.isEmpty() && numbers[queue.peekLast()] <= numbers[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (queue.peek() <= i - windowSize) {
                queue.poll();
            }
            if (i + 1 >= windowSize) {
                res[i + 1 - windowSize] = numbers[queue.peek()];
            }
        }
        return res;
    }


    /**
     * 寻找数组中重复和缺少的数字
     *
     * @return
     */
    public static int[] findDupAndLose(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] res = new int[2];
        Arrays.sort(arr);
        int dupIndex = -1;
        int dup = 0, lose = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                dup = arr[i];  //发现了重复数
                if (dupIndex != -1) {
                    throw new RuntimeException("多与两个数字重复");
                }
                dupIndex = i;
            } else if (arr[i] != (arr[i - 1] + 1)) {
                lose = arr[i - 1] + 1;
            }
        }
        if (dupIndex != -1) {
            res[0] = dup;
            res[1] = lose;
            return res;
        } else {
            return null;
        }
    }

    public int[] solution(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            throw new IllegalArgumentException("illegal arguments!");
        }
        Arrays.sort(numbers);
        //we can use two pointers imply two sticks
        int left = 0;
        int right = numbers.length - 1;
        while (right > left) {
            if (numbers[left] + numbers[right] == target) {
                return new int[]{numbers[left], numbers[right]};
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        //if we couldn't find those two sticks, return an empty array
        return new int[0];
    }


    public ListNode mergeKSortedList(List<ListNode> listNodes) {
        if (listNodes == null || listNodes.size() == 0) {
            return null;
        }
        return this.helper(listNodes, 0, listNodes.size() - 1);
    }

    private ListNode helper(List<ListNode> listNodes, int l, int r) {
        if (l == r) {
            return listNodes.get(l);
        }
        if (l == r - 1) {
            return this.mergeTwoList(listNodes.get(l), listNodes.get(r));
        }
        int m = l + (l - r) / 2;
        ListNode lList = helper(listNodes, l, m - 1);
        ListNode rList = helper(listNodes, m, r);
        return this.mergeTwoList(lList, rList);
    }

    private ListNode mergeTwoList(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode fakeHead = new ListNode(0);
        ListNode cur = fakeHead;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        cur.next = head1 == null ? head2 : head1;
        return fakeHead.next;
    }

}
