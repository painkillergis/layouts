package com.painkillergis.layouts.print_layout

import kotlinx.serialization.Serializable

@Serializable
data class PrintLayout(val size: Rectangle, val innerSize: Rectangle, val margin: Rectangle)