package com.student.mentalpotion

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ExampleKotestUnitTest : StringSpec({

    "addition works" {
        (2 + 3) shouldBe 5
    }

    "mockk works" {
        val mockedList = mockk<MutableList<String>>()
        every { mockedList.size } returns 42

        mockedList.size shouldBe 42
    }

})