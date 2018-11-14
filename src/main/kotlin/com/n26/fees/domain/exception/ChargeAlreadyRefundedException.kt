package com.n26.fees.domain.exception

import com.n26.fees.domain.Charge
import java.lang.RuntimeException

class ChargeAlreadyRefundedException(charge: Charge)
    : RuntimeException("Charge ${charge.id} is already refunded.") {

    override fun fillInStackTrace(): Throwable {
        return this
    }
}
