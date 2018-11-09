package com.n26.fees.infrastructure.http

import com.n26.auth.SystemUser
import com.n26.fees.application.cancelfee.CancelFeeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CancelFeeController(
        private val cancelFeeService: CancelFeeService
) {
    @DeleteMapping("/fees/{feeId}")
    fun cancelFee(@PathVariable("feeId") feeId: String): ResponseEntity.HeadersBuilder<*> {
        cancelFeeService.cancelFee(feeId, SystemUser)
        return ResponseEntity.noContent()
    }
}
