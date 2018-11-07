///**
// * 版权：zcc
// * 作者：c0z00k8
// * @data 2018年8月24日
// */
//package com.zcc.config;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import com.alibaba.druid.pool.DruidDataSource;
//
///**
// * @author c0z00k8
// *
// */
//@Configuration
////扫描 Mapper 接口并容器管理
//@MapperScan(basePackages = Master2DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "master2SqlSessionFactory")
//public class Master2DataSourceConfig {
//
//	static final String PACKAGE = "com.zcc.mapper2";
//    static final String MAPPER_LOCATION = "classpath:mybatis/*.xml";
//
//    @Value("${master2.datasource.url}")
//    private String url;
//
//    @Value("${master2.datasource.username}")
//    private String user;
//
//    @Value("${master2.datasource.password}")
//    private String password;
//
//    @Value("${master2.datasource.driverClassName}")
//    private String driverClass;
//
//    @Bean(name = "master2DataSource")
//    public DataSource master2DataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driverClass);
//        dataSource.setUrl(url);
//        dataSource.setUsername(user);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//
//    @Bean(name = "master2TransactionManager")
//    public DataSourceTransactionManager master2TransactionManager() {
//        return new DataSourceTransactionManager(master2DataSource());
//    }
//
//    @Bean(name = "master2SqlSessionFactory")
//    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("master2DataSource") DataSource master2DataSource) throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(master2DataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(Master2DataSourceConfig.MAPPER_LOCATION));
//        return sessionFactory.getObject();
//
//    }
//}
