package com.n26.fees.application.registerfee

import com.n26.auth.Principal

data class RegisterFeeRequest(
        val id: String,
        val providerName: String,
        val customerId: String,
        val accountId: String,
        val amount: Double,
        val principal: Principal
)