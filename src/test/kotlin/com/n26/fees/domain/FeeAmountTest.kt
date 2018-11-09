package com.n26.fees.domain

import com.n26.fees.domain.exception.InvalidFeeAmountException
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class FeeAmountTest {

    @Test
    fun `valid fee amount is created properly`() {
        assertThat(FeeAmount(9.90).amount).isEqualTo(9.9)
    }

    @Test(expected = InvalidFeeAmountException::class)
    fun `negative fee is not allowed`() {
        FeeAmount(-1.0)
    }
}
