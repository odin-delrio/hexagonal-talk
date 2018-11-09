package com.n26.auth.exception

import com.n26.auth.PrincipalId

class ForbiddenException : RuntimeException {

    constructor(message: String) : super(message)

    constructor(principalPrincipalId: PrincipalId, requestedPrincipalIdAccess: PrincipalId)
            : super("The user $principalPrincipalId tried to access to a resource owned by $requestedPrincipalIdAccess")
}
