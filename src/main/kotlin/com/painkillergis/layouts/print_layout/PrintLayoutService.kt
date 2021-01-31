package com.painkillergis.layouts.print_layout

class PrintLayoutService {
  fun answer(printLayoutQuestion: PrintLayoutQuestion): PrintLayout {
    val (printOption, source) = printLayoutQuestion
    val innerSize = getInnerSize(printOption, source)
    return PrintLayout(
      printOption,
      innerSize,
      Rectangle(
        (printOption.width - innerSize.width) / 2,
        (printOption.height - innerSize.height) / 2,
      ),
    )
  }

  private val getInnerSize = { (width, height): Rectangle, (sourceWidth, sourceHeight): Rectangle ->
    if (height.toDouble() / width > sourceHeight.toDouble() / sourceWidth)
      Rectangle(width, sourceHeight * width / sourceWidth)
    else
      Rectangle(sourceWidth * height / sourceHeight, height)
  }
}
