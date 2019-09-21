package com.soapsnake.lab.classloader;

public class KevinLoader extends ClassLoader {


	@Override
	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		System.out.println("class:  "+ name+ " load by customer loader...");
		ClassLoader loader = getParent();
		while (loader != null) {
			System.out.println(loader);
			loader = loader.getParent();
		}
		return super.loadClass(name, resolve);
	}
}
