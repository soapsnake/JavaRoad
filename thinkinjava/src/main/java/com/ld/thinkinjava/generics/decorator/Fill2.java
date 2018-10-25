package com.ld.thinkinjava.generics.decorator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudun on 2017/7/16.
 */
public class Fill2 {

    public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
        for (int i = 0; i < size; i++) {
            try {
                addable.add(classToken.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            List<? extends Integer> list = new ArrayList<>();
//            list.add(1);
        }
    }

//    public static <T> void fill(Addable<T> addable, Generator<T> generator, int size) {
//        for (int i = 0; i < size; i++) {
//            addable.add(generator.next());
//        }
//    }


}
