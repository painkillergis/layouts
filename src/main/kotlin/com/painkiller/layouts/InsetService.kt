package com.painkiller.layouts

class InsetService {
  fun getLayout(inset: Inset): Layout {
    val ratio = inset.sourceWidth.toDouble() / inset.sourceHeight
    return if (inset.sourceWidth < inset.sourceHeight)
      Layout(
        inset.width,
        inset.height,
        (inset.width - ((inset.width - inset.margin * 2) * ratio).toInt()) / 2,
        inset.margin,
      )
    else
      Layout(
        inset.width,
        inset.height,
        inset.margin,
        (inset.height - ((inset.height - inset.margin * 2) / ratio).toInt()) / 2,
      )
  }

}
