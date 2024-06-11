package com._ddbb.brianezm.application.controller

import com._ddbb.brianezm.domain.models.t1.Ticket
import com._ddbb.brianezm.domain.repository.MasterRepo.MasterDDBB
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ControllerDB1(
    val repo1: MasterDDBB,
) {

    @PostMapping("/post")
    fun insertTest(): ResponseEntity<Ticket> {
        val tk1 = Ticket(null, "asdasdasd")
        repo1.save(tk1)
        return ResponseEntity.ok(tk1)
    }
}