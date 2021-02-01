package com.painkillergis.layouts.print_layout

class RankedPrintLayoutsService(val printLayoutService: PrintLayoutService) {
  fun answer(rankedPrintLayoutsQuestion: RankedPrintLayoutsQuestion): List<PrintLayout> {
    return rankedPrintLayoutsQuestion.printOptions.map {
      printLayoutService.answer(
        PrintLayoutQuestion(
          it,
          rankedPrintLayoutsQuestion.source
        )
      )
    }
  }
}
