package com.soapsnake.springboot.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.soapsnake.springboot.service.LombokTestService;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.extern.java.Log;
import lombok.val;

@Log
public class LombokTestServiceImpl implements LombokTestService {

    @Override
    public void test(@NonNull String path) throws IOException {
        //@cleanup 注解相当于自动添加了流的close操作
        //val 可以使用动态类型,编译时会把val替换成指定类型
        @Cleanup InputStream inputStream = new FileInputStream(path);
        @Cleanup OutputStream outputStream  = new FileOutputStream("output");
        byte[] b = new byte[10000];
        while (true) {
            int r = inputStream.read(b);
            if (r == -1) {
                break;
            }
            outputStream.write(b, 0, r);
        }
    }
}
