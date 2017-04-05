package com.ld.wsdl.server;

import com.ld.wsdl.pojo.UserInfo;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by kevin on 2017/4/3.
 */
    @WebService
    public class WebService001 {

        public UserInfo testWebService(UserInfo userInfo) {
            System.out.println("Test sucessfully, you input name is :" + userInfo.getUserName());
            System.out.println("Test sucessfully, you input age is :" + userInfo.getUserAge());
            return userInfo;
        }

        public static void main(String[] args) {
            Endpoint.publish("http://localhost:8888/webService/test", new WebService001());
            System.out.println("webService pulish completed!");
        }
    }



