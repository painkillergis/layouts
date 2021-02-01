package com.painkillergis.layouts.print_layout

class PrintLayoutService {
  fun answer(printLayoutQuestion: PrintLayoutQuestion): PrintLayout {
    val (printOption, source, margin) = printLayoutQuestion
    val printOptionBody = Rectangle(printOption.width - margin * 2, printOption.height - margin * 2)
    val innerSize = if (printOptionBody.height.toDouble() / printOptionBody.width > source.height.toDouble() / source.width)
      Rectangle(printOptionBody.width, source.height * printOptionBody.width / source.width)
    else
      Rectangle(source.width * printOptionBody.height / source.height, printOptionBody.height)
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
