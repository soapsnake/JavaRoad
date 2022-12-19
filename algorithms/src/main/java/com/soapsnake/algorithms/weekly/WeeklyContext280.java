package com.soapsnake.algorithms.weekly;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-02-13
 * JavaRoad
 */
public class WeeklyContext280 {
    

    //if 

    int count  = 0;
    public int countOperations(int num1, int num2) {
        helper(num1, num2);
        return count;
    }

    private void helper(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            return;
        }
        if (num1 >= num2) {
            num1 -= num2;
        } else {
            num2 -= num1;
        }
        count++;
        helper(num1, num2);
    }

    public int minimumOperations4(int[] nums) {
    if (nums.length == 1) {
        return 0;
    }
    Map<Integer, Integer> map1 = new HashMap<>();
    Map<Integer, Integer> map2 = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        if (i % 2 == 0) {
            map1.put(nums[i], map1.getOrDefault(nums[i], 0) + 1);
        } else {
            map2.put(nums[i], map2.getOrDefault(nums[i], 0) + 1);
        }

    }
    List<Map.Entry<Integer, Integer>> list1 = new ArrayList<>(map1.entrySet());
    List<Map.Entry<Integer, Integer>> list2 = new ArrayList<>(map2.entrySet());
    list1.sort((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
    list2.sort((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));

    System.out.println("list1 = " + list1 + " evenpai = " + list2);

    if (list1.get(0).getKey().equals(list2.get(0).getKey())) {
        if (list1.size() == 1 && list2.size() == 1) {
            return Math.min(nums.length - list1.get(0).getValue(), nums.length - list2.get(0).getValue());
        } else if (list1.size() == 1) {
            return nums.length - list1.get(0).getValue() - list2.get(1).getValue();
        } else if (list2.size() == 1) {
            return nums.length - list1.get(1).getValue() - list2.get(0).getValue();
        } else {
            int val1 = nums.length - list1.get(0).getValue() - list2.get(1).getValue();
            int val2 = nums.length - list1.get(1).getValue() - list2.get(0).getValue();
            return Math.min(val1, val2);
        }
    } else {
        return nums.length - list1.get(0).getValue() - list2.get(0).getValue();
    }
}

    public int minimumOperations(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> oddmap = new HashMap<>();
        Map<Integer, Integer> evenMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 != 0) {
                oddmap.put(nums[i], oddmap.getOrDefault(nums[i], 0) + 1);
            } else {
                evenMap.put(nums[i], evenMap.getOrDefault(nums[i], 0) + 1);
            }
        }
        System.out.println("evenMap = " + evenMap + " oddmap = " + oddmap);
        List<PairOf> oddpai = new ArrayList<>();
        List<PairOf> evenpai = new ArrayList<>();
        oddmap.forEach((k, v) -> {
            oddpai.add(new PairOf(k, v));
        });
        evenMap.forEach((k, v) -> {
            evenpai.add(new PairOf(k, v));
        });
        oddpai.sort((o1, o2) -> Integer.compare(o2.count, o1.count));
        evenpai.sort((o1, o2) -> Integer.compare(o2.count, o1.count));

        System.out.println("oddpai = " + oddpai + " evenpai = " + evenpai);

        if(oddpai.get(0).num == evenpai.get(0).num){
            if (oddpai.size() == 1 && evenpai.size() == 1) {
                return Math.min(n - oddpai.get(0).count, n - evenpai.get(0).count);
            } else if (oddpai.size() == 1) {
                return n - oddpai.get(0).count - evenpai.get(1).count;
            } else if (evenpai.size() == 1) {
                return n - evenpai.get(0).count - oddpai.get(1).count;
            } else {
                int count1 = n - oddpai.get(0).count - evenpai.get(1).count;
                int count2 = n - oddpai.get(1).count  - evenpai.get(0).count;
                return Math.min(count1, count2);
            }
        } else {
            return n - oddpai.get(0).count - evenpai.get(0).count;
        }
    }

    class  PairOf{
        public int num;
        public int count;
        PairOf(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Pai{" +
                    "num=" + num +
                    ", count=" + count +
                    '}';
        }
    }

        public int minimumOperations3(int[] nums) {
        int n = nums.length;
        int size = 1 << 14;
        int[] even = new int[size];
        int[] odd = new int[size];
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                even[nums[i]]++;
            } else {
                odd[nums[i]]++;
            }
        }

        //找出even和odd中value最大的两个数字, 如果不相等,就是我们想要的
        Arrays.sort(even);
        Arrays.sort(odd);
        int evenPointer = even.length -1 ;
        int oddPointer = odd.length - 1;

        while (evenPointer >= 0 && oddPointer >= 0) {
            while (even[evenPointer] == 0) {
                evenPointer--;
            }
            while (odd[oddPointer] == 0) {
                oddPointer--;
            }
            if (even == odd) {
                //数字一样,谁count小换谁
                if (even[evenPointer] >= odd[oddPointer]) {
                    oddPointer--;
                } else {
                    evenPointer--;
                }
            } else {
                break;
            }
        }

        
        System.out.println("even" + evenPointer + " odd" + oddPointer);
        return n - (even[evenPointer] + odd[oddPointer]);
    }


    @Test
    public void testSomeFuction() {
    
    }

    public long minimumRemoval(int[] beans) {
        int n = beans.length;
        Arrays.sort(beans);
        long total = Long.MAX_VALUE;
        long[] presum = new long[n];
        presum[0] = beans[0];
        long[] postsum = new long[n];
        for (int i = 1; i < n ; i++) {
            presum[i] = presum[i - 1] + beans[i];
        }
        postsum[n - 1] = beans[n - 1];
        for ( int i = n - 2; n >= 0; i--) {
            postsum[i] = postsum[i + 1] + beans[i];
        }

        for (int i = 0; i < postsum.length; i++) {
            long temp1 = presum[i] - beans[i];
            long temp2 = postsum[i] - (long)( n - i) * beans[i];
            total = Math.min(total, temp1 + temp2);
        }
        return total;
    
   }

    public long minimumRemoval1(int[] beans) {
        long ans = Long.MAX_VALUE;
        Arrays.sort(beans);
        int n = beans.length;
        long[] pre = new long[n + 1];
        pre[0] = 0;
        for(int i = 0; i < n; i++){

            pre[i + 1] = pre[i] + (long) beans[i];
        }
        for(int i = 0; i < n; i++){
            long temp = 0;
            temp += pre[i];
            temp += pre[n] - pre[i + 1];
            temp -= (long)(n - i - 1) * beans[i];
            ans = Math.min(ans, temp);
        }
        return ans;
    }

    public long minimumRemoval2(int[] beans) {
        if (beans.length == 1) {
            return 0;
        }
        Arrays.sort(beans);
        long ans = Long.MAX_VALUE;
        long sum = 0;
        for (int bean : beans) {
            sum += bean;
        }
        for (int i = 0; i < beans.length; i++) {
            long out = sum - (long) (beans.length - i) * beans[i];
            ans = Math.min(ans, out);
        }
        return ans;
    }

    
    public static void main(String[] args) {
        WeeklyContext280 weeklyContext280 = new WeeklyContext280();
        int[] nums = {2,1,2,2,2,2};
        System.out.println(weeklyContext280.minimumOperations(nums));
        System.out.println(weeklyContext280.minimumOperations4(nums));

        weeklyContext280.printshits();


        int[][] arr = new int[][]{{1 ,2},{3, 4}};
        for (int[] tmp : arr) {
            System.out.println(Arrays.toString(tmp));
        }

        String sb = "";
        sb.substring(1, 2);
    }


    public void printshits () {
       
        Map<String, String> map = new HashMap<>();
        map.put("ni hao","ya" );
        map.put("wo bing bu", "hao");
        map.put("wei shen", "ma");


        map.forEach((k, v) -> {
            System.out.println(k);
        });

        System.out.println("结束了1");

        map.forEach((k, v) -> {
            System.out.println(k);
        });

        System.out.println("结束了2");

        map.forEach((k, v) -> {
            System.out.println(k);
        });
        System.out.println("结束了3");

    }

}
