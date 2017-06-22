package com.ld.thinkinjava.map;

import java.util.Hashtable;

/**
 * Created by liudun on 2017/6/21.
 */
public class SpringDetector {

    public static void main(String[] args) {
        Hashtable<Groundhog,Prediction> hashtable = new Hashtable<>();
        for (int i = 0;i < 10;i++){
            hashtable.put(new Groundhog(i),new Prediction());
            System.out.println("hashtable = "+hashtable + "\n");
        }

        System.out.println("looking up prediction for groundhog #3:");

        Groundhog groundhog = new Groundhog(3);

        if (hashtable.containsKey(groundhog)){
            System.out.println("在hashtable中找到目标: "+ hashtable.get(groundhog));
        }
    }
}
