package com.soapsnake.lab.classloader;

public class ClassLoaderTester {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {
        ClassLoader classLoader = new KevinLoader();
//		try {
//			Class<?> claz = classLoader.loadClass("com.soapsnake.lab.classloader.Person");
//			Object o = claz.newInstance();
//			System.out.println(o.toString());
//		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
//			e.printStackTrace();
//		}

        //forName会初始化static块
        Class.forName("com.soapsnake.lab.classloader.Person");


        //奇怪吧,loadCLass不会初始化static块
        classLoader.loadClass("com.soapsnake.lab.classloader.ClassLoaderTester");

        Thread.currentThread().sleep(0);

    }


}
