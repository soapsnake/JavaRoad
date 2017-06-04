package com.vico.license.rpc;

import com.vico.license.pojo.Person;
import com.vico.license.service.HelloWorldService;
import org.apache.thrift.TException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liudun on 2017/6/4.
 */

public class HelloWorldThriftClient {


    public static void main(String[] args) throws TException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:*.xml");

        //代理工厂类为什么能够被转换成client了???
        HelloWorldService.Iface iface = (HelloWorldService.Iface) ctx.getBean("helloWorldService");

        Person person = iface.sayHello("liu");
        System.out.println(person.toString());
    }


}
