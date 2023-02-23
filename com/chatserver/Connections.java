package com.chatserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    private static Connection c;

    public void setC() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        c =DriverManager.getConnection("jdbc:mysql://10.52.0.131:3306/proapp", "todoadmins", "todo@111");
    }

    public Connection getC() {
        return c;
    }   
}
