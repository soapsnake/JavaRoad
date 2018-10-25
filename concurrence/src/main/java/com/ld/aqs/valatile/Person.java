package com.ld.aqs.valatile;

public class Person {

    volatile int age;

    public static void main(String[] args) {
        Person person = new Person();
        person.age = 5;

        new Thread(new Worker1(person)).start();

        new Thread(new Worker2(person)).start();

        LittlePerson person1 = new Person().new LittlePerson();
        System.out.println(person1.getInnerAge());
        System.out.println(person1.getOutterAge());

    }

    public void doSome() {
        Person.this.age = 10;
    }

    class LittlePerson {
        int name;
        int age;

        LittlePerson() {
            this.age = 100;
        }

        public int getInnerAge() {
            return this.age;         //这里的this指向内部对象
        }

        public int getOutterAge() {
            return Person.this.age;   //这里的this指向的是外部的对象
        }
    }

}
