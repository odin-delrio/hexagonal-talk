package com.n26.fees.infrastructure.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.n26.auth.Principal
import com.n26.fees.application.registerfee.RegisterFeeRequest
import com.n26.fees.application.registerfee.RegisterFeeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class RegisterFeeController(
        private val registerFeeService: RegisterFeeService
) {

    data class RegisterFeePayload(
            @JsonProperty("fee_id") val feeId: String,
            @JsonProperty("customer_id") val customerId: String,
            @JsonProperty("account_id") val accountId: String,
            @JsonProperty("amount", required = true) val amount: Double
    )

    @PostMapping("/fees")
    fun registerFee(principal: Principal, @RequestBody body: RegisterFeePayload) : ResponseEntity<Any> {
        return with(body) {
            RegisterFeeRequest(
                    id = feeId,
                    amount = amount,
                    customerId = customerId,
                    accountId = accountId,
                    principal = principal
            )
        }.apply(registerFeeService::registerFee)
        .let { ResponseEntity.created(URI("/fees/${body.feeId}")).build() }
    }
}
