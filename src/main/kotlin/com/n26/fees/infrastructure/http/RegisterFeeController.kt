package com.n26.fees.infrastructure.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.n26.auth.Principal
import com.n26.fees.application.registerfee.AlreadyRegisteredSameFee
import com.n26.fees.application.registerfee.DifferentFeeWithSameProviderIdAlreadyExists
import com.n26.fees.application.registerfee.RegisterFeeRequest
import com.n26.fees.application.registerfee.RegisterFeeService
import com.n26.fees.application.registerfee.RegisteredFee
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class RegisterFeeController(
        private val registerFeeService: RegisterFeeService
) {

    data class RegisterFeePayload(
            @JsonProperty("provider_id") val providerId: String,
            @JsonProperty("provider") val provider: String,
            @JsonProperty("customer_id") val customerId: String,
            @JsonProperty("account_id") val accountId: String,
            @JsonProperty("amount", required = true) val amount: Double
    )

    @PostMapping("/fees")
    fun registerFee(principal: Principal, @RequestBody body: RegisterFeePayload) : ResponseEntity<Any> {
        val result = with(body) {
            RegisterFeeRequest(
                    id = providerId,
                    providerName = provider,
                    amount = amount,
                    customerId = customerId,
                    accountId = accountId,
                    principal = principal
            )
        }.let(registerFeeService::registerFee)

        return when(result) {
            is RegisteredFee -> created(URI("/fees/${result.id}")).body(RegisterFeeResponseBody(result.id))

            is AlreadyRegisteredSameFee -> ok(RegisterFeeResponseBody(result.id))

            is DifferentFeeWithSameProviderIdAlreadyExists -> status(HttpStatus.CONFLICT)
                    .body(RegisterFeeResponseBody(result.id))
        }
    }

    data class RegisterFeeResponseBody(@JsonProperty("provider_id") val provider: String)
}
