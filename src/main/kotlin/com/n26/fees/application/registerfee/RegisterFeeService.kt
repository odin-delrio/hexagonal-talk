package com.n26.fees.application.registerfee

import com.n26.fees.domain.*

data class RegisterFeeRequest(
        val id: String,
        val customerId: String,
        val accountId: String,
        val amount: Double
)

class RegisterFeeService(private val feesRepository: FeesRepository) {

    fun registerFee(request: RegisterFeeRequest) {
        with(request) {
            Fee(
                    id = FeeId(id),
                    account = Account(AccountId(accountId), CustomerId(customerId)),
                    amount = FeeAmount(amount)
            )
        }.apply(feesRepository::save)
    }
}
