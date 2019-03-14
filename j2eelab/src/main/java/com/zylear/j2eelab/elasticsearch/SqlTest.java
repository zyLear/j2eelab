package com.zylear.j2eelab.elasticsearch;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.ElasticSearchDruidDataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by xiezongyu on 2019/3/14.
 */
public class SqlTest {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
//        properties.put("url", "jdbc:elasticsearch://172.20.13.216:9300/" + "test_index");
        properties.put("url", "result:elasticsearch://172.20.13.216:9300/elasticsearch");

        DruidDataSource dds = (DruidDataSource) ElasticSearchDruidDataSourceFactory.createDataSource(properties);
        Connection connection = dds.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT  id,title FROM test_index WHERE id=2");
        ResultSet resultSet = ps.executeQuery();
        List<String> result = new ArrayList<String>();
        while (resultSet.next()) {
            System.out.println(resultSet.getLong("id") + "," + resultSet.getString("title"));
        }
        ps.close();
        connection.close();
        dds.close();
    }

}
