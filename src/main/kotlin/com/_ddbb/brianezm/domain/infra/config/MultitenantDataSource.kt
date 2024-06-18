package com._ddbb.brianezm.domain.infra.config

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class MultitenantDataSource(
    @Qualifier("masterDataSource") private val masterDataSource: DataSource,
    @Qualifier("readOnlyDataSource") private val readOnlyDataSource: DataSource
) : AbstractRoutingDataSource() {

    override fun determineCurrentLookupKey(): Any {
        val tenantId = TenantContext.getCurrentTenant() ?: "master"
        println("Tenant resuelto en MultitenantDataSource: $tenantId") // Agrega este log
        return tenantId
    }

    @PostConstruct
    override fun initialize() {
        val targetDataSources = mutableMapOf<Any, Any>()
        targetDataSources["master"] = masterDataSource
        targetDataSources["read-only"] = readOnlyDataSource // Asegúrate de que el nombre coincida con el tenant
        setTargetDataSources(targetDataSources)
        setDefaultTargetDataSource(masterDataSource)
    }
}
