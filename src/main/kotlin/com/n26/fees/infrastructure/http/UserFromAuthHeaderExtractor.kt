package com.n26.fees.infrastructure.http

import com.n26.auth.Authenticated
import com.n26.auth.PrincipalId

class UserFromAuthHeaderExtractor {

    fun extractAuthenticated(authHeader: String) : Authenticated {
        return Authenticated(PrincipalId(authHeader))
    }
}