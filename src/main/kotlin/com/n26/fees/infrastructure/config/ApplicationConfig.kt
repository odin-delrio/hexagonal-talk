package com.n26.fees.infrastructure.config

import com.n26.fees.application.cancelfee.CancelFeeService
import com.n26.fees.application.registerfee.RegisterFeeService
import com.n26.fees.domain.FeesRepository
import com.n26.fees.infrastructure.InMemoryFeesRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {

    @Bean
    fun createRegisterFeeService(feesRepository: FeesRepository) = RegisterFeeService(feesRepository)

    @Bean
    fun createCancelFeeService(feesRepository: FeesRepository) = CancelFeeService(feesRepository)

    @Bean
    fun createFeesRepository() = InMemoryFeesRepository()
}
