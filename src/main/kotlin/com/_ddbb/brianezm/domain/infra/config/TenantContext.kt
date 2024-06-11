package com._ddbb.brianezm.domain.infra.config

object TenantContext {
    private val currentTenant = ThreadLocal.withInitial { "master" }

    fun getCurrentTenant(): String = currentTenant.get()

    fun setCurrentTenant(tenant: String) = currentTenant.set(tenant)

    fun clear() = currentTenant.remove()
}
