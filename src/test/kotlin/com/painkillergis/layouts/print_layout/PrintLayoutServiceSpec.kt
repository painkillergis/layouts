package com.painkillergis.layouts.print_layout

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PrintLayoutServiceSpec : StringSpec({
  "calculate margins when source aspect ratio is wider than print option" {
    PrintLayoutService().answer(
      PrintLayoutQuestion(
        printOption = Rectangle(100, 100),
        source = Rectangle(240, 300),
      ),
    ) shouldBe PrintLayout(
      size = Rectangle(100, 100),
      margin = Rectangle(10, 0),
    )
  }

  "calculate margins when source aspect ratio is taller than print option" {
    PrintLayoutService().answer(
      PrintLayoutQuestion(
        printOption = Rectangle(100, 100),
        source = Rectangle(300, 240),
      ),
    ) shouldBe PrintLayout(
      size = Rectangle(100, 100),
      margin = Rectangle(0, 10),
    )
  }
})