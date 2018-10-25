package com.kevin.snake.random;

public class Randow2 {

    private static final String NUMBER_REGEX = "^[1-9]\\d*|0$";

    public static void main(String[] args) {
        Randow2 randow2 = new Randow2();
        String[] inter = new String[]{"1200", "2000"};
        System.out.println(randow2.checkInterval(inter));

        System.out.println("10".matches(NUMBER_REGEX));
    }

    private boolean checkInterval(String[] interval) {
        if (interval == null) {
            return false;
        }

        if (interval.length != 2) {
            return false;
        }
        return interval[0].matches(NUMBER_REGEX) && interval[1].matches(NUMBER_REGEX);
    }
}
