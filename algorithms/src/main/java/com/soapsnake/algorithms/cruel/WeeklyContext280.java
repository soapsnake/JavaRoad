package com.soapsnake.algorithms.cruel;

import org.apache.commons.collections4.map.HashedMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-02-13
 * JavaRoad
 */
public class WeeklyContext280 {
    

    //if 
    public static void main(String[] args) {
        WeeklyContext280 weeklyContext280 = new WeeklyContext280();
        int num1 = 2;
        int num2 = 3;
        int[] nums = {1,2,2,2,2};
        System.out.println(1 << 14);
        System.out.println(weeklyContext280.minimumOperations(nums));
    }

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

    class  Pai{
        public int num;
        public int count;
        Pai(int num, int count) {
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
    public int minimumOperations(int[] nums) {
        Map<Integer, Integer> oddmap = new HashMap<>();
        Map<Integer, Integer> evenMap = new HashedMap<>();
        AtomicInteger oddTotal = new AtomicInteger();
        AtomicInteger evenTOtal = new AtomicInteger();
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                oddTotal.getAndIncrement();
                oddmap.put(nums[i], evenMap.getOrDefault(nums[i], 0) + 1);
            } else {
                evenTOtal.getAndIncrement();
                evenMap.put(nums[i], evenMap.getOrDefault(nums[i], 0) + 1);
            }
        }
        List<Pai> oddpai = new ArrayList<>();
        List<Pai> evenpai = new ArrayList<>();
        oddmap.forEach((k, v) -> {
            oddpai.add(new Pai(k, v));
        });
        evenMap.forEach((k, v) -> {
            evenpai.add(new Pai(k, v));
        });
        Collections.sort(oddpai, Comparator.comparingInt(o -> o.count));
        Collections.sort(evenpai, Comparator.comparingInt(o -> o.count));
        System.out.println("oddpai = " + oddpai);
        System.out.println("evenpai = " + evenpai);

        int evenPointer = evenpai.size() - 1;
        int oddPointer = oddpai.size() - 1;

        Pai even = null;
        Pai odd = null;
        while (evenPointer >= 0 && oddPointer >= 0) {
             even = evenpai.get(evenPointer);
             odd = oddpai.get(oddPointer);
            if (even.num == odd.num) {
                //数字一样,谁count小换谁
                if (even.count >= odd.count) {
                    oddPointer--;
                } else {
                    evenPointer--;
                }
            } else {
                break;
            }
        }
        Pai finalOdd = odd;
        oddmap.forEach((k, v) -> {
            if (k != finalOdd.num) {
                oddTotal.addAndGet(-v);
            }
        });
        Pai finalEven = even;
        evenMap.forEach((k, v) -> {
            if (k != finalEven.num) {
                evenTOtal.addAndGet(-v);
            }
        });
        return oddTotal.get() + evenTOtal.get();
    }

        public int minimumOperations2(int[] nums) {
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


    public void main() {
        int a = 10;
        int b = 10;

        if ( a == b ) {
            
        }
    }

}
