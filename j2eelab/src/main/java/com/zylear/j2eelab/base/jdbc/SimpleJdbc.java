package com.zylear.j2eelab.base.jdbc;

import javax.sql.DataSource;
import java.sql.*;

public class SimpleJdbc {

    public static void main(String[] args) throws Exception {


        Connection connection = getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from t_wechat_user;");
        while (resultSet.next()) {
            long id = resultSet.getLong(0);
        }

        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_wechat_user where id = ?;");
        preparedStatement.setLong(1, 1L);
        ResultSet resultSet1 = preparedStatement.executeQuery();
        while (resultSet1.next()) {
            long id = resultSet.getLong(0);
        }

        connection.close();
    }


    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 加载驱动类com.mysql.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");


            // 连接数据库
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/exam?characterEncoding=UTF-8", "root", "root");
            //该类就在 mysql-connector-java-5.0.8-bin.jar中,如果忘记了第一个步骤的导包，就会抛出ClassNotFoundException
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
