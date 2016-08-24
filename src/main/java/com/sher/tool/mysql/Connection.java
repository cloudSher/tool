package com.sher.tool.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/8/24.
 */
public class Connection {

    private static String url = "jdbc:mysql://localhost:3306/";
    private static String user = "root";
    private static String pwd="root";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection(url,user,pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void



}
