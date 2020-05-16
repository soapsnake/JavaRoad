package com.soapsnake.algorithms.leetcode.number;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import org.hamcrest.core.Is;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-12
 */
public class Question592 {


    //leetcode592
    public String fractionAddition(String expression) {
        Scanner sc = new Scanner(expression).useDelimiter("/|(?=[-+])");
        int A = 0, B = 1;
        while (sc.hasNext()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            A = A * b + a * B;
            B *= b;
            int g = gcd(A, B);
            A /= g;
            B /= g;
        }
        return A + "/" + B;
    }

    private int gcd(int a, int b) {
        return a != 0 ? gcd(b % a, a) : Math.abs(b);
    }

    /**
     * Example 3:
     * Input:"1/3-1/2"
     * Output: "-1/6"
     * Example 4:
     * Input:"5/3+1/3"
     * Output: "2/1"
     * @param args
     */
    public static void main(String[] args) {
        Question592 question592 = new Question592();

        System.out.println(question592.gcd(1, 3));

        System.out.println(question592.gcd(2, 3));

        System.out.println(question592.gcd(3, 3));
        System.out.println(question592.gcd(4, 3));
        System.out.println(question592.gcd(5, 3));
        System.out.println(question592.gcd(7, 3));
        System.out.println(question592.gcd(12, 3));
        System.out.println(question592.gcd(5, 3));

        String exp = "1/3-1/2";
        System.out.println(question592.fractionAddition(exp));

    }
}
