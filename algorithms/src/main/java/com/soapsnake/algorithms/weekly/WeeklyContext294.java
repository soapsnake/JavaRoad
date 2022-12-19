package com.soapsnake.algorithms.weekly;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-05-22
 * JavaRoad
 */
public class WeeklyContext294 {

    public static void main(String[] args) {
        WeeklyContext294 weeklyContext294 = new WeeklyContext294();


        //String s = "vmvvvvvzrvvpvdvvvvyfvdvvvvpkvvbvvkvvfkvvvkvbvvnvvomvzvvvdvvvkvvvvvvvvvlvcvilaqvvhoevvlmvhvkvtgwfvvzy";
        //char l = 'v';


        String s = "gncqrxuceivrxubvrhzlrjzokvoanotduzxastguuxfnhijraog";
        char l = 'e';
        //System.out.println(weeklyContext294.percentageLetter(s, l));

        //int[][] stock = {{1,7},{2,6},{3,5},{4,4},{5,4},{6,3},{7,2},{8,1}};

        int[][] stock = {{1,1},{499999999,2},{999999998,3},{1000000000,5}};
        System.out.println(weeklyContext294.minimumLines(stock));
    }

    public int percentageLetter(String s, char letter) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == letter) {
                count++;
            }
        }


        //return (int) Math. round(count * 100.0/ s.length());
        BigDecimal b = BigDecimal.valueOf(count);
        BigDecimal a = BigDecimal.valueOf(s.length());
        return b.divide(a, 4, BigDecimal.ROUND_HALF_DOWN).multiply(BigDecimal.valueOf(100)).intValue();
    }

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        //读题,capacity是溶剂,rocks是已经放了的数量, 要求把addition放了后是满状态背包的最大数量
        //思路:求remain数组,升序排列,依次往后遍历,从addition里面减,减到0,计算数量节课

        int[] remain = new int[capacity.length];
        for (int i = 0; i < capacity.length; i++) {
            remain[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(remain);
        int count = 0;
        for (int rock : remain) {
            additionalRocks -= rock;
            count++;
            if (additionalRocks <= 0) {
                break;
            }
        }
        return count;
    }


    public int minimumLines(int[][] stockPrices) {
            //感觉是线段合并,新线段的依据是 斜率不一致? x /y 不一样, x/y一样也不一定是同一线段,中间插了斜率不一样的


        BigDecimal prevXie = null;
        int[] prev = null;
        if (stockPrices.length <= 1) {
            return 0;
        }
        if (stockPrices.length == 2) {
            return 1;
        }
        Arrays.sort(stockPrices, Comparator.comparingInt(o -> o[0]));

        System.out.println(Arrays.deepToString(stockPrices));
        prev = stockPrices[1];
        int count = 1;
        prevXie = count(stockPrices[1][1] - stockPrices[0][1], stockPrices[1][0] - stockPrices[0][0]);
        System.out.println("prev = " + prevXie);
        for (int i = 2; i < stockPrices.length; i++) {
            int[] cur = stockPrices[i];
            BigDecimal curXie = count(cur[1] - prev[1], cur[0] - prev[0]);
            System.out.println("i = " + i + " -> " + curXie);
            if (curXie.compareTo(prevXie) != 0) {
                //System.out.println("cur = " + curXie + " prevXi = " + prevXie);
                count++;
                prevXie = curXie;
            }
            prev = cur;
        }
        return count;
    }

    private BigDecimal count(int x, int y) {
        BigDecimal b = BigDecimal.valueOf(x);
        BigDecimal a = BigDecimal.valueOf(y);
        return b.divide(a, 20, RoundingMode.FLOOR);
    }


    public int totalStrength(int[] strength) {
        long res = 0;
        int mod = (int) 1e9+7;
        for (int i = 0; i < strength.length; i++) {
            int min = Integer.MAX_VALUE;
            int sum = 0;
            for (int j = i; j < strength.length; j++) {
                sum += strength[j];
                min = Math.min(strength[j], min);
            }
            res += sum % mod;
        }
        String s = "321321";
        return (int) (res % mod);
    }

    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] diff = new int[limit * 2 + 1];
        for (int i = 0; i < n / 2; i++) {
            int max = Math.max(nums[i], nums[n - i - 1]);
            int min = Math.min(nums[i], nums[n - i - 1]);
            if (min == 1) {
                diff[2] += 1;
            } else{
                diff[2] += 2;
                diff[min + 1] -= 1;
            }
            diff[max + min] -= 1;
            if (max + min + 1 < diff.length) {
                diff[max + min + 1] += 1;
            }
            if (max + limit + 1 < diff.length) {
                diff[max + limit + 1] += 1;
            }
        }
        int now = 0, res = n / 2;
        for (int i = 2; i < diff.length; i++) {
            now += diff[i];
            res = Math.min(res, now);
        }
        return res;
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        return null;
    }

    public int cal(String s, int l, int r) {
        int res = 0;
        while (r < s.length() && l >= 0) {
            if (s.charAt(l) == s.charAt(r)) {
                res = r - l + 1;
                l--;
                r++;
            } else {
                break;
            }
        }
        return res;
    }

    public int bestRotation(int[] nums) {
        //读题:
        int N = nums.length;
        int[] rotations = new int[N];


        for (int i = 0; i < N; i++) {
            int leftK = (i+1) % N;
            int rightK = (i-nums[i]+1+N) % N;
            rotations[leftK]++;
            rotations[rightK]--;
            if (leftK > rightK) rotations[0]++;
        }

        int best = -N;
        int ans = 0, overlaps = 0;
        for (int k = 0; k < N; k++) {
            overlaps += rotations[k];
            if (overlaps > best) {
                best = overlaps;
                ans = k;
            }
        }
        return ans;
    }

}