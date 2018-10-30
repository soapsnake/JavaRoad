/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ld.dubbo.consumer;

import com.alibaba.dubbo.rpc.service.GenericService;
import com.ld.dubbo.api.service.CallbackListener;
import com.ld.dubbo.api.service.CallbackService;
import com.ld.dubbo.api.service.DemoService;
import com.ld.dubbo.api.service.TestGenericService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {

    /**
     * To get ipv6 address to work, add
     * System.setProperty("java.net.preferIPv6Addresses", "true");
     * before running your application.
     */
    public static void main(String[] args) {
//        System.setProperty("java.net.preferIPv6Addresses", "true");    //加在这里没有用,只能添加为虚拟机参数
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-consumer.xml"});
        context.start();

        GenericService barService = (GenericService) context.getBean("barService");
        Object result = barService.$invoke("testBar", new String[] {"java.lang.String", "java.lang.Integer"}, new Object[] { "snake", 100 });
        System.out.println("GenericService call ->" + result);

        TestGenericService testGenericService = (TestGenericService) context.getBean("testGenericService");
        System.out.println("testGenericService --->" + testGenericService.testGen("hello"));

        DemoService demoService = (DemoService) context.getBean("demoService"); // get remote service proxy

        CallbackService callbackService = (CallbackService) context.getBean("callbackService");
        callbackService.addListener("http://10.20.160.198/wiki/display/dubbo/foo.bar", new CallbackListener() {
            public void changed(String msg) {
                System.out.println("callback1:" + msg);
            }
        });

        int i = 0;
        while (true) {
            try {
                Thread.sleep(1000);
                String hello = demoService.sayHello("world"); // call remote method
                System.out.println(i++ + ":: " + hello); // get result

                demoService.checkToken();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
