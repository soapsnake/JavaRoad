package com.soapsnake.lab.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class IeratorTest {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person("张三", 10, 1);
        Person p2 = new Person("李四", 11, 0);
        Person p3 = new Person("王五", 12, 1);

        list.add(p1);
        list.add(p2);
        list.add(p3);

        //一边遍历一边remove不会抛异常
        //但是一边遍历一边add必抛异常
//		for (Person person : list) {
//			if (person.gender == 0) {
//				list.remove(person);

//				Person p4 = new Person("二球", 90, 0);
//				list.add(p4);
//			}
//		}

        //iterator一边遍历一边删除不会抛异常
        //listIteraotor有add接口,可以一边遍历一边添加不会抛异常
        ListIterator<Person> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Person curr = iterator.next();
            if (curr.gender == 0) {
                iterator.remove();

                Person p4 = new Person("二球", 90, 0);
                iterator.add(p4);
            }
        }
        System.out.println(list);

        list.removeAll(Arrays.asList(new Person("三傻", 1, 0)));

        System.out.println(list);
    }

}
