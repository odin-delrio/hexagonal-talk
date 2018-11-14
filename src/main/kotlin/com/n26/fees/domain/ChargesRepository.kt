package com.n26.fees.domain

interface ChargesRepository {

    fun get(id: ChargeId) : Charge

    fun save(charge: Charge)
}
