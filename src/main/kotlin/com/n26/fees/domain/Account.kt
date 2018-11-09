package com.n26.fees.domain

data class Account(
        val accountId: AccountId,
        val customerId: CustomerId
)

data class AccountId(val id: String)
