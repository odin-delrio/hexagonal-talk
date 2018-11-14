package com.n26.fees.application.registerfee

sealed class RegisterFeeResponse(open val id: String)

data class RegisteredFee(override val id: String) : RegisterFeeResponse(id)

data class AlreadyRegisteredSameFee(override val id: String) : RegisterFeeResponse(id)

data class DifferentFeeWithSameProviderIdAlreadyExists(override val id: String) : RegisterFeeResponse(id)
