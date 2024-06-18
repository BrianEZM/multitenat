package com._ddbb.brianezm.domain.services

import com._ddbb.brianezm.application.services.Services
import com._ddbb.brianezm.domain.infra.config.Tenant
import com._ddbb.brianezm.domain.models.t1.Ticket
import com._ddbb.brianezm.domain.repository.TicketRepository
import org.springframework.stereotype.Service

@Service
class ServicesImplementation(
    private val repositoryTicket: TicketRepository
) : Services {

    @Tenant("read-only")
    override fun readTk(): List<Ticket> {
        val list = repositoryTicket.findAll()
        return list
    }

    @Tenant("master")
    override fun addTk(tk: Ticket) {
        repositoryTicket.save(tk)
    }
}