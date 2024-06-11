package com._ddbb.brianezm.domain.repository.MasterRepo

import com._ddbb.brianezm.domain.models.t1.Ticket
import org.springframework.data.jpa.repository.JpaRepository

interface MasterDDBB : JpaRepository<Ticket, Int> {}