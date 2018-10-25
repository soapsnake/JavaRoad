package com.ld.annotations;

import java.lang.reflect.Method;

/**
 * Created by liudun on 2017/5/16.
 */
public class App {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Sample2 sample2 = (Sample2) Class.forName("com.ld.annotations.Sample2").newInstance();

        Method[] methods = sample2.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(ExceptionTest.class)) {
                try {
                    method.invoke(null);
                    System.out.println(method.getName() + "测试不通过:未抛出异常!");
                } catch (Exception e) {
                    Class<? extends Exception> exeType = method.getAnnotation(ExceptionTest.class).value();
                    Throwable throwable = e.getCause();
                    if (exeType.isInstance(throwable)) {
                        System.out.println(method.getName() + "测试通过!");
                    } else {
                        System.out.println(method.getName() + "测试不通过:异常类型不匹配!");
                    }
                }
            } else {
                System.out.println(method.getName() + "未添加测试注解!");
            }
        }
    }
}
