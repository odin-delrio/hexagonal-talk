package com.n26.fees.infrastructure.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.n26.fees.application.registerfee.RegisterFeeRequest
import com.n26.fees.application.registerfee.RegisterFeeService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterFeeController(
        private val registerFeeService: RegisterFeeService
) {

    data class RegisterFeePayload(
            @JsonProperty("customer_id") val customerId: String,
            @JsonProperty("account_id") val accountId: String,
            @JsonProperty("amount", required = true) val amount: Double
    )

    @PutMapping("/fees/{feeId}")
    fun registerFee(@PathVariable("feeId") feeId: String, @RequestBody body: RegisterFeePayload) {
        with(body) {
            RegisterFeeRequest(
                    id = feeId,
                    amount = amount,
                    customerId = customerId,
                    accountId = accountId
            )
        }.apply(registerFeeService::registerFee)
    }
}
