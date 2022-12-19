package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *xxxx
 * Created on 2022-02-06
 */
public class WeeklyContest279 {

    //奇数索引降序,偶索引升级序
    public int[] sortEvenOdd(int[] nums) {
        int n = nums.length;
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                even.add(nums[i]);
            } else {
                odd.add(nums[i]);
            }
        }
        odd.sort((o1, o2) -> {
            return o2 - o1;
        });
        even.sort((o1, o2) -> {
            return o1 - o2;
        });

        int[] res = new int[n];
        int o = 0;
        int e = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                res[i] = even.get(e++);
            } else {
                res[i] = odd.get(o++);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        WeeklyContest279 weeklyContest279 = new WeeklyContest279();
        //int[] nums = {4,1,2,3};
        int[] nums = {2,1};
        long num = -7605;
        System.out.println(weeklyContest279.smallestNumber(num));
    }

    public long smallestNumber(long num) {
        String val = String.valueOf(Math.abs(num));
        int n = val.length();
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = val.charAt(i) - '0';
        }
        Arrays.sort(temp);  //升序排列了
        //最小,那只需要排序,如果正,那么小的数字放前面,大的放最后,负数反过来,大的放最前,小的放后面
        if (num > 0) {
            StringBuilder sb = new StringBuilder();
            int firstIndex = -1;
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != 0) {
                    firstIndex = i;
                    break;
                }
            }
            if (firstIndex == -1) {
                return num;  //num就是0
            }
            sb.append(temp[firstIndex]);
            for (int j = 0; j < temp.length; j++) {
                if (firstIndex != j) {
                    sb.append(temp[j]);
                }
            }
            return Long.parseLong(sb.toString());
        } else {
            StringBuilder sb2 = new StringBuilder();
            for (int i = temp.length - 1; i >=0; i--) {
                sb2.append(temp[i]);
            }
            return -Long.parseLong(sb2.toString());
        }
    }


    class Bitset {

        private int[] temp;
        private int size;

        public Bitset(int size) {
            this.size = size;
            temp = new int[size];
            Arrays.fill(temp, 0);
        }

        public void fix(int idx) {
            temp[idx] = 1;
        }

        public void unfix(int idx) {
            temp[idx] = 0;
        }

        public void flip() {
            for (int i = 0; i < size; i++) {
                if (temp[i] == 1) {
                    temp[i] = 0;
                } else {
                    temp[i] = 1;
                }
            }
        }

        public boolean all() {
            for (int i = 0; i < size; i++) {
                if (temp[i] != 1) {
                    return false;
                }
            }
            return true;
        }

        public boolean one() {
            for (int i = 0; i < size; i++) {
                if (temp[i] == 1) {
                    return true;
                }
            }
            return false;
        }

        public int count() {
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (temp[i] == 1) {
                    count++;
                }
            }
            return count;

        }

        public String toString() {
            return Arrays.toString(temp);
        }
    }



}
