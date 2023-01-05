package br.com.pb.barbershop.msscheduling.framework.adapters.out.repository.config;

import br.com.pb.barbershop.msscheduling.domain.model.scheduling.Scheduling;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "br.com.pb.barbershop.msscheduling.framework.adapters.out.repository.scheduling",
    entityManagerFactoryRef = "schedulingEntityManagerFactory",
    transactionManagerRef = "schedulingTransactionManager"
)
public class SchedulingRepositoryConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.scheduling")
    DataSourceProperties schedulingDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean schedulingEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
            .dataSource(schedulingDataSourceProperties().initializeDataSourceBuilder().build())
            .packages(Scheduling.class)
            .build();
    }

    @Bean
    @Primary
    PlatformTransactionManager schedulingTransactionManager(EntityManagerFactory schedulingEntityManagerFactory) {
        return new JpaTransactionManager(schedulingEntityManagerFactory);
    }
}
