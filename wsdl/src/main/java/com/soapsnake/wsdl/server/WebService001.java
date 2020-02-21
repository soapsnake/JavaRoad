package com.soapsnake.wsdl.server;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.soapsnake.wsdl.pojo.WsdlResponse;

/**
 * Created by kevin on 2017/4/3.
 */
@WebService
public class WebService001 {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/webService/test", new WebService001());
        System.out.println("webService pulish completed!");
    }

    public WsdlResponse testWebService(String username) {
        System.out.println("Test sucessfully, you input name is :" + username);
//            return userInfo;
        WsdlResponse response = new WsdlResponse();
        response.setResponse("wsdl success!");
        response.setStatus(1);
        response.setUserName(username);
        return response;
    }
}



