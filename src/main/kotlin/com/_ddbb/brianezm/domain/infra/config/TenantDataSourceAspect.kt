package com._ddbb.brianezm.domain.infra.config

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Order(1)
@Aspect
@Component
class TenantDataSourceAspect {

    @Before("@annotation(tenantAnnotation)")
    fun before(joinPoint: JoinPoint, tenantAnnotation: Tenant) {
        val tenantId = tenantAnnotation.value
        TenantContext.setCurrentTenant(tenantId)
    }

    @After("@annotation(tenantAnnotation)")
    fun after() {
        TenantContext.clear()
    }
}