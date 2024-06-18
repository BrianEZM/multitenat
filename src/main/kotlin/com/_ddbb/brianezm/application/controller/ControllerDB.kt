package com._ddbb.brianezm.application.controller

import com._ddbb.brianezm.application.services.Services
import com._ddbb.brianezm.domain.models.t1.Ticket
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ControllerDB(
    private val serv: Services
) {

    @GetMapping("/list")
    fun listAll(): ResponseEntity<List<Ticket>> {
        val list = serv.readTk()
        return ResponseEntity.ok(list)
    }

    @PostMapping("/post")
    fun insertTest(@RequestBody body: String): ResponseEntity<Ticket> {
        val tk1 = Ticket(null, body)
        serv.addTk(tk1)
        return ResponseEntity.ok(tk1)
    }
}