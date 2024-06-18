package com._ddbb.brianezm.domain.repository.master

import com._ddbb.brianezm.domain.models.t1.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasterRepository : JpaRepository<Ticket, Int> {}