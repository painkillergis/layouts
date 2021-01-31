package com.painkillergis.layouts.print_layout

import kotlinx.serialization.Serializable

@Serializable
data class PrintLayoutQuestion(val printOption: Rectangle, val source : Rectangle)