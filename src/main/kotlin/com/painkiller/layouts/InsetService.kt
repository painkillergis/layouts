package com.painkiller.layouts

class InsetService {
  fun getLayout(inset: Inset): Layout {
    return Layout(
      inset.width,
      inset.height,
      (inset.width - inset.sourceWidth) / 2,
      (inset.height - inset.sourceHeight) / 2,
    )
  }
}
