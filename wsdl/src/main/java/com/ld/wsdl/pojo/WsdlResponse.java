package com.ld.wsdl.pojo;

/**
 * Created by liudun on 2017/4/5.
 */
public class WsdlResponse {

    private String response;
    private int status;
    private String userName;


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
