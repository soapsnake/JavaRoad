package com.soapsnake.algorithms.weekly;

import java.math.BigDecimal;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-11-13
 * JavaRoad
 */
public class WeeklyContext319 {

    public static void main(String[] args) {
        WeeklyContext319 w = new WeeklyContext319();
        System.out.println(w);
    }

    public double[] convertTemperature(double celsius) {
        double[] res = new double[2];
        BigDecimal c = BigDecimal.valueOf(celsius);
        res[0] = c.add(BigDecimal.valueOf(273.15)).doubleValue();
        res[1] = c.multiply(BigDecimal.valueOf(1.80)).add(BigDecimal.valueOf(32.00)).doubleValue();
        return res;
    }
}
