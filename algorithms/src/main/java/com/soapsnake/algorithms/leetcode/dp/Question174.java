package com.soapsnake.algorithms.leetcode.dp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-21
 */
public class Question174 {

    /**
     * More concise with some explanation:
     * We cannot determines the health without knowing how much health loss is waiting for us in the future. Thus we
     * need to consider from the opposite way. i.e. from destination to start position.
     *
     * First we need to know what's the min health we need at the princess location?
     * The key is assuming we have M[m - 1][n - 1] health when we reach dungeon[m - 1][n - 1] before fighting, then
     * 1.1 we must have at least 1 blood, i.e. M[m - 1][n - 1] >= 1
     * 1.2 after we fight with demons, we need to have at least 1 blood to be alive, i.e. M[m - 1][n - 1] + dungeon[m
     * - 1][n - 1] >= 1
     * With above, we have: M[m - 1][n - 1] >= 1 and M[m - 1][n - 1] >= 1 - dungeon[i][j], thus M[m - 1][n - 1] >=
     * max(1, 1 - dungeon[m - 1][n - 1])
     * Then what about the previous min health we should have, let's denote cur min health as cur?
     * 2.1 we must have at least 1 blood, i.e. prev >= 1
     * 2.2 after we fight with demons, we have cur health, i.e. prev + dungeon[prev] = cur
     * From above, we have prev >= 1 and prev >= cur - dungeon[prev], thus prev = max(1, cur - dungeon[prev])
     * I will leave rest explanation to you :)
     * @param dungeon
     * @return
     */
    //leetcode174
    public int calculateMinimumHP(int[][] dungeon) {
        // corner case
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }

        // M[i][j] represents the health when I reach dungeon[i][j]
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] M = new int[m][n];
        // initialization:
        // M[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
        // or M[m - 1][n - 1] = dungeon[i][j] >= 0? 1 : -dungeon[i][j] + 1;
        // induction rule:
        // M[i][n - 1] = max(M[i + 1][n - 1] - dungeon[i][n - 1], 1)
        // M[m - 1][j] = max(M[m - 1][j + 1] - dungeon[m - 1][j], 1)
        // M[i][j] = min(max(M[i][j + 1] - dungeon[i][j], 1), max(M[i + 1][j] - dungeon[i][j], 1))
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    M[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
                } else if (i == m - 1) {
                    M[m - 1][j] = Math.max(M[m - 1][j + 1] - dungeon[m - 1][j], 1);
                } else if (j == n - 1) {
                    M[i][n - 1] = Math.max(M[i + 1][n - 1] - dungeon[i][n - 1], 1);
                } else {
                    M[i][j] = Math.min(Math.max(M[i + 1][j] - dungeon[i][j], 1),
                            Math.max(M[i][j + 1] - dungeon[i][j], 1));
                }
            }
        }
        return M[0][0];
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys", new HashSet<>());
        if (Collection.class.isAssignableFrom(map.get("sys").getClass())) {
            System.out.println("sdfsafda");
        }
    }
}
