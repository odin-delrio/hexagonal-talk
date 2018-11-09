package com.n26.fees.infrastructure.http.auth

import com.n26.auth.Authenticated
import com.n26.auth.PrincipalId
import com.n26.fees.infrastructure.http.UserFromAuthHeaderExtractor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.context.request.NativeWebRequest
import java.util.UUID.randomUUID

class PrincipalArgumentResolverTest {

    private val someAuthHeader = "some header value"
    private val someAuthUser = Authenticated(PrincipalId(randomUUID().toString()))

    private val parameter = mock<MethodParameter>()
    private val userFromHeaderExtractor = mock<UserFromAuthHeaderExtractor>()
    private val request = mock<NativeWebRequest>()

    private val resolver = PrincipalArgumentResolver(userFromHeaderExtractor)

    @Test
    fun `returns null when no auth header and parameter is optional`() {
        whenever(parameter.isOptional).thenReturn(true)
        whenever(request.getHeader(AUTHORIZATION)).thenReturn(null)

        val actual = resolver.resolveArgument(parameter, null, request, null)

        assertThat(actual).isNull()
    }

    @Test(expected = ServletRequestBindingException::class)
    fun `fails when no auth header and parameter is required`() {
        whenever(request.getHeader(AUTHORIZATION)).thenReturn(null)
        whenever(parameter.isOptional).thenReturn(false)

        resolver.resolveArgument(parameter, null, request, null)
    }

    @Test
    fun `returns authenticated when auth exists header`() {
        whenever(request.getHeader(AUTHORIZATION)).thenReturn(someAuthHeader)
        whenever(userFromHeaderExtractor.extractAuthenticated(someAuthHeader)).thenReturn(someAuthUser)

        val actual = resolver.resolveArgument(parameter, null, request, null)

        assertThat(actual).isEqualTo(someAuthUser)
    }
}
