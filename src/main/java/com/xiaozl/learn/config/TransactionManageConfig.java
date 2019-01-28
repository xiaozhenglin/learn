/*package com.xiaozl.learn.config;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

感觉不需要配置，不知道底层什么样的，配了反而会出错。

@EnableTransactionManagement 
@Configuration
public class TransactionManageConfig implements TransactionManagementConfigurer {
	
	@Resource(name="primaryJpaTransactionManager")
    private PlatformTransactionManager primaryJpaTransactionManager;
	
	@Bean(name = "primaryJpaTransactionManager")
    public PlatformTransactionManager jpaManager( @Autowired @Qualifier(value= "primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
	@Bean(name = "secondaryJpaTransactionManager")
    public PlatformTransactionManager second( @Autowired @Qualifier(value= "secondaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
	//配置默认的事物管理器
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return primaryJpaTransactionManager;
	}
	
	
}
*/