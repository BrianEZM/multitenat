package com._ddbb.brianezm.domain.infra.config

import org.hibernate.context.spi.CurrentTenantIdentifierResolver

class TenantIdentifierResolverImpl : CurrentTenantIdentifierResolver<String> { // Especificamos el tipo String para el identificador de tenant
    override fun resolveCurrentTenantIdentifier(): String {
        return TenantContext.getCurrentTenant() ?: "master" // Obtén el tenant del contexto o usa "master" como valor por defecto
    }

    override fun validateExistingCurrentSessions(): Boolean {
        return true
    }
}