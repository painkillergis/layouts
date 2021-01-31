package com.painkillergis.layouts.print_layout

class PrintLayoutService {
  fun answer(printLayoutQuestion: PrintLayoutQuestion) =
    PrintLayout(
      printLayoutQuestion.printOption,
      Rectangle(
        (printLayoutQuestion.printOption.width - printLayoutQuestion.source.width) / 2,
        (printLayoutQuestion.printOption.height - printLayoutQuestion.source.height) / 2,
      ),
    )
}
