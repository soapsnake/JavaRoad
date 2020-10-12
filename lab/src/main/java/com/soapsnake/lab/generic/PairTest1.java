package com.soapsnake.lab.generic;

/**
 * Hello world!
 */
public class PairTest1 {
    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println("min= " + mm.getFirst());
        System.out.println("max= " + mm.getSecond());


//    	double middle = (double) com.soapsnake.generic.ArrayAlg.getMiddle(3.14 , 1729, 3);
//    	System.out.println(middle);

    }
}
