package com.n26.fees.domain.exception

import java.lang.RuntimeException

class InvalidRefundAmountException(amount: Double)
    : RuntimeException("Charge refund of $amount is not valid.") {

    override fun fillInStackTrace(): Throwable {
        return this
    }
}
