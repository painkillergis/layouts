package com.painkillergis.layouts.print_layout

class PrintLayoutService {
  fun answer(printLayoutQuestion: PrintLayoutQuestion) =
    PrintLayout(printLayoutQuestion.printOption)
}
