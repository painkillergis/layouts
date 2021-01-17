package com.painkiller.layouts

class InsetService {
  fun getLayout(inset: Inset): Layout {
    return Layout(
      inset.width,
      inset.height,
      inset.insetWidth,
      (inset.height - inset.insetHeight) / 2,
    )
  }
}
