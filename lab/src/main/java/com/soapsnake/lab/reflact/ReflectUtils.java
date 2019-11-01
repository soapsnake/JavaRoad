package com.soapsnake.lab.reflact;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.regex.Pattern;

public class ReflectUtils {

    private static Pattern pattern = Pattern.compile("\\w+\\.");

    public static void main(String[] args) {
        String classname = "com.soapsnake.lab.reflact.ReflactTester";
        test(classname);
    }

    public static void test(String className) {

        try {
            Class<?> clazz = Class.forName(className);
            //获取类的所有方法
            Method[] methods = clazz.getMethods();
            //获取类的所有构造方法
            Constructor[] constructors = clazz.getConstructors();
            //获取类的所有注解(类级别)
            Annotation[] annotations = clazz.getAnnotations();
            //获取类的所有注解
            Annotation[] annotations1 = clazz.getDeclaredAnnotations();

            //获取类的所有属性
            Field[] fields = clazz.getDeclaredFields();

            //获取类的所有接口???
            Type[] types = clazz.getGenericInterfaces();

            //获取类的所有????
            TypeVariable<? extends Class<?>>[] typeVariables = clazz.getTypeParameters();

            System.out.println("================will show you all methods========");
            for (Method method : methods) {
//				System.out.println(pattern.matcher(method.toString()).replaceAll(""));
                System.out.println(method.toString());
            }

            System.out.println("================will show you all constructors========");
            for (Constructor constructor : constructors) {
                System.out.println(pattern.matcher(constructor.toString()).replaceAll(""));
            }

            System.out.println("================will show you all Annotations========");
            for (Annotation annotation : annotations) {
                System.out.println(annotation.toString());
            }

            System.out.println("================will show you all Annotations1========");
            for (Annotation annotation : annotations1) {
                System.out.println(annotation.toString());
            }

            System.out.println("================will show you all fields========");
            for (Field annotation : fields) {
                System.out.println(annotation.toString());
            }

            System.out.println("================will show you all types========");
            for (Type annotation : types) {
                System.out.println(annotation.toString());
            }

            System.out.println("================will show you all typeVariables========");
            for (Type annotation : typeVariables) {
                System.out.println(annotation.toString());
            }

            System.out.println("================will show you all lines = " + (methods.length + constructors.length));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
