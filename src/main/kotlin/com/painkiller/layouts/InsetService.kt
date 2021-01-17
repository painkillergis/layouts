package com.painkiller.layouts

class InsetService {
  fun getLayout(inset: Inset): Layout {
    return if (inset.sourceWidth < inset.sourceHeight)
      Layout(
        inset.width,
        inset.height,
        marginLeft(inset),
        inset.margin,
      )
    else
      Layout(
        inset.width,
        inset.height,
        inset.margin,
        marginTop(inset),
      )
  }

  private fun marginLeft(inset: Inset): Int {
    val innerWidth = inset.width - inset.margin * 2
    return (inset.width - innerWidth * inset.sourceWidth / inset.sourceHeight) / 2
  }

  private fun marginTop(inset: Inset): Int {
    val innerHeight = inset.height - inset.margin * 2
    return (inset.height - innerHeight * inset.sourceHeight / inset.sourceWidth) / 2
  }
}
