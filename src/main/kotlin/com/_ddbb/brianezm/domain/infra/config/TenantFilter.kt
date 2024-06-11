package com._ddbb.brianezm.domain.infra.config
/*
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(1) // Orden de ejecución del filtro (1 indica alta prioridad)
class TenantFilter : Filter {

    @Value("\${defaultTenant}") // Inyectar el inquilino predeterminado
    private lateinit var defaultTenant: String

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest
        val tenantId = req.getHeader("X-TenantID") ?: defaultTenant // Usar el valor predeterminado si no se encuentra el encabezado

        TenantContext.setCurrentTenant(tenantId)

        try {
            chain.doFilter(request, response)
        } catch (e: Exception) {
            // Registrar el error o tomar alguna acción apropiada
            throw e
        } finally {
            TenantContext.clear()
        }

    }
}*/