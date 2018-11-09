package com.n26.fees.infrastructure.config

import com.n26.fees.infrastructure.http.UserFromAuthHeaderExtractor
import com.n26.fees.infrastructure.http.auth.PrincipalArgumentResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfig {

    @Bean
    fun getUserFromAuthHeaderExtractor() = UserFromAuthHeaderExtractor()

    @Bean
    fun authenticatedArgumentResolver(userExtractor: UserFromAuthHeaderExtractor): PrincipalArgumentResolver {
        return PrincipalArgumentResolver(userExtractor)
    }
}
