package com._ddbb.brianezm.domain.repository.readonly

import com._ddbb.brianezm.domain.models.t1.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReadOnlyRepository : JpaRepository<Ticket, Int> {}