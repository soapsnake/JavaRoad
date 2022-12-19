package com.soapsnake.algorithms.weekly;

import com.sun.swing.internal.plaf.metal.resources.metal;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-08-28
 * JavaRoad
 */
public class WeeklyContext308 {

    public static void main(String[] args) {
        WeeklyContext308 weeklyContext308 = new WeeklyContext308();
        System.out.println();

        int[] nums = {736411,184882,914641,37925,214915};
        int[] q = {331244,273144,118983,118252,305688,718089,665450};
        //System.out.println(Arrays.toString(weeklyContext308.answerQueries(nums, q)));

        String[] laji ={"G","P","GP","GG"};
        int[] t = {2,4,3};
        System.out.println(weeklyContext308.garbageCollection(laji, t));  //Should 21
    }

    public int garbageCollection(String[] garbage, int[] travel) {
        //读题: 1. 垃圾车分三类,每一类只能收拾特定类垃圾,总话费时间 = 路程总时间 + 垃圾个数
        //2. 如果没有某类型垃圾,这个类型不花时间, 如果后面没有该类型垃圾,不需要在进行traval
        int n = garbage.length;
        int metal = 0;
        int paper = 0;
        int glass = 0;
        int mPre = 0;
        int pPre = 0;
        int gPre = 0;
        int[] sum = new int[travel.length + 1];
        sum[0] = travel[0];
        for (int i = 1; i < sum.length ; i++) {
            sum[i] = sum[i - 1] + travel[i - 1];
        }
        for (int i = 0; i < n; i++) {
            String laji = garbage[i];
            int mcnt = 0;
            int pcnt = 0;
            int gcnt = 0;
            for (char c : laji.toCharArray()) {
                if (c == 'G') {
                    gcnt++;
                } else if (c == 'M') {
                    mcnt++;
                } else {
                    pcnt++;
                }
            }
            if (mcnt > 0) {
                metal += mcnt;
                int tmp = sum[i] - sum[mPre];
                metal += tmp;
                mPre = i;
            }
            if (pcnt > 0) {
                paper += pcnt;
                int tmp = sum[i] - sum[pPre];
                paper += tmp;
                pPre = i;
            }
            if (gcnt > 0) {
                glass += gcnt;
                int tmp = sum[i] - sum[gPre];
                glass += tmp;
                gPre = i;
            }
            System.out.println("glass = " + glass);
        }
        System.out.println("metal " + metal);
        System.out.println("paper " + paper);
        System.out.println("glass " + glass);

        return metal + paper + glass;
    }

    public String removeStars(String s) {
        //读题:
        char[] sc = s.toCharArray();
        for (int i =0; i < sc.length; i++) {
            if (sc[i] == '*') {
                sc[i] = '#';
                int j = i - 1;
                while (j >= 0 && sc[j] == '#') {
                    j--;
                }
                sc[j] = '#';
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sc.length; i++) {
            if (sc[i] != '#') {
                sb.append(sc[i]);
            }
        }
        return sb.toString();
    }

    public String removeStars2(String s) {
        //读题:
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public int[] answerQueries(int[] nums, int[] queries) {
        int n = queries.length;
        int[] res = new int[n];
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            res[i] = this.find4(nums, queries[i]);
        }
        return res;
    }

    public int find5(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > k) {
                return i;
            }
            sum  += nums[i];
        }
        return nums.length;
    }

    private int find4(int[] arr, int k) {
        if(arr == null || arr.length == 0)
            return 0;

        int n = arr.length;
        int[] negsum = new int[n];
        int prev = 0;
        for(int i=n-1; i>=0 ; i--) {
            negsum[i] = prev;
            if(prev + arr[i] <= 0) {
                prev += arr[i];
            }
            else {
                prev = 0;
            }
        }

        int sum = 0;
        int max = 0;
        int i = 0;

        for(int j=0; j<n; j++) {
            sum += arr[j];
            while(i<=j && sum + negsum[j] > k) {
                sum -= arr[i++];
            }
            max = Math.max(max, j-i+1);
        }
        return max;
    }

    public int find3(int arr[], int k) {
        long sum = 0;
        int cnt = 0, maxcnt = 0;
        for (int i = 0; i < arr.length; i++) {
            // If adding current element doesn't
            // cross limit add it to current window
            if ((sum + arr[i]) <= k) {
                sum += arr[i];
                cnt++;
            }// Else, remove first element of current
            // window.
            else if(sum!=0)
            {
                sum = sum - arr[i - cnt] + arr[i];
            }
            // keep track of max length.
            maxcnt = Math.max(cnt, maxcnt);
        }
        return maxcnt;
    }

    public int find(int[] arr, int k){
        long[] h = new long[arr.length+1];
        long sum = 0;
        h[0] = sum;
        for(int i=0;i!=arr.length;i++){
            sum += arr[i];
            h[i+1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for(int i=0;i!=arr.length;i++){
            sum+=arr[i];
            pre = getLessIndex(h, sum-k);
            len = pre == -1?0:i-pre+1;
            res = Math.max(res, len);
        }
        return res;
    }




    public int getLessIndex(long[] arr, long num){
        int low = 0;
        int high = arr.length-1;
        int mid = 0;
        int res = -1;
        while(low<=high){
            mid = (low+high)>>1;
            if(arr[mid]>=num){
                res = mid;
                high = mid -1;
            }else{
                low = mid+1;
            }
        }
        return res;
    }

    public int find2(int[] nums, int limit) {
        if(nums.length <= 1)
            return nums.length;

        // increasing deque to track minimums
        Deque<Integer> minimums = new LinkedList<>();

        // decreasing deque to track maximums
        Deque<Integer> maximums = new LinkedList<>();

        int maxLen = 1;
        int left = 0;

        minimums.addLast(nums[0]);
        maximums.addLast(nums[0]);

        for(int right=1; right < nums.length; ++right)
        {
            while(!minimums.isEmpty() && nums[right] < minimums.getLast())
                minimums.removeLast();

            minimums.addLast(nums[right]);

            while(!maximums.isEmpty() && nums[right] > maximums.getLast())
                maximums.removeLast();

            maximums.addLast(nums[right]);

            int diff = maximums.getFirst() - minimums.getFirst();

            if(diff <= limit)
            {
                maxLen = Math.max(maxLen, 1+right-left);
            }
            else
            {
                // advance the left pointer
                if(nums[left] == minimums.getFirst())
                    minimums.removeFirst();

                if(nums[left] == maximums.getFirst())
                    maximums.removeFirst();

                left++;
            }

        }
        return maxLen;
    }
}
