package com.n26.fees.domain

import com.n26.fees.domain.exception.ChargeAlreadyRefundedException
import com.n26.fees.domain.exception.InvalidRefundAmountException
import java.time.Instant

data class Charge(
        val id: ChargeId,
        val chargedAt: Instant,
        val refund: Refund? = null
) {
    fun refund(refundPercentage: RefundPercentage): Charge {
        if (refund != null) {
            throw ChargeAlreadyRefundedException(this)
        }

        return this.copy(refund = Refund(refundPercentage))
    }
}

data class ChargeId(val id: String)

data class Refund(
        val amount: RefundPercentage,
        val refundedAt: Instant
) {
    constructor(amount: RefundPercentage) : this(amount, Instant.now())
}

data class RefundPercentage(
        val percentage: Double
) {
    init {
        if (percentage <= 0 || percentage > 100) {
            throw InvalidRefundAmountException(percentage)
        }
    }
}
