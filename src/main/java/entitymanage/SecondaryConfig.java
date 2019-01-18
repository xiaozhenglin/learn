package entitymanage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SecondaryConfig {

    @Autowired @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;

    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder) {
    	  LocalContainerEntityManagerFactoryBean entityManagerFactory =  builder.dataSource(secondaryDataSource)
                  .packages("com.songsf.learn.entity2").build();
          Properties jpaProperties = new Properties();
          jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
          jpaProperties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
          jpaProperties.put("hibernate.connection.charSet", "utf-8");
          jpaProperties.put("hibernate.show_sql", "false");
          entityManagerFactory.setJpaProperties(jpaProperties);
          return entityManagerFactory;
    }

}