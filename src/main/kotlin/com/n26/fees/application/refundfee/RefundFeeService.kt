package com.n26.fees.application.refundfee

import com.n26.auth.Principal
import com.n26.fees.domain.ChargeId
import com.n26.fees.domain.ChargesRepository
import com.n26.fees.domain.RefundPercentage
import com.n26.fees.domain.TransactionManager

data class RefundFeeRequest(
        val chargeId: String,
        val principal: Principal,
        val refundPercentage: Double
)

class RefundFeeService(
        private val chargesRepository: ChargesRepository,
        private val transactionManager: TransactionManager
) {

    fun refund(request: RefundFeeRequest) {
        request.principal.requireSuperUser()

        transactionManager.write {
            val charge = chargesRepository.get(ChargeId(request.chargeId))
            val refundedCharge = charge.refund(RefundPercentage(request.refundPercentage))

            chargesRepository.save(refundedCharge)
        }
    }
}
