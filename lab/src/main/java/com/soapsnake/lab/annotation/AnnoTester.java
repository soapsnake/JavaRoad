package com.soapsnake.lab.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class AnnoTester {

	public static void main(String[] args) {

		XXXserviceImpl xservice = new XXXserviceImpl();

		Class<?> clazz = XXXserviceImpl.class;
		clazz = xservice.getClass();
		Method[] methods = clazz.getDeclaredMethods();

		System.out.println(Arrays.toString(methods));

		for (Method method : methods) {
			System.out.println("method = "  + method);
			Annotation[] methodAnnos = method.getDeclaredAnnotations();
			Annotation[][] paramAnnos = method.getParameterAnnotations();   //参数注解为什么是二维数组,因为每一个参数都可附加很多个注解
			System.out.println("methodAnnos = " + Arrays.toString(methodAnnos));
			System.out.println("paramAnnos = " + Arrays.deepToString(paramAnnos));

			if (method.isAnnotationPresent(TestMethodAnnotation.class)) {
				try {

					TestMethodAnnotation annotation = method.getAnnotation(TestMethodAnnotation.class);
					if (annotation.field1()) {
						System.out.println("注解为true,通过校验,开始fuck");
						method.invoke(xservice, true, "fuck");
					} else {
						System.out.println("注解为flase,不允许fuck,很遗憾");
					}


					Parameter[] parameters = method.getParameters();
					for (Parameter parameter : parameters) {
						if (parameter.isAnnotationPresent(TestParamAnnotation.class)) {
							System.out.println("name = " + parameter.getName());
							System.out.println("modifiers = " + parameter.getModifiers());
							System.out.println("type = " + parameter.getType());
							System.out.println("paramType = " + parameter.getParameterizedType());
							System.out.println("executable = " + parameter.getDeclaringExecutable());
						}
					}


					for (Annotation[] annotations : paramAnnos) {
						for (Annotation ano : annotations) {
							if (ano instanceof TestParamAnnotation2) {
								System.out.println("ano = " + ano);
							}
						}
					}

				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}



//
//			if (null != annotation)
//				System.out.println(Arrays.toString(annotation.fuckAction()));

//			for (Annotation annotation : annos) {
//				if (annotation.annotationType().equals(TestAnnotation.class)) {
//					TestAnnotation dest = (TestAnnotation) annotation;
//					System.out.println(dest.field1());
//					System.out.println(dest.filed2());
//				}
//			}
		}
	}
}
