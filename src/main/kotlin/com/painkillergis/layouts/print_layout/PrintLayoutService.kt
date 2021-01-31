package com.painkillergis.layouts.print_layout

class PrintLayoutService {
  fun answer(printLayoutQuestion: PrintLayoutQuestion): PrintLayout {
    val (printOption, source) = printLayoutQuestion
    val innerSize =
      if (printOption.height.toDouble() / printOption.width > source.height.toDouble() / source.width)
        Rectangle(
          printOption.width,
          source.height * printOption.width / source.width,
        )
      else
        Rectangle(
          source.width * printOption.height / source.height,
          printOption.height,
        )
    return PrintLayout(
      printOption,
      innerSize,
      Rectangle(
        (printOption.width - innerSize.width) / 2,
        (printOption.height - innerSize.height) / 2,
      ),
    )
  }
}
