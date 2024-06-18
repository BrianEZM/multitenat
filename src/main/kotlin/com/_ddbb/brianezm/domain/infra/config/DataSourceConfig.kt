package com._ddbb.brianezm.domain.infra.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean(name = ["masterDataSource"])
    @Primary
    @ConfigurationProperties("spring.master.datasource")
    fun masterDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean(name = ["readOnlyDataSource"])
    @ConfigurationProperties("spring.read-only.datasource")
    fun readOnlyDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }
}