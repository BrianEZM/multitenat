package com._ddbb.brianezm.domain.infra.config

import org.hibernate.context.spi.CurrentTenantIdentifierResolver

class TenantIdentifierResolverImpl : CurrentTenantIdentifierResolver<String> {

    override fun resolveCurrentTenantIdentifier(): String {
        return TenantContext.getCurrentTenant() ?: "master" // Obtén el tenant del contexto o usa "master" como valor por defecto
    }

    override fun validateExistingCurrentSessions(): Boolean {
        return true
    }
}