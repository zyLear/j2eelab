package com.zylear.j2eelab.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by xiezongyu on 2017/9/21.
 */


@Configuration
@MapperScan(basePackages = DataBaseConfig.BASE_PACKAGES,
        sqlSessionTemplateRef = DataBaseConfig.SQL_SESSION_TEMPLATE,
        sqlSessionFactoryRef = DataBaseConfig.SQL_SESSION_FACTORY
)
@EnableTransactionManagement
public class DataBaseConfig {

    static public final String BASE_PACKAGES = "com.zylear.j2eelab.dao";
    static public final String SQL_SESSION_TEMPLATE = "testSqlSessionTemplate";
    static public final String SQL_SESSION_FACTORY = "testSqlSessionFactory";
    public static final String TX_MANAGER = "testTransactionManager";

    @Bean(name = "testDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource testDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean(name = SQL_SESSION_FACTORY)
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("testDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/zylear/j2eelab/mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = TX_MANAGER)
    public DataSourceTransactionManager testTransactionManager(@Qualifier("testDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = SQL_SESSION_TEMPLATE)
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier(SQL_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
