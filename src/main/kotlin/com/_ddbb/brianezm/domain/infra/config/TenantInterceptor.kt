package com._ddbb.brianezm.domain.infra.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class TenantInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val tenantId = request.getHeader("X-TenantID") ?: "master" // Obtén el tenantId del encabezado o usa "master" como predeterminado
        TenantContext.setCurrentTenant(tenantId)
        return true // Continúa con la cadena de procesamiento
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        TenantContext.clear() // Limpia el contexto del tenant después de procesar la solicitud
    }
}