package entitymanage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

/**
 * @author xiaozl
 *
 */
@Configuration
public class PrimaryConfig {

    @Autowired 
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManagerPrimary(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }
    
    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
    	   LocalContainerEntityManagerFactoryBean entityManagerFactory =  builder.dataSource(primaryDataSource)
                   .packages("com.songsf.learn.entity1").build();
           Properties jpaProperties = new Properties();
           jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
           jpaProperties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
           jpaProperties.put("hibernate.connection.charSet", "utf-8");
           jpaProperties.put("hibernate.show_sql", "false");
           entityManagerFactory.setJpaProperties(jpaProperties);
           return entityManagerFactory;
    }
    
    @Bean(name = "entityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }
    
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
    	   LocalContainerEntityManagerFactoryBean entityManagerFactory =  builder.dataSource(primaryDataSource)
                   .packages("com.songsf.learn.entity1").build();
           Properties jpaProperties = new Properties();
           jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
           jpaProperties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
           jpaProperties.put("hibernate.connection.charSet", "utf-8");
           jpaProperties.put("hibernate.show_sql", "false");
           entityManagerFactory.setJpaProperties(jpaProperties);
           return entityManagerFactory;
    }

}