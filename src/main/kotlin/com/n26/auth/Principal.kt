package com.n26.auth

import com.n26.auth.exception.ForbiddenException
import com.n26.auth.exception.UnauthorizedException

sealed class Principal {

    abstract fun requireAccessToUserId(id: PrincipalId): Principal

    fun requireSuperUser() {
        if (this !is SuperUser) {
            throw ForbiddenException("SuperUser needed")
        }
    }
}

data class PrincipalId(val id: String)

object Anonymous : Principal() {
    override fun requireAccessToUserId(id: PrincipalId): Anonymous {
        throw UnauthorizedException()
    }
}

data class Authenticated(val id: PrincipalId) : Principal() {
    override fun requireAccessToUserId(id: PrincipalId): Authenticated {
        if (this.id != id) {
            throw ForbiddenException(this.id, id)
        }

        return this
    }
}

sealed class SuperUser : Principal()

data class Admin(val id: PrincipalId) : SuperUser() {
    override fun requireAccessToUserId(id: PrincipalId): Admin {
        return this
    }
}

object SystemUser : SuperUser() {
    override fun requireAccessToUserId(id: PrincipalId): SystemUser {
        return this
    }
}
