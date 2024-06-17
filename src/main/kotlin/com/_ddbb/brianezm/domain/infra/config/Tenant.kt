package com._ddbb.brianezm.domain.infra.config

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Tenant(val value: String = "master") // Valor por defecto "master"