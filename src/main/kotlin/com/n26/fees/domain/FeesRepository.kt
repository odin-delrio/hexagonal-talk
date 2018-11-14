package com.n26.fees.domain

interface FeesRepository {

    fun findByProviderId(providerId: FeeProviderId): Fee?

    fun findById(feeId: FeeId): Fee?

    fun save(fee: Fee)

    fun delete(feeId: FeeId)
}
