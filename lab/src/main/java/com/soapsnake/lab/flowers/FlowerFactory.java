package com.soapsnake.lab.flowers;

public interface FlowerFactory {

    Object getBean(String name);

    <T> T getBean(String name, Class<T> requiredType);


    <T> T getBean(Class<T> requiredType);


    Object getBean(String name, Object... args);


    <T> T getBean(Class<T> requiredType, Object... args);


    boolean containsBean(String name);


    boolean isSingleton(String name);


    boolean isPrototype(String name);


    Class<?> getType(String name);


}
