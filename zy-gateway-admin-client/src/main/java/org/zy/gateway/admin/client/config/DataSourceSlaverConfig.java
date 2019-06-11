package org.zy.gateway.admin.client.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 从库配置
 */
@Configuration
@MapperScan(basePackages = "org.zy.gateway.admin.client.dao.slaver", sqlSessionTemplateRef = "slaverSqlSessionTemplate")
public class DataSourceSlaverConfig {

	@Bean(name = "slaverDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.slaver")
	public HikariDataSource dataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "slaverSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("slaverDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/slaver/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "slaverTransactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager(
			@Qualifier("slaverDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "slaverSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("slaverSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}