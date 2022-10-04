package io.holunda.zeebe.camel._example

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import mu.KLogging
import org.apache.camel.builder.RouteBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.NullPointerException


fun main() = runApplication<CamelConnectorExampleApplication>().let {  }

@OpenAPIDefinition(
  info = Info(title= "camel connector example", description = "hackdays 2022", version = "1")
)
@SpringBootApplication
class CamelConnectorExampleApplication {
}

@RestController
class SimulatingConnectorController {
  companion object : KLogging() {

  }

  @PostMapping
  fun connect(@RequestParam endPoint: String, @RequestBody body: Any) {
    logger.info {
      """

        connect:

        endPoint: $endPoint
        body: $body


      """.trimIndent()
    }
  }
}

@Component
class MyRoute : RouteBuilder() {
  @Throws(Exception::class)
  override fun configure() {
    from("timer:foo").to("log:bar")
  }
}

