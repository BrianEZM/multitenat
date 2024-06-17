package com._ddbb.brianezm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class BrianezmApplication

fun main(args: Array<String>) {
	runApplication<BrianezmApplication>(*args)
}
