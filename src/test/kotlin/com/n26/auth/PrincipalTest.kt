package com.n26.auth

import com.n26.auth.exception.ForbiddenException
import com.n26.auth.exception.UnauthorizedException
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class PrincipalTest {

    private val someUserId = PrincipalId("someUserId")
    private val anotherUserId = PrincipalId("anotherUserId")

    @Test(expected = UnauthorizedException::class)
    fun `anonymous user does not have access to any user id`() {
        Anonymous.requireAccessToUserId(someUserId)
    }

    @Test(expected = ForbiddenException::class)
    fun `authenticated user does not have access to another user id`() {
        Authenticated(someUserId).requireAccessToUserId(anotherUserId)
    }

    @Test
    fun `authenticated user have access to his user id`() {
        val authenticated = Authenticated(someUserId)
        assertThat(authenticated.requireAccessToUserId(someUserId)).isEqualTo(authenticated)
    }

    @Test
    fun `admin user has access to other user id`() {
        val principal = Admin(someUserId)
        assertThat(principal.requireAccessToUserId(anotherUserId)).isEqualTo(principal)
    }

    @Test
    fun `system user has access to any user id`() {
        assertThat(SystemUser.requireAccessToUserId(someUserId)).isEqualTo(SystemUser)
        assertThat(SystemUser.requireAccessToUserId(anotherUserId)).isEqualTo(SystemUser)
    }

    @Test(expected = ForbiddenException::class)
    fun `requiring super user on non super user fails`() {
        Anonymous.requireSuperUser()
    }
}
