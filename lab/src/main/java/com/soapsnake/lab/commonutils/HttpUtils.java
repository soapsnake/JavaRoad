package com.soapsnake.lab.commonutils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;

import sun.net.www.http.HttpClient;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-03
 */
public class HttpUtils {

    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();

    public static Object httpPost(List<NameValuePair> urlParameters, String url, String code, ContactInfo info) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(code.getBytes()));
//        HttpEntity postParams = new UrlEncodedFormEntity(urlParameters, StandardCharsets.UTF_8);
        StringEntity entity = new StringEntity(JSON.toJSONString(info), StandardCharsets.UTF_8);
//        httpPost.setEntity(postParams);
        httpPost.setEntity(entity);
        try (CloseableHttpResponse response = HTTP_CLIENT.execute(httpPost)) {
            JSONObject object = JSON.parseObject(EntityUtils.toString(response.getEntity()));
            System.out.println("res = " + object);
            return object;
        } catch (Exception e) {
            return null;
        }
    }


}
