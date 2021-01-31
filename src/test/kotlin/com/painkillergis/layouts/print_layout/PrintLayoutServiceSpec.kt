package com.painkillergis.layouts.print_layout

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PrintLayoutServiceSpec : StringSpec({
  "echo question" {
    PrintLayoutService().answer(PrintLayoutQuestion(Rectangle(1234, 4321))) shouldBe PrintLayout(Rectangle(1234, 4321))
  }
})