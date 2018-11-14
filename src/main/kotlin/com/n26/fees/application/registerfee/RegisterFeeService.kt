package com.n26.fees.application.registerfee

import com.n26.auth.PrincipalId
import com.n26.fees.domain.Account
import com.n26.fees.domain.AccountId
import com.n26.fees.domain.CustomerId
import com.n26.fees.domain.Fee
import com.n26.fees.domain.FeeAmount
import com.n26.fees.domain.FeeId
import com.n26.fees.domain.FeeProviderId
import com.n26.fees.domain.FeesRepository
import com.n26.fees.domain.TransactionManager

class RegisterFeeService(
        private val feesRepository: FeesRepository,
        private val transactionManager: TransactionManager
) {

    fun registerFee(request: RegisterFeeRequest): RegisterFeeResponse {
        request.principal.requireAccessToUserId(PrincipalId(request.customerId))

        val feeProviderId = FeeProviderId(request.id, request.providerName)

        return transactionManager.write {
            val previousExistingFee = feesRepository.findByProviderId(feeProviderId)

            if (previousExistingFee == null) {
                val newFee = createFee(request, feeProviderId)
                feesRepository.save(newFee)

                return@write RegisteredFee(newFee.id.id)
            } else {
                return@write AlreadyRegisteredSameFee(previousExistingFee.id.id)
            }
        }
    }

    private fun createFee(request: RegisterFeeRequest, feeProviderId: FeeProviderId) : Fee {
        return with(request) {
            Fee(
                    id = FeeId(id),
                    providerId = feeProviderId,
                    account = Account(AccountId(accountId), CustomerId(customerId)),
                    amount = FeeAmount(amount)
            )
        }
    }
}
