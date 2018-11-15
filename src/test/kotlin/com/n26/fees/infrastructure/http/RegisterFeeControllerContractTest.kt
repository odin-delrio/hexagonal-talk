package com.n26.fees.infrastructure.http

import com.n26.fees.application.registerfee.RegisterFeeService
import com.n26.fees.application.registerfee.RegisteredFee
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.RequestEntity
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
internal class RegisterFeeControllerContractTest {

    @MockBean
    private lateinit var registerFeeService : RegisterFeeService

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `201 is returned when fee is created`() {
        whenever(registerFeeService.registerFee(any())).thenReturn(RegisteredFee("registeredFeeId"))

        val response = restTemplate.exchange<String>(
                RequestEntity
                        .post(URI("/fees"))
                        .header(AUTHORIZATION, "user-id-12345")
                        .header(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .body("""
                            {
                              "account_id": "account-id-2222",
                              "customer_id": "user-id-12345",
                              "provider_id": "client-generated-id",
                              "provider": "provider-identifier",
                              "amount": 100
                            }
                        """)
        )

        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
    }
}