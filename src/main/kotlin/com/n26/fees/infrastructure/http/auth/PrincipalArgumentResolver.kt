package com.n26.fees.infrastructure.http.auth

import com.n26.auth.Principal
import com.n26.fees.infrastructure.http.UserFromAuthHeaderExtractor
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class PrincipalArgumentResolver(
        private val userFromAuthExtractor: UserFromAuthHeaderExtractor
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == Principal::class.java
    }

    override fun resolveArgument(
            parameter: MethodParameter,
            mavContainer: ModelAndViewContainer?,
            webRequest: NativeWebRequest,
            binderFactory: WebDataBinderFactory?
    ): Any? {
        return webRequest
                .getHeader(AUTHORIZATION)
                ?.let { userFromAuthExtractor.extractAuthenticated(it) }
                .also {
                    if (it == null && !parameter.isOptional) {
                        throw ServletRequestBindingException("Missing request header $AUTHORIZATION")
                    }
                }
    }
}
