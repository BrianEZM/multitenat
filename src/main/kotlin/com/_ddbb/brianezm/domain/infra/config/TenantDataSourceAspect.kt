package com._ddbb.brianezm.domain.infra.config

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class TenantDataSourceAspect {

    @Before("@annotation(com._ddbb.brianezm.domain.infra.config.Tenant)") // Punto de corte antes de métodos anotados con @Tenant
    fun before(joinPoint: JoinPoint) {
        val args = joinPoint.args // Obtiene los argumentos del método
        val tenantId = args.firstOrNull { it is Tenant }?.let { (it as Tenant).value }
            ?: "master" // Extrae el valor del tenant del argumento anotado con @Tenant, si existe. Si no, usa "master" como valor por defecto.
        TenantContext.setCurrentTenant(tenantId) // Establece el tenant en el contexto
    }

    @After("@annotation(com._ddbb.brianezm.domain.infra.config.Tenant)") // Punto de corte después de métodos anotados con @Tenant
    fun after() {
        TenantContext.clear() // Limpia el contexto después de la ejecución del método
    }
}