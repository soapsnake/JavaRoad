package com.soapsnake.lab.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleTest {


	public static void main(String[] args) {
//		String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//		System.out.println("classPath= " + classPath);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("testMessage", Locale.CHINESE);
//		ResourceBundle resourceBundle1 = ResourceBundle.getBundle(classPath + "resource", Locale.ENGLISH);

		System.out.println(resourceBundle.getString("message.doing"));
//		System.out.println(resourceBundle1.getString("message.doing"));


	}
}
