package com.ld.thinkinjava.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by liudun on 2017/6/23.
 */
public class Collection1 {

    public static Collection fill(Collection collection,int start,int size){
        for (int i= start ; i< start + size ; i++) collection.add(Integer.toString(i));
            return collection;
    }

    public static Collection fill(Collection collection,int size) {
        return fill(collection, 0, size);
    }

    public static Collection fill(Collection collection){
        return fill(collection,0,10);
    }

    public static Collection newCollection(){
        return fill(new ArrayList());
    }

    public static Collection newCollcetion(int start,int size){
        return fill(new ArrayList(),start,size);
    }

    public static void print(Collection collection){
        for (Iterator x = collection.iterator(); x.hasNext();) System.out.println(x.next() + " ");
        System.out.println("=======");
    }

    public static void main(String[] args) {
        Collection collection = newCollection();
        collection.add("teb");
        collection.add("eleven");
        print(collection);

        Object[] array = collection.toArray();
        String[] str = (String[])collection.toArray(new String[1]);

        System.out.println("Collections.max(collection) = " + Collections.max(collection));
        System.out.println("Collections.min(collection) = " + Collections.min(collection));

        collection.addAll(newCollcetion(10,20));

        print(collection);

        collection.remove("3");

        print(collection);

        collection.remove("3");

        print(collection);

        collection.removeAll(newCollcetion(10,20));

        print(collection);

        collection.addAll(newCollcetion(10,20));

        print(collection);

        System.out.println("collection.contains(\"4\") = " + collection.contains("4"));

        System.out.println("collection.containsAll(newCollection()) = " + collection.containsAll(newCollcetion(10,20)));

        Collection collection2 = newCollection();

    }



}
