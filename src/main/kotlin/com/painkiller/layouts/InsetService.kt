package com.painkiller.layouts

class InsetService {
  fun getLayout(inset: Inset): Layout {
    val ratio = inset.sourceWidth.toDouble() / inset.sourceHeight
    return if (inset.sourceWidth < inset.sourceHeight)
      Layout(
        inset.width,
        inset.height,
        marginLeft(inset.width, inset.margin, ratio),
        inset.margin,
      )
    else
      Layout(
        inset.width,
        inset.height,
        inset.margin,
        marginTop(inset.height, inset.margin, ratio),
      )
  }

  private fun marginLeft(width: Int, margin: Int, ratio: Double): Int {
    return (width - ((width - margin * 2) * ratio).toInt()) / 2
  }

  private fun marginTop(height: Int, margin: Int, ratio: Double): Int {
    return (height - ((height - margin * 2) / ratio).toInt()) / 2
  }
}
