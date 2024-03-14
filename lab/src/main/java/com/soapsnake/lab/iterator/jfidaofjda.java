package com.soapsnake.lab.iterator;

import java.math.BigDecimal;

/**
 * Created on 2023-12-17
 */
public class jfidaofjda {

    void disp() {
        System.out.println("data" + data);
    }

    int data;
    static String mes;

    public static void main(String[] args) {
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

//        int x = 15;
//        new jfidaofjda().disp();
//        df.disp();

//        int value = 25;
//        Integer before = value;
//        Long after = ++before == 26 ? 5 : new Long(10);
//        System.out.println(after.intValue() - before.intValue());
//        BufferedReader bs = new BufferedReader(new FileReader("input.dat"));
//        Locale l = new Locale("th", "TH");
//        ResourceBundle bu = ResourceBundle.getBundle("TestBundle1", l);
//        System.out.println("" + bu.getString("sayHello"));

//        Shape shape = new Quad();
//        Quad quadriliater = new Quad();
//        shape = quadriliater;
//        Trangle tr = (Trangle) quadriliater;

//        Shape tri = quadriliater;
//        Trangle tr = (Trangle) shape;
        BigDecimal b1 = new BigDecimal(0);
        BigDecimal b2 = new BigDecimal(10);
        BigDecimal b3 = b1.subtract(b2);
        try {
            String mes = getMe("B");
            System.out.println(mes);
        } catch (Exception e) {
            mes += "F";
            System.out.println(mes);
        } finally {
            System.out.println(mes);
        }
    }

    public static String getMe(String message) throws Exception{
        String data = message.toCharArray()[0] == 66 ? "A" : "D";
        System.out.println(data);
        try {
            if(data == "A") {
                throw new Exception();
            } else {
            }
        } catch (Exception e) {
            System.out.println(e);
            message += "C";
        }finally {
            message += "E";
        }
        return message;
    }
}

interface Math {
}

class Shape {}
class Quad extends  Shape {}
class Trangle extends  Shape{}
