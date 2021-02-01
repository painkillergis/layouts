package com.painkillergis.layouts.print_layout

import kotlinx.serialization.Serializable

@Serializable
data class RankedPrintLayoutsQuestion(val printOptions: List<Rectangle>, val source : Rectangle)