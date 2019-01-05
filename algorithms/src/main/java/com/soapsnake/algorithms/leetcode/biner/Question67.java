package com.soapsnake.algorithms.leetcode.biner;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/10 22:33
 */
public class Question67 {

    //这个解法也太他妈复杂了吧
    public String addBinary(String a, String b) {
        int aLeng = a.length();
        int bLeng = b.length();
        if (aLeng == 0) {
            return b;
        }
        if (bLeng == 0) {
            return a;
        }
        int leng = aLeng >= bLeng ? aLeng : bLeng;
        String bigger = aLeng >= bLeng ? a : b;
        String smaller = a == bigger ? b : a;
        int diff = bigger.length() - smaller.length();
        StringBuilder res = new StringBuilder();
        int step = 0;
        for (int i = leng - 1; i >= 0; i--) {
            if (i - diff >= 0) {
                int temp = Integer.valueOf(bigger.charAt(i) + "") + Integer.valueOf(smaller.charAt(i - diff) + "");
                if (temp == 2) {
                    res.insert(0, step);
                    step = 1;
                } else {
                    int temp2 = temp + step;
                    if (temp2 == 2) {
                        res.insert(0, 0);
                        step = 1;
                    } else {
                        res.insert(0, temp2);
                        step = 0;
                    }
                }
            } else {
                int temp = Integer.valueOf(bigger.charAt(i) + "");
                if (temp + step == 2) {
                    res.insert(0, 0);
                    step = 1;
                } else {
                    res.insert(0, temp + step);
                    step = 0;
                }
            }
        }
        if (step == 1) {
            res.insert(0, 1);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Question67 question67 = new Question67();
        String a = "110010";
        String b = "10111";
        System.out.println(question67.addBinary3(a, b));
    }

    public String addBinary2(String a, String b) {
        int aLeng = a.length();
        int bLeng = b.length();
        if (aLeng == 0) {
            return b;
        }
        if (bLeng == 0) {
            return a;
        }
        int leng = aLeng >= bLeng ? aLeng : bLeng;
        String bigger = aLeng >= bLeng ? a : b;
        String smaller = a == bigger ? b : a;
        int diff = bigger.length() - smaller.length();
        StringBuilder res = new StringBuilder();
        for (int i = leng - 1; i >= 0; i--) {
            if (i - diff >= 0) {
                int temp = Integer.valueOf(bigger.charAt(i) + "") + Integer.valueOf(smaller.charAt(i - diff) + "");
                res.insert(0, temp);
            } else {
                int temp2 = Integer.valueOf(bigger.charAt(i) + "");
                res.insert(0, temp2);
            }
        }

        String resnum = res.toString();
        boolean step = false;
        //todo 替换字符
//        for (int i = resnum.length() - 1; i >= 0 ; i--) {
//            if (resnum.charAt(i) == '2' ) {
//                if (step) {
//                    resnum.charAt(i) = "1";
//                } else {
//                    resnum.charAt(i) = '0';
//                }
//                step = true;
//            }
//        }
        if (step) {
            resnum = "1" + resnum;
        }
        return resnum;
    }

    public String addBinary3(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            sb.append(sum % 2);  //0, 1, 2  0和2的结果都是0, 1的结果是1,求余运算的特性
            carry = sum / 2;   //只要sum大于2就会发生进位
        }
        if (carry != 0) sb.append(carry);
        System.out.println("reverse前 = " + sb.toString());
        return sb.reverse().toString();  //这里为什么要反转实在是想不明白
    }
}
