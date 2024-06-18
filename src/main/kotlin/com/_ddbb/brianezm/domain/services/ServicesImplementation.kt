package com._ddbb.brianezm.domain.services

import com._ddbb.brianezm.application.services.Services
import com._ddbb.brianezm.domain.infra.config.Tenant
import com._ddbb.brianezm.domain.models.t1.Ticket
import com._ddbb.brianezm.domain.repository.master.MasterRepository
import com._ddbb.brianezm.domain.repository.readonly.ReadOnlyRepository
import org.springframework.stereotype.Service

@Service
class ServicesImplementation(
    private val repositoryMaster: MasterRepository,
    private val repositoryReadOnly: ReadOnlyRepository
) : Services {

    @Tenant("read-only")
    override fun readTk(): List<Ticket> {
        val list = repositoryReadOnly.findAll()
        return list
    }

    @Tenant("master")
    override fun addTk(tk: Ticket) {
        repositoryMaster.save(tk)
    }
}