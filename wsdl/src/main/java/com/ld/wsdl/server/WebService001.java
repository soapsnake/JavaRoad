package com.ld.wsdl.server;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by kevin on 2017/4/3.
 */
    @WebService
    public class WebService001 {

        public String testWebService(String name) {
            System.out.println("Test sucessfully, you input name is :" + name);
            return name;
        }

        public static void main(String[] args) {
            Endpoint.publish("http://localhost:8888/webService/test", new WebService001());
            System.out.println("webService pulish completed!");
        }
    }





