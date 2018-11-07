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
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import com.alibaba.druid.pool.DruidDataSource;
//
//@Configuration
//// 扫描 Mapper 接口并容器管理
//@MapperScan(basePackages = AutoTestMybatisConfig.PACKAGE, sqlSessionFactoryRef = "master1SqlSessionFactory")
//public class AutoTestMybatisConfig {
//    // 精确到 master 目录，以便跟其他数据源隔离
//    static final String PACKAGE = "com.zcc.mapper";
//    static final String MAPPER_LOCATION = "classpath:/mapper/*/*Mapper.xml";
// 
//    @Value("${master1.datasource.url}")
//    private String url;
// 
//    @Value("${master1.datasource.username}")
//    private String user;
// 
//    @Value("${master1.datasource.password}")
//    private String password;
// 
//    @Value("${master1.datasource.driverClassName}")
//    private String driverClass;
// 
//    @Bean(name = "master1DataSource")
//    @Primary
//    public DataSource masterDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driverClass);
//        dataSource.setUrl(url);
//        dataSource.setUsername(user);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
// 
//    @Bean(name = "master1TransactionManager")
//    @Primary
//    public DataSourceTransactionManager masterTransactionManager() {
//        return new DataSourceTransactionManager(masterDataSource());
//    }
// 
//    @Bean(name = "master1SqlSessionFactory")
//    @Primary
//    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("master1DataSource") DataSource masterDataSource)
//            throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(masterDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(AutoTestMybatisConfig.MAPPER_LOCATION));
//        return sessionFactory.getObject();
//    }
//}