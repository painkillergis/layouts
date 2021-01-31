package com.painkillergis.layouts.print_layout

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PrintLayoutServiceSpec : StringSpec({
  "calculate margins when there is a width gap" {
    PrintLayoutService().answer(
      PrintLayoutQuestion(
        printOption = Rectangle(100, 100),
        source = Rectangle(80, 100),
      ),
    ) shouldBe PrintLayout(
      size = Rectangle(100, 100),
      margin = Rectangle(10, 0),
    )
  }

  "calculate margins when there is a height gap" {
    PrintLayoutService().answer(
      PrintLayoutQuestion(
        printOption = Rectangle(100, 100),
        source = Rectangle(100, 80),
      ),
    ) shouldBe PrintLayout(
      size = Rectangle(100, 100),
      margin = Rectangle(0, 10),
    )
  }
})