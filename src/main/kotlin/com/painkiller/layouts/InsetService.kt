package com.painkiller.layouts

class InsetService {
  fun getLayout(inset: Inset): Layout {
    return if (inset.sourceWidth < inset.sourceHeight)
      Layout(
        inset.width,
        inset.height,
        (inset.width - normalizeWidth(inset)) / 2,
        inset.margin,
      )
    else
      Layout(
        inset.width,
        inset.height,
        inset.margin,
        (inset.height - normalizeHeight(inset)) / 2,
      )
  }

  private fun normalizeWidth(inset: Inset): Int {
    val innerWidth = inset.width - inset.margin * 2
    return (inset.sourceWidth.toDouble() / inset.sourceHeight * innerWidth).toInt()
  }

  private fun normalizeHeight(inset: Inset): Int {
    val innerHeight = inset.height - inset.margin * 2
    return (inset.sourceHeight.toDouble() / inset.sourceWidth * innerHeight).toInt()
  }
}
