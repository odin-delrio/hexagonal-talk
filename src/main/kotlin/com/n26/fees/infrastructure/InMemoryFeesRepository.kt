package com.n26.fees.infrastructure

import com.n26.fees.domain.Fee
import com.n26.fees.domain.FeeId
import com.n26.fees.domain.FeesRepository
import java.util.concurrent.ConcurrentHashMap

class InMemoryFeesRepository: FeesRepository {
    private val store = ConcurrentHashMap<FeeId, Fee>()

    override fun save(fee: Fee) {
        store[fee.id] = fee
    }

    override fun deleteFee(feeId: FeeId) {
        store.remove(feeId)
    }
}
