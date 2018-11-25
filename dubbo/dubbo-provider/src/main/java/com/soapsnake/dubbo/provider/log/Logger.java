package com.soapsnake.dubbo.provider.log;

import com.soapsnake.dubbo.provider.annotation.LogSpi;

/**
 * @author soapsnake
 * @date 2018/10/29
 */
@LogSpi(value = "snake")     //这个value用来指定默认值
public interface Logger {

    void log();
}
