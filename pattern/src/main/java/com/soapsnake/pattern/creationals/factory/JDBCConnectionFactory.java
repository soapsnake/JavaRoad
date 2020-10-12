package com.soapsnake.pattern.creationals.factory;

import java.util.HashSet;
import java.util.Set;

public class JDBCConnectionFactory {

    //jdbc的最大连接数就是采用这种方式来生成的

    private static final Integer MAX_CONNECTION = 10;

    private static final Set<Object> SET = new HashSet<>(16);

    public static Object createConnection() {
        if (SET.isEmpty()) {
            Object conn = new Object();
            SET.add(conn);
            return conn;
        } else {
            if (SET.size() < MAX_CONNECTION) {
                Object conn = new Object();
                SET.add(conn);
                return conn;
            } else {
                throw new RuntimeException("已经达到最大连接数,无法创建新的连接");
            }
        }
    }


}
