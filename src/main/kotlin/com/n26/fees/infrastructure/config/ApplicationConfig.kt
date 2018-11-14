package com.n26.fees.infrastructure.config

import com.n26.fees.application.cancelfee.CancelFeeService
import com.n26.fees.application.registerfee.RegisterFeeService
import com.n26.fees.domain.FeesRepository
import com.n26.fees.domain.TransactionManager
import com.n26.fees.infrastructure.InMemoryFeesRepository
import com.n26.fees.infrastructure.NoOpTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {

    @Bean
    fun createRegisterFeeService(feesRepository: FeesRepository, transactionManager: TransactionManager) =
            RegisterFeeService(feesRepository, transactionManager)

    @Bean
    fun createCancelFeeService(feesRepository: FeesRepository) = CancelFeeService(feesRepository)

    @Bean
    fun createFeesRepository(): FeesRepository = InMemoryFeesRepository()

    @Bean
    fun createTransactionManager(): TransactionManager = NoOpTransactionManager()
}
