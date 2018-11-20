package com.ld.leetcode.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author soapsnake
 * @date 2018/11/13
 */
class Question937 {

    public String[] reorderLogFiles(String[] logs) {
        //todo 难就难在数字日志次序不能乱
        /**
         * 1. 字母日志要在数字日志之前
         * 2. 字母日志按字典序
         * 3. 数字日志按原始顺序
         */
        List<String> res = new LinkedList<>();
        for (int i = 0; i < logs.length; i++) {
            if (isStrLog(logs[i])) {
                if (res.isEmpty()) {
                    res.add(logs[i]);
                    continue;
                }
                String head = ((LinkedList<String>) res).element();
                while (head != null) {


                }
            }
        }
        return res.toArray(new String[0]);
    }

    private void change(int i, int j, String[] arr) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int firstChar(String log) {
        String[] strings = log.split(" ");
        char[] chars = strings[1].toCharArray();
        return (int) chars[0];
    }

    private boolean isStrLog(String log) {
        String[] strings = log.split(" ");
        try {
            Integer.parseInt(strings[1]);
            return false;
        }catch (Exception e) {
            return true;
        }
    }

    public static void main(String[] args) {
        Question937 question937 = new Question937();
        String[] strings = {"t kvr", "r 3 1", "i 403", "7 so", "t 54"};
        System.out.println(Arrays.toString(question937.reorderLogFiles(strings)));
    }


}
