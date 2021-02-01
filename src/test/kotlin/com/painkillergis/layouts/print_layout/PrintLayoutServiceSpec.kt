package com.painkillergis.layouts.print_layout

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

internal class PrintLayoutServiceSpec : StringSpec({
  "calculate margins when print option is taller than scaled source" {
    table(
      headers("question", "answer"),
      row(
        PrintLayoutQuestion(
          printOption = Rectangle(100, 200),
          source = Rectangle(200, 100),
          0,
        ),
        PrintLayout(
          size = Rectangle(100, 200),
          innerSize = Rectangle(100, 50),
          margin = Rectangle(0, 75),
        ),
      ),
      row(
        PrintLayoutQuestion(
          printOption = Rectangle(100, 200),
          source = Rectangle(100, 150),
          0,
        ),
        PrintLayout(
          size = Rectangle(100, 200),
          innerSize = Rectangle(100, 150),
          margin = Rectangle(0, 25),
        ),
      ),
      row(
        PrintLayoutQuestion(
          printOption = Rectangle(30, 40),
          source = Rectangle(20, 40),
          10,
        ),
        PrintLayout(
          size = Rectangle(30, 40),
          innerSize = Rectangle(10, 20),
          margin = Rectangle(10, 10),
        ),
      ),
    ).forAll { question, answer ->
      PrintLayoutService().answer(question) shouldBe answer
    }
  }

  "calculate margins when print option is same as scaled source" {
    table(
      headers("question", "answer"),
      row(
        PrintLayoutQuestion(
          printOption = Rectangle(100, 100),
          source = Rectangle(200, 200),
          0,
        ),
        PrintLayout(
          size = Rectangle(100, 100),
          innerSize = Rectangle(100, 100),
          margin = Rectangle(0, 0),
        ),
      ),
      row(
        PrintLayoutQuestion(
          printOption = Rectangle(120, 120),
          source = Rectangle(200, 200),
          10,
        ),
        PrintLayout(
          size = Rectangle(120, 120),
          innerSize = Rectangle(100, 100),
          margin = Rectangle(10, 10),
        ),
      ),
    ).forAll { question, answer ->
      PrintLayoutService().answer(question) shouldBe answer
    }
  }

  "calculate margins when print option is wider than scaled source" {
    table(
      headers("question", "answer"),
      row(
        PrintLayoutQuestion(
          printOption = Rectangle(200, 100),
          source = Rectangle(100, 200),
          margin = 0,
        ),
        PrintLayout(
          size = Rectangle(200, 100),
          innerSize = Rectangle(50, 100),
          margin = Rectangle(75, 0),
        ),
      ),
      row(
        PrintLayoutQuestion(
          printOption = Rectangle(200, 100),
          source = Rectangle(150, 100),
          margin = 0,
        ),
        PrintLayout(
          size = Rectangle(200, 100),
          innerSize = Rectangle(150, 100),
          margin = Rectangle(25, 0),
        ),
      ),
      row(
        PrintLayoutQuestion(
          printOption = Rectangle(40, 30),
          source = Rectangle(40, 20),
          10,
        ),
        PrintLayout(
          size = Rectangle(40, 30),
          innerSize = Rectangle(20, 10),
          margin = Rectangle(10, 10),
        ),
      ),
    ).forAll { question, answer ->
      PrintLayoutService().answer(question) shouldBe answer
    }
  }
})