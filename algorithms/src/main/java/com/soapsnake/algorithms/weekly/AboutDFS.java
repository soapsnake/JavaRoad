package com.soapsnake.algorithms.weekly;

import java.util.Arrays;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-02-09
 * JavaRoad
 */
public class AboutDFS {


    public int minFlips(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] cnt = new int[m][n];
        int bit = 0, limit = (1 << n) - 1;
        int ans = 10;
        while (bit <= limit) {
            int tmp = 0;
            for (int i = 0; i < m; i++) {
                Arrays.fill(cnt[i], 0);
            }

            for (int i = 0; i < n; i++) {
                if ((bit & (1 << i)) != 0) {
                    tmp++;
                    cnt[0][i]++;
                    if (m > 1) {
                        cnt[1][i]++;
                    }
                }
                if (i - 1 >= 0 && (bit & (1 << (i - 1))) != 0) {
                    cnt[0][i]++;
                }
                if (i + 1 < n && (bit & (1 << (i + 1))) != 0) {
                    cnt[0][i]++;
                }
            }

            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i - 1][j] == 0 && cnt[i - 1][j] % 2 == 1 || mat[i - 1][j] == 1 && cnt[i - 1][j] % 2 == 0) {
                        cnt[i][j]++;
                        if (i + 1 < m) {
                            cnt[i + 1][j]++;
                        }
                        tmp++;
                        if (j - 1 >= 0) {
                            cnt[i][j - 1]++;
                        }
                        if (j + 1 < n) {
                            cnt[i][j + 1]++;
                        }
                    }
                }
            }

            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (mat[m - 1][i] == 0 && cnt[m - 1][i] % 2 == 1 || mat[m - 1][i] == 1 && cnt[m - 1][i] % 2 == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans = Math.min(ans, tmp);
            }
            bit++;
        }

        if (ans == 10) {
            return -1;
        } else {
            return ans;
        }
    }


    public static void main(String[] args) {
        int[][] arr = new int[1][2];
        Arrays.sort(arr, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        System.out.println(Arrays.toString(arr));
    }
}
