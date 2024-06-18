package com._ddbb.brianezm.domain.infra.config

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Order(1)
@Aspect
@Component
class TenantDataSourceAspect {

    @Before("@annotation(tenantAnnotation)")
    fun before(joinPoint: JoinPoint, tenantAnnotation: Tenant) { // Inyectamos la anotación @Tenant directamente
        val tenantId = tenantAnnotation.value  // Obtenemos el valor del tenant desde la anotación
        TenantContext.setCurrentTenant(tenantId)
        println("(Desde TenantDataSourceAspect) Tenant establecido en TenantContext: $tenantId") // Log para depuración
    }

    @After("@annotation(tenantAnnotation)") // Aseguramos que el contexto se limpie después de cada método anotado
    fun after() {
        TenantContext.clear()
    }
}