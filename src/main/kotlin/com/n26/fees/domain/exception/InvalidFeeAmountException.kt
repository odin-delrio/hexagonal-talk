package com.n26.fees.domain.exception

import java.lang.RuntimeException

class InvalidFeeAmountException(amount: Double)
    : RuntimeException("Fee amount $amount is not valid.") {

    override fun fillInStackTrace(): Throwable {
        return this
    }
}