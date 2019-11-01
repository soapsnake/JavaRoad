package com.soapsnake.dubbo.provider.annotation;

import java.lang.annotation.*;

/**
 * @author soapsnake
 * @date 2018/10/29
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LogSpi {
    String value() default "";
}
