package com._ddbb.brianezm.domain.services

import com._ddbb.brianezm.application.services.Services
import com._ddbb.brianezm.domain.infra.config.Tenant
import com._ddbb.brianezm.domain.models.t1.Ticket
import com._ddbb.brianezm.domain.repository.MasterRepo.MasterDDBB

class ServicesImplementation(
    private val repository: MasterDDBB
) : Services {

    @Tenant("read-only")
    override fun readTk(): Collection<Ticket> {
        val list = repository.findAll()
        return list
    }

    @Tenant("master")
    override fun addTk(tk: Ticket) {
        repository.save(tk)
    }
}