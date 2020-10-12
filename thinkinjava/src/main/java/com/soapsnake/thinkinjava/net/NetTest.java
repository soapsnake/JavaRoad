package com.soapsnake.thinkinjava.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;

/**
 * Created by soapsnake on 2017/9/14.
 */
public class NetTest {

    public static void main(String[] args) throws IOException {

        try {
            NetworkInterface networkInterface = NetworkInterface.getByName("en0");

            if (networkInterface == null) {
                System.out.println("no such interface :en0");
            } else {
                System.out.println("getDisplayName: " + networkInterface.getDisplayName());


                for (byte interfaceEnumeration : networkInterface.getHardwareAddress()) {
                    System.out.println("getHardwareAddress: " + interfaceEnumeration);
                }
                System.out.println("getInetAddresses: " + networkInterface.getInetAddresses().toString());
                System.out.println("getInterfaceAddresses: " + networkInterface.getInterfaceAddresses());
                System.out.println("getName: " + networkInterface.getName());
                System.out.println("getParent: " + networkInterface.getParent());
                System.out.println("getSubInterfaces: " + networkInterface.getSubInterfaces());
                System.out.println("supportsMulticast: " + networkInterface.supportsMulticast());
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }


        try {
            URL url = new URL("http://www.baidu.com");
            System.out.println("getAuthority: " + url.getAuthority());
            System.out.println("getContent: " + url.getContent());
            System.out.println("getDefaultPort: " + url.getDefaultPort());
            System.out.println("getHost: " + url.getHost());
            System.out.println("getPath: " + url.getPath());
            System.out.println("getProtocol: " + url.getProtocol());
            System.out.println("getUserInfo: " + url.getUserInfo());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

}
