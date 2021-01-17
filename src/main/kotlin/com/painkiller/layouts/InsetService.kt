package com.painkiller.layouts

class InsetService {
  fun getLayout(inset: Inset): Layout {
    return Layout(
      inset.width,
      inset.height,
      (inset.width - normalizeWidth(inset)) / 2,
      (inset.height - normalizeHeight(inset)) / 2,
    )
  }

  private fun normalizeWidth(inset: Inset): Int {
    val innerWidth = inset.width - inset.margin * 2
    return if (inset.sourceWidth < inset.sourceHeight)
      (inset.sourceWidth.toDouble() / inset.sourceHeight * innerWidth).toInt()
    else innerWidth
  }

  private fun normalizeHeight(inset: Inset): Int  {
    val innerHeight = inset.height - inset.margin * 2
    return if (inset.sourceHeight < inset.sourceWidth)
      (inset.sourceHeight.toDouble() / inset.sourceWidth * innerHeight).toInt()
    else innerHeight
  }
}
