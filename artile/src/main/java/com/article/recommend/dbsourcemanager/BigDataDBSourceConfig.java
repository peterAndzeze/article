package com.article.recommend.dbsourcemanager;

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
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 咨询平台数据库链接信息
 */
@Configuration("bigDataDBSourceConfig")
@MapperScan(basePackages = {"com.article.recommend.mapper.bigDataMapper"},sqlSessionTemplateRef ="bigDataSessionTemplate" )
public class BigDataDBSourceConfig {


    @Bean("bigDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.bigdata")
    public DataSource bigDataDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean("bigDataSqlSessionFactory")
    public SqlSessionFactory bigDataSqlSessionFactory(@Qualifier("bigDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath*:mybatis/bigdatamapper/*.xml"));
        return bean.getObject();
    }
    @Bean("bigDataTransactionManager")
    public PlatformTransactionManager bigDataTransactionManager(@Qualifier("bigDataSource") DataSource dataSource){
        return  new DataSourceTransactionManager(dataSource);
    }
    @Bean("bigDataSessionTemplate")
    public SqlSessionTemplate bigDataSessionTemplate(@Qualifier("bigDataSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
