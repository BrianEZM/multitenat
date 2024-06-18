package com._ddbb.brianezm.domain.infra.config

import org.springframework.core.env.Environment
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

@Configuration
class MultitenantConfiguration(
    @Value("\${spring.tenant.default}") private val defaultTenant: String,
    private val environment: Environment
) {

    @Bean
    fun dataSource(): AbstractRoutingDataSource {
        val resolvedDataSources = mutableMapOf<Any, Any>()

        // Configuración tenant "master"
        val masterDataSourceBuilder = DataSourceBuilder.create()
            .driverClassName(environment.getProperty("spring.master.datasource.driver-class-name")) // Adaptado a tu estructura
            .username(environment.getProperty("spring.master.datasource.username"))
            .password(environment.getProperty("spring.master.datasource.password"))
            .url(environment.getProperty("spring.master.datasource.jdbc-url"))
        resolvedDataSources["master"] = masterDataSourceBuilder.build()

        // Configuración tenant "read-only"
        val readOnlyDataSourceBuilder = DataSourceBuilder.create()
            .driverClassName(environment.getProperty("spring.read-only.datasource.driver-class-name")) // Adaptado a tu estructura
            .username(environment.getProperty("spring.read-only.datasource.username"))
            .password(environment.getProperty("spring.read-only.datasource.password"))
            .url(environment.getProperty("spring.read-only.datasource.jdbc-url"))
        resolvedDataSources["read-only"] = readOnlyDataSourceBuilder.build()

        val dataSource = MultitenantDataSource()
        dataSource.setTargetDataSources(resolvedDataSources)
        resolvedDataSources[defaultTenant]?.let { dataSource.setDefaultTargetDataSource(it) }
        dataSource.afterPropertiesSet()
        return dataSource
    }
}