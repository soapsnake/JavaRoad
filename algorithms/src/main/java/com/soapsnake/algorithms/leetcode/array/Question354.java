package com.soapsnake.algorithms.leetcode.array;

import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * Created on 2022-01-08
 */
public class Question354 {

    //public static void main(String[] args) {
    //    Question354 question354 = new Question354();
    //    String s = "lee(t(c)o)de)";
    //    System.out.println(question354.minRemoveToMakeValid(s));
    //}

    public int maxEnvelopes(int[][] envelopes) {
        //先把envelopes按照w进行排序,然后对h找最长递增子序列即可

        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });

        List<Integer> list = new ArrayList<>();
        list.add(envelopes[0][1]);
        for (int i = 1; i < envelopes.length; i++) {
            int height = envelopes[i][1];
            if (height > list.get(list.size() - 1)) {
                list.add(height);
            } else {
                int index = this.findIndex(list, height);
                list.set(index, height);
            }
        }
        return list.size();
    }

    private int findIndex(List<Integer> list, int height) {
        if (list.size() == 0 || height > list.get(list.size() - 1)) {
            return list.size();
        }
        int left = 0, right = list.size() - 1;
        while (right > left) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > height) {
                right = mid;
            } else {
                left = mid - 1;
            }
        }
        return left;
    }

    public String minRemoveToMakeValid2(String s) {
        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Arrays.asList(s));
        Set<String> dup = new HashSet<>();
        while (!queue.isEmpty()) {
            List<String> row = queue.poll();
            List<String> next = new ArrayList<>();
            for (String cur : row) {
                if (this.isValid(cur)) {
                    return cur;
                }
                for (int i = 0; i < cur.length(); i++) {
                    if (cur.charAt(i) == '(' || cur.charAt(i) == ')') {
                        String str = cur.substring(0, i) + cur.substring(i + 1);
                        if (!dup.contains(str)) {
                            dup.add(str);
                            next.add(str);
                        }
                    }
                }
            }
            queue.offer(next);
        }
        return null;
    }

    private boolean isValid(String cur) {
        int cnt = 0;
        for (int i = 0; i < cur.length(); i++) {
            if (cur.charAt(i) == '(') {
                cnt++;
            } else if (cur.charAt(i) == ')') {
                cnt--;
                if (cnt < 0) {
                    return false;
                }
            }
        }
        return cnt == 0;
    }

    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                stack.add(i);
            } else if (sb.charAt(i) == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    sb.setCharAt(i, '*');
                }
            }
        }
        while (!stack.isEmpty()) {
            sb.setCharAt(stack.pop(), '*');
        }
        return sb.toString().replaceAll("\\*", "");
    }

    public int minInsertions(String s) {
        //思路: cnt, 如果是(那就++, 如果是)那就--,平衡值是-1, 如果小于-1,那就表示要添加),如果最终cnt是 >-1的,那么有多少cnt,就要*2
        int res = 0, right = 0;  //right represents the number of right parentheses needed.
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                //需求的')'是奇数个,需要补一个')',只有这一种情况是只补一个')'的
                if (right % 2 > 0) {
                    right--;
                    res++;  //这个res++指的是补充一个')',这里虽然补了,但是补的是之前欠的
                }
                right += 2;  //补了之后还是少两个
            } else {
                right--;
                if (right < 0) {
                    //'('数量过多,需要补充')'
                    right += 2;
                    res++; //这个res++指的是补'('
                }
            }
        }
        return right + res;
    }

    public int minInsertions2(String s) {
        int ans = 0, neededRight = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                if (neededRight % 2 > 0) {
                    neededRight--;
                    ans++;
                }
                neededRight += 2;
            } else {
                neededRight--;
                if (neededRight < 0) {
                    neededRight += 2;
                    ans++;
                }
            }
        }
        return neededRight + ans;
    }

    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if (n % 2 != 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            if (locked.charAt(i) == '0') {
                sb.setCharAt(i, '*');
            }
        }
        return checkValidString(sb.toString());
    }

    public boolean checkValidString(String s) {
        int l = 0, r = 0, nos = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                l++;
            } else if (s.charAt(i) == ')') {
                l--;
            } else {
                nos++;
            }
            if (l < 0) {
                nos--;
                l++;
            }
            if (nos < 0) {
                return false;
            }
        }
        nos = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                r++;
            } else if (s.charAt(i) == '(') {
                r--;
            } else {
                nos++;
            }
            if (r < 0) {
                nos--;
                r++;
            }
            if (nos < 0) {
                return false;
            }
        }
        return true;
    }


    public long mostPoints(int[][] questions) {
        //这个可能要用DP
        int n = questions.length;  //dp总长度
        long[] dp = new long[n + 1];   //从index开始解的最大分数
        for (int i = n - 1; i >= 0; i--) {
            int points = questions[i][0];
            dp[i] = points;
            int indexRange = questions[i][1];
            if (i + indexRange < n) {
                dp[i] += dp[i + indexRange + 1];
            }
            dp[i] = Math.max(dp[i + 1], dp[i]);
        }
        return dp[0];
    }

    public String[] divideString(String s, int k, char fill) {
        int index = 0;
        List<String> res = new ArrayList<>();
        while (index < s.length()) {
            StringBuilder subStr;
            if (index + k > s.length()) {
                subStr = new StringBuilder(s.substring(index));
            } else {
                subStr = new StringBuilder(s.substring(index, index + k));
            }
            while (subStr.length() < k) {
                subStr.append(fill);
            }
            res.add(subStr.toString());
            index += k;
        }
        return res.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Question354 question354 = new Question354();
        int tartget = 10;
        int maxd = 4;
        System.out.println(question354.minMoves(10 , 4));
    }



    public int minMoves(int target, int maxDoubles) {
        int move = 0;
        while (maxDoubles > 0 && target > 1) {
            if (target % 2 == 0) {
                //如果是偶数那就直接除2
                move += 1;
                target /= 2;
            } else  {
                //如果是奇数那就减去1然后再除2,这种操作需要两步
                target = (target - 1) / 2;
                move += 2;
            }
            maxDoubles -= 1;
        }
        return move + target;
    }





}
