package com.soapsnake.mydubbo.msg;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-09
 */
public class RpcRequest {

    private String serviceName;

    private String methodName;
    private Class<?> paramType;
    private Object[] args;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?> getParamType() {
        return paramType;
    }

    public void setParamType(Class<?> paramType) {
        this.paramType = paramType;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
