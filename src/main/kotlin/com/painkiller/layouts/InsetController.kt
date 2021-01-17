package com.painkiller.layouts

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

class InsetController(val insetService: InsetService) {
  fun Routing.routes() {
    post("/inset") {
      val inset = call.receive<Inset>()
      val layout = insetService.getLayout(inset)
      call.respond(layout)
    }
  }
}