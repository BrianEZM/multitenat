package com._ddbb.brianezm.domain.infra.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com._ddbb.brianezm.domain.repository.master"],
    entityManagerFactoryRef = "entityManagerFactoryDbMaster",
    transactionManagerRef = "transactionManagerDbMaster"
)
class MasterDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.master.datasource")
    fun dataSourcePropertiesDbMaster(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean(name = ["dataSourceDbMaster"])
    @Primary
    @ConfigurationProperties("spring.master.datasource")
    fun dataSourceDbMaster(): DataSource {
        return dataSourcePropertiesDbMaster().initializeDataSourceBuilder().build()
    }

    @Primary
    @Bean(name = ["entityManagerFactoryDbMaster"])
    fun entityManagerFactoryDbMaster(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("dataSourceDbMaster") dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(dataSource)
            .packages("com._ddbb.brianezm.domain.models")
            .persistenceUnit("master")
            .properties(mapOf("hibernate.tenant_identifier_resolver" to TenantIdentifierResolverImpl::class.java.name))
            .build()
    }

    @Primary
    @Bean(name = ["transactionManagerDbMaster"])
    fun transactionManagerDbMaster(@Qualifier("entityManagerFactoryDbMaster") entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}
