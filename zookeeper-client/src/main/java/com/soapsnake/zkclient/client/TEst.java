package com.soapsnake.zkclient.client;

import java.util.ArrayList;

/**
 * Created by liudun on 2017/7/21.
 */
public class TEst {
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<String>();
        al.add("pen");
        al.add("pencil");
        al.add("ink");
        al.add("notebook");
        al.add("book");
        al.add("books");
        al.add("paper");
        al.add("white board");

        for (int k = 0; k < al.size(); k++) {
            System.out.println("elements are" + al.get(k));

            al.get(k);
        }
    }
}
