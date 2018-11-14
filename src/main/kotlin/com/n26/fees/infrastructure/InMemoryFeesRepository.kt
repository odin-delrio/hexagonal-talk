package com.n26.fees.infrastructure

import com.n26.fees.domain.Fee
import com.n26.fees.domain.FeeId
import com.n26.fees.domain.FeeProviderId
import com.n26.fees.domain.FeesRepository
import java.util.concurrent.ConcurrentHashMap

class InMemoryFeesRepository: FeesRepository {
    private val store = ConcurrentHashMap<FeeId, Fee>()

    override fun save(fee: Fee) {
        store[fee.id] = fee
    }

    override fun delete(feeId: FeeId) {
        store.remove(feeId)
    }

    override fun findById(feeId: FeeId): Fee? {
        return store[feeId]
    }

    override fun findByProviderId(providerId: FeeProviderId): Fee? {
        return store.values.first {
            it.providerId == providerId
        }
    }
}
