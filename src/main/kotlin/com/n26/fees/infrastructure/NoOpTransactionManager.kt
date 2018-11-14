package com.n26.fees.infrastructure

import com.n26.fees.domain.TransactionManager

class NoOpTransactionManager : TransactionManager {
    override fun <T> readOnly(function: () -> T): T = function.invoke()

    override fun <T> write(function: () -> T): T = function.invoke()
}
