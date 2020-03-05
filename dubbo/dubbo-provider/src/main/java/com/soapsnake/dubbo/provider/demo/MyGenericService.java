package com.soapsnake.dubbo.provider.demo;

import java.util.Arrays;

import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * @author soapsnake
 * @date 2018/10/31
 * <p>
 * 泛化实现
 */
public class MyGenericService implements GenericService {
    @Override
    public Object $invoke(String methodName, String[] parameterTypes, Object[] args) throws GenericException {
        System.out.println("MyGenericService been called!!!, args: " + Arrays.toString(args));
        if ("testGen".equals(methodName)) {
            return "Welcome " + args[0];
        }
        return null;
    }
}
