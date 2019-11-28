package com.soapsnake.algorithms.leetcode.str;

public class Question43 {

    public static void main(String[] args) {
        Question43 question43 = new Question43();
        String num1 = "498828660196";
        String num2 = "840477629533";
        System.out.println(question43.multiply2(num1, num2));

    }

    public String multiply(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] products = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {  //易错点1: 应该从最右侧开始算起
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;  //算法核心步骤 //易错点2: 索引如何计算,值是累计值
            }
        }
        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) { //易错点3: 应该从最右侧开始算起
            int tmp = (products[i] + carry) % 10;  //取模得实际值     //易错点4: 要加这个进位值后再进行计算
            carry = (products[i] + carry) / 10;    //除10得进位值
            products[i] = tmp;  //products重复利用
        }
        StringBuilder sb = new StringBuilder();
        for (int num : products) {
            sb.append(num);
        }
        while (sb.length() != 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);    //易错点5: 要把数字前面无意义的0排除掉
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public String multiply2(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "";
        }
        int[] products = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                products[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        int step = 0;
        for (int j = products.length - 1; j >= 0; j--) {
            int temp = (products[j] + step) % 10;
            step = (products[j] + step) / 10;
            products[j] = temp;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i1 : products) {
            stringBuilder.append(i1);
        }
        while (stringBuilder.length() != 0 && stringBuilder.charAt(0) == '0') {
            stringBuilder.deleteCharAt(0);
        }
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }
}
