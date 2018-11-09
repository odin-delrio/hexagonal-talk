package com.n26.fees.domain

interface FeesRepository {

    fun save(fee: Fee)

    fun deleteFee(feeId: FeeId)
}
