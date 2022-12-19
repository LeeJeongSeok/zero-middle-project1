package com.lee.zeromiddleproject1.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String driverName = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/wifi?serverTimezone=UTC";//database이름 = testdb
    private final String user = "root";//자신의 workbench 아이디
    private final String pass = "1q2w3e4r";//자신의 workbench 비번

    private static DBConnection connection = new DBConnection();

    private DBConnection(){
        //1. DB Driver를 로딩
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        return connection;
    }

    public Connection getConnection() throws SQLException {
        //2. DB(MySQL)에 접속하기
        return DriverManager.getConnection(url, user, pass);
    }

    public void close(AutoCloseable... closeables) {
        for(AutoCloseable c : closeables) {
            if(c!=null) {
                try {
                    c.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
