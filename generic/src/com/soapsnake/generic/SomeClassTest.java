package com.soapsnake.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SomeClassTest {

    public static void main(String[] args) {
        SomeClass<String, Integer> someClass = new SomeClass<>();

        String k = "this is key";
        Integer v = 10;
        someClass.someMethod(k, v);

        Map<String, Integer> k1 = new HashMap<>();
        k1.put("first", 2);

        List<String> v1 = new ArrayList<>();
        v1.add("is!!!");

        SomeClass<Map, List<String>> someClass1 = new SomeClass<>();
        someClass1.someMethod(k1, v1);

        System.out.println(System.getProperty("java.library.path"));


//		String for = "1234134";


        Map<String, String> map = new HashMap<>();

        List<Ren> list = new ArrayList<>();
        Ren ren1 = new Ren(1, "xiaohong", 1);
        Ren ren2 = new Ren(2, "dahei", 0);
        Ren ren3 = new Ren(1, "zhongjian", 1);

        list.add(ren1);
        list.add(ren2);
        list.add(ren3);

        Map<Integer, List<Ren>> renMap = list.stream().collect(Collectors.groupingBy(Ren::getYear));

        System.out.println(renMap);


    }

    static class Ren {
        private int year;
        private String name;
        private int male;

        public Ren(int year, String name, int male) {
            this.year = year;
            this.name = name;
            this.male = male;
        }

        @Override
        public String toString() {
            return "Ren{" +
                    "year=" + year +
                    ", name='" + name + '\'' +
                    ", male=" + male +
                    '}';
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getMale() {
            return male;
        }

        public void setMale(int male) {
            this.male = male;
        }
    }
}
