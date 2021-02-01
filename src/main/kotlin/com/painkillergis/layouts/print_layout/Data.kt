package com.painkillergis.layouts.print_layout

import kotlinx.serialization.Serializable

@Serializable
data class PrintLayout(val size: Rectangle, val innerSize: Rectangle, val margin: Rectangle)

@Serializable
data class PrintLayoutQuestion(val printOption: Rectangle, val source: Rectangle)

@Serializable
data class RankedPrintLayoutsQuestion(val printOptions: List<Rectangle>, val source: Rectangle)

@Serializable
data class Rectangle(val width: Int, val height: Int)
