package com.ld.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * There is a robot starting at position (0, 0), the origin, on a 2D plane.
 * Given a sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.
 *
 * The move sequence is represented by a string,
 * and the character moves[i] represents its ith move. Valid moves are R (right),
 * L (left), U (up), and D (down). If the robot returns to the origin after it finishes all of its moves, return true. Otherwise, return false.
 *
 * Note: The way that the robot is "facing" is irrelevant. "R" will always make the robot move to the right once, "L" will always make it move left, etc.
 * Also, assume that the magnitude of the robot's movement is the same for each move.
 */
public class Question657 {

    public boolean judgeCircle(String moves) {

        int[] origin = {0, 0};

        int[] L = {-1, 0};
        int[] R = {1, 0};
        int[] U = {0, 1};
        int[] D = {0, -1};

        Map<Character, int[]> map = new HashMap<>();
        map.put('L', L);
        map.put('R', R);
        map.put('U', U);
        map.put('D', D);
        int[] temp = {0, 0};
        for (char s: moves.toCharArray()){
            for (int i=0;i<origin.length;i++){
                temp[i] += map.get(s)[i];
            }
        }

        return Arrays.equals(temp, origin);
    }

    //666版本解法!!
    public boolean judgeCircle2(String moves) {
        int x = 0;
        int y = 0;
        for (char ch : moves.toCharArray()) {
            if (ch == 'U') y++;
            else if (ch == 'D') y--;
            else if (ch == 'R') x++;
            else if (ch == 'L') x--;
        }
        return x == 0 && y == 0;
    }

        public static void main(String[] args) {
        Question657 question657 = new Question657();
        System.out.println(question657.judgeCircle("LL"));
        System.out.println(question657.judgeCircle("UD"));
        System.out.println(question657.judgeCircle("DU"));

        System.out.println(question657.judgeCircle("LR"));
        System.out.println(question657.judgeCircle("RL"));



    }

}
