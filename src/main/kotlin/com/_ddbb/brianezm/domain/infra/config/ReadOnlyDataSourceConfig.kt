package com._ddbb.brianezm.domain.infra.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com._ddbb.brianezm.domain.repository.readonly"],
    entityManagerFactoryRef = "entityManagerFactoryDbReadOnly",
    transactionManagerRef = "transactionManagerDbReadOnly"
)
class ReadOnlyDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.read-only.datasource")
    fun dataSourcePropertiesDbReadOnly(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean(name = ["dataSourceDbReadOnly"])
    @ConfigurationProperties("spring.read-only.datasource")
    fun dataSourceDbReadOnly(): DataSource {
        return dataSourcePropertiesDbReadOnly().initializeDataSourceBuilder().build()
    }

    @Bean(name = ["entityManagerFactoryDbReadOnly"])
    fun entityManagerFactoryDbReadOnly(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("dataSourceDbReadOnly") dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(dataSource)
            .packages("com._ddbb.brianezm.domain.models")
            .persistenceUnit("read-only")
            .properties(mapOf("hibernate.tenant_identifier_resolver" to TenantIdentifierResolverImpl::class.java.name))
            .build()
    }

    @Bean(name = ["transactionManagerDbReadOnly"])
    fun transactionManagerDbReadOnly(@Qualifier("entityManagerFactoryDbReadOnly") entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}
