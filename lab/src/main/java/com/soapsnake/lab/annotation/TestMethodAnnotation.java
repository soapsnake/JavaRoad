package com.soapsnake.lab.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Retention(RetentionPolicy.SOURCE)   //只在源码期间保留,不会被编译成字节码文件
//@Retention(RetentionPolicy.CLASS)    //会被编译成字节码,但是类加载器不会加载,也就是运行期不能反射获取
@Retention(RetentionPolicy.RUNTIME)  //运行期可以通过反射获取

@Target(ElementType.METHOD)   //这个注解只能被加在方法上
public @interface TestMethodAnnotation {

	//应该把注解理解成pojo而不是interface,语法很像interface但是实际上是pojo
	//这个field1()看着非常像一个名叫field1()的接口方法,但是实际上,他表示一个叫field1的布尔变量(Field),默认为true
	boolean field1() default true;

	String filed2() default "hello world";

	String[] fuckAction();
}
