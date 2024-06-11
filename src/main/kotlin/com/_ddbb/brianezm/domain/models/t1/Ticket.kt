package com._ddbb.brianezm.domain.models.t1

import jakarta.persistence.*

@Entity
@Table(name="tickets")
class Ticket (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    val content: String?
) {
}