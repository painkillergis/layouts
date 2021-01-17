package com.painkiller.layouts

class InsetService {
  fun getLayout(inset: Inset): Layout {
    val (marginLeft, marginTop) = if (inset.sourceWidth < inset.sourceHeight)
      listOf(marginLeft(inset), inset.margin)
    else
      listOf(inset.margin, marginTop(inset))
    return Layout(
      inset.width,
      inset.height,
      inset.width - marginLeft * 2,
      inset.height - marginTop * 2,
      marginLeft,
      marginTop,
    )
  }

  private fun marginLeft(inset: Inset): Int {
    if (inset.sourceHeight == 0.0) return 0
    val innerWidth = inset.width - inset.margin * 2
    return (inset.width - (innerWidth * inset.sourceWidth / inset.sourceHeight).toInt()) / 2
  }

  private fun marginTop(inset: Inset): Int {
    if (inset.sourceWidth == 0.0) return 0
    val innerHeight = inset.height - inset.margin * 2
    return (inset.height - (innerHeight * inset.sourceHeight / inset.sourceWidth).toInt()) / 2
  }
}
