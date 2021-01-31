package com.painkillergis.layouts.print_layout

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PrintLayoutServiceSpec : StringSpec({
  "calculate margins when print option is taller than scaled source" {
    PrintLayoutService().answer(
      PrintLayoutQuestion(
        printOption = Rectangle(100, 200),
        source = Rectangle(200, 100),
      ),
    ) shouldBe PrintLayout(
      size = Rectangle(100, 200),
      innerSize = Rectangle(100, 50),
      margin = Rectangle(0, 75),
    )

    PrintLayoutService().answer(
      PrintLayoutQuestion(
        printOption = Rectangle(100, 200),
        source = Rectangle(100, 150),
      ),
    ) shouldBe PrintLayout(
      size = Rectangle(100, 200),
      innerSize = Rectangle(100, 150),
      margin = Rectangle(0, 25),
    )
  }

  "calculate margins when print option is same as scaled source" {
    PrintLayoutService().answer(
      PrintLayoutQuestion(
        printOption = Rectangle(100, 100),
        source = Rectangle(200, 200),
      ),
    ) shouldBe PrintLayout(
      size = Rectangle(100, 100),
      innerSize = Rectangle(100, 100),
      margin = Rectangle(0, 0),
    )
  }

  "calculate margins when print option is wider than scaled source" {
    PrintLayoutService().answer(
      PrintLayoutQuestion(
        printOption = Rectangle(200, 100),
        source = Rectangle(100, 200),
      ),
    ) shouldBe PrintLayout(
      size = Rectangle(200, 100),
      innerSize = Rectangle(50, 100),
      margin = Rectangle(75, 0),
    )

    PrintLayoutService().answer(
      PrintLayoutQuestion(
        printOption = Rectangle(200, 100),
        source = Rectangle(150, 100),
      ),
    ) shouldBe PrintLayout(
      size = Rectangle(200, 100),
      innerSize = Rectangle(150, 100),
      margin = Rectangle(25, 0),
    )
  }
})