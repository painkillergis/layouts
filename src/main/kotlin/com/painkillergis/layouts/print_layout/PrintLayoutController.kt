package com.painkillergis.layouts.print_layout

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.printLayoutController() {
  routing {
    post("/print-layout") {
      call.respond(call.receive<PrintLayoutQuestion>().printOption)
    }
  }
}