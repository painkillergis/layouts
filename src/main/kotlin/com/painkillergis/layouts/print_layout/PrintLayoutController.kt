package com.painkillergis.layouts.print_layout

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.Logger
import java.lang.Exception

fun Application.printLayoutController(logger: Logger, printLayoutService: PrintLayoutService) {
  routing {
    post("/print-layout") {
      try {
        call.respond(
          printLayoutService.answer(
            call.receive(),
          )
        )
      } catch (error: Exception) {
        when (error) {
          is JsonParseException,
          is MissingKotlinParameterException,
          is UnrecognizedPropertyException ->
            call.respond(HttpStatusCode.BadRequest, "Malformed request body")
          else -> {
            logger.error("Failed to answer print layout question", error)
            call.respond(HttpStatusCode.InternalServerError)
          }
        }
      }
    }
  }
}