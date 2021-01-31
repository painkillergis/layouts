package com.painkillergis.layouts.print_layout

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.printLayoutController(printLayoutService: PrintLayoutService) {
  routing {
    post("/print-layout") {
      call.respond(
        printLayoutService.answer(
          call.receive(),
        )
      )
    }
  }
}