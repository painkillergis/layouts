package com.painkillergis.layouts.print_layout

class PrintLayoutService {
  fun answer(printLayoutQuestion: PrintLayoutQuestion): PrintLayout {
    val (printOption, source) = printLayoutQuestion
    val (innerWidth, innerHeight) =
      if (source.width > source.height)
        listOf(
          printOption.width,
          printOption.height * source.height / source.width,
        )
      else
        listOf(
          printOption.width * source.width / source.height,
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
