package com.painkillergis.layouts.print_layout

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

internal class RankedPrintLayoutsServiceSpec : StringSpec() {
  private val printLayoutService = mockk<PrintLayoutService>()
  private val rankedPrintLayoutsService = RankedPrintLayoutsService(
    printLayoutService,
  )

  init {
    "answer many print layout questions" {
      val printOption = mockk<Rectangle>()
      val source = mockk<Rectangle>()
      val question = RankedPrintLayoutsQuestion(
        printOptions = listOf(
          printOption,
        ),
        source = source,
      )
      val printLayout = mockk<PrintLayout>()

      every { printLayoutService.answer(PrintLayoutQuestion(printOption, source)) } returns printLayout

      rankedPrintLayoutsService.answer(question) shouldBe listOf(printLayout)
    }
  }
}