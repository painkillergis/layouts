package com.painkillergis.layouts.print_layout

import com.painkillergis.layouts.Rectangle
import com.painkillergis.layouts.RectangleDouble
import kotlinx.serialization.Serializable

@Serializable
data class RankedPrintLayoutsQuestion(val printOptions: List<Rectangle>, val source: RectangleDouble, val margin: Int)

@Serializable
data class PrintLayout(val size: Rectangle, val innerSize: Rectangle, val margin: Rectangle)

@Serializable
data class PrintLayoutQuestion(val printOption: Rectangle, val source: RectangleDouble, val margin: Int)