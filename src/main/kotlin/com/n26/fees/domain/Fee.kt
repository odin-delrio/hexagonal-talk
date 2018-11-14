package com.n26.fees.domain

import com.n26.fees.domain.exception.InvalidFeeAmountException

data class Fee(
        val id: FeeId,
        val providerId: FeeProviderId,
        val account: Account,
        val amount: FeeAmount
)

data class FeeId(val id: String)

data class FeeProviderId(val id: String, val provider: String)

data class FeeAmount(val amount: Double) {
    init {
        if (amount < 0) {
            throw InvalidFeeAmountException(amount)
        }
    }
}
