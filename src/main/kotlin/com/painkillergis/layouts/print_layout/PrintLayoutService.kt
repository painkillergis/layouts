package com.painkillergis.layouts.print_layout

class PrintLayoutService {
  fun answer(printLayoutQuestion: PrintLayoutQuestion): PrintLayout {
    val (printOption, source) = printLayoutQuestion
    val (innerWidth, innerHeight) =
      if (printOption.height.toDouble() / printOption.width > source.height.toDouble() / source.width)
        listOf(
          printOption.width,
          source.height * printOption.width / source.width,
        )
      else
        listOf(
          source.width * printOption.height / source.height,
          printOption.height,
        )
    return PrintLayout(
      printOption,
      Rectangle(
        (printOption.width - innerWidth) / 2,
        (printOption.height - innerHeight) / 2,
      ),
    )
  }
}
