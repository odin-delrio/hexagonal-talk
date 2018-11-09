package com.n26.fees

import com.n26.fees.infrastructure.FeesApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [FeesApplication::class])
class FeesApplicationTests {

	@Test
	fun contextLoads() {
	}
}
