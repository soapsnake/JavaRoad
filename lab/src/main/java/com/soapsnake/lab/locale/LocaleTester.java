package com.soapsnake.lab.locale;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class LocaleTester {

    public static void main(String[] args) {
//		Locale locale = new Locale("zh", "CN");
        Locale locale = Locale.KOREA;


        double qian = 12346654.98;

        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        System.out.println(formatter.format(qian));


        DateFormat dateFormat = DateFormat.getDateInstance(1, locale);
        System.out.println(dateFormat.format(new Date()));


    }
}
