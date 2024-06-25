package com._ddbb.brianezm.domain.infra.config

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class MultitenantDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any {
        return TenantContext.getCurrentTenant() ?: "master"
    }
}
