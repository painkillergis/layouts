package com.painkillergis.layouts.bspec

internal data class RankedPrintLayoutsQuestion(val printOptions: List<Rectangle>, val source: Rectangle)
internal data class PrintLayoutQuestion(val printOption: Rectangle, val source: Rectangle)
internal data class PrintLayout(val size: Rectangle, val innerSize: Rectangle, val margin: Rectangle)
internal data class Rectangle(val width: Int, val height: Int)
