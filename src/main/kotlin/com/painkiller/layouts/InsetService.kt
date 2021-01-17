package com.painkiller.layouts

class InsetService {
  fun getLayout(inset: Inset): Layout {
    return Layout(
      inset.width,
      inset.height,
      (inset.width - inset.insetWidth) / 2,
      (inset.height - inset.insetHeight) / 2,
    )
  }
}
