package com.n26.fees.application.cancelfee

import com.n26.auth.Principal
import com.n26.fees.domain.FeeId
import com.n26.fees.domain.FeesRepository

class CancelFeeService(
        private val feesRepository: FeesRepository
) {
    fun cancelFee(feeId: String, principal: Principal) {
        principal.requireSuperUser()

        feesRepository.delete(FeeId(feeId))
    }
}
