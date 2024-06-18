package com._ddbb.brianezm.application.services

import com._ddbb.brianezm.domain.models.t1.Ticket

interface Services {

    fun addTk(tk: Ticket)

    fun readTk(): List<Ticket>

}