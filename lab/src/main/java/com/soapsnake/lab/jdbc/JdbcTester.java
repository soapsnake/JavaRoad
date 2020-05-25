package com.soapsnake.lab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.soapsnake.lab.thread.Person;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-20
 */
public class JdbcTester {

    public boolean makeBooking(Person person) throws SQLException {

        String url = "";
        String user = "";
        String password = "";
        Connection connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);

        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        Statement statement = connection.createStatement();
        String sql = "select * from table where ab = 123";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();


        return false;
    }
}
