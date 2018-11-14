package com.n26.fees.domain

interface TransactionManager {

    fun <T> readOnly(function: () -> T) : T

    fun <T> write(function: () -> T) : T
}
