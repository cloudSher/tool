package com.sher.tool.mysql;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/8/24.
 */
public class Connection {

    private static String url = "jdbc:mysql://localhost:3306/";
    private static String user = "root";
    private static String pwd="root";
    private static java.sql.Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection connection = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public java.sql.Connection getConnection(){
        return connection;
    }

    public void execute(String sql) throws SQLException {
        //建立sql陈述对象
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public void prepareStatement(String sql) throws SQLException {
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.execute(sql);

    }



}
