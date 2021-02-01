package com.painkillergis.layouts.print_layout

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

internal class RankedPrintLayoutsServiceSpec : StringSpec() {
  private val printLayoutService = mockk<PrintLayoutService>()
  private val rankedPrintLayoutsService = RankedPrintLayoutsService(
    printLayoutService,
  )

  init {
    "answer and sort many print layout questions" {
      table(
        headers("firstMargin", "lastMargin"),
        row(Rectangle(0, 0), Rectangle(10, 0)),
        row(Rectangle(0, 0), Rectangle(0, 10)),
        row(Rectangle(5, 6), Rectangle(7, 5)),
        row(Rectangle(6, 5), Rectangle(5, 7)),
      ).forAll { firstMargin, lastMargin ->
        val firstPrintOption = mockk<Rectangle>()
        val lastPrintOption = mockk<Rectangle>()
        val source = mockk<Rectangle>()
        val question = RankedPrintLayoutsQuestion(
          printOptions = listOf(
            firstPrintOption,
            lastPrintOption,
          ),
          source = source,
          margin = 123,
        )
        val firstPrintLayout = mockk<PrintLayout> {
          every { margin } returns firstMargin
        }
        val lastPrintLayout = mockk<PrintLayout> {
          every { margin } returns lastMargin
        }

        every { printLayoutService.answer(PrintLayoutQuestion(firstPrintOption, source, 123)) } returns lastPrintLayout
        every { printLayoutService.answer(PrintLayoutQuestion(lastPrintOption, source, 123)) } returns firstPrintLayout

        rankedPrintLayoutsService.answer(question) shouldBe listOf(
          firstPrintLayout,
          lastPrintLayout,
        )
      }
    }
  }
}