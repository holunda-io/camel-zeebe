package io.holunda.zeebe.camel.example

import io.holunda.zeebe.camel.component.DynamicCamelTaskWorker
import io.holunda.zeebe.camel.connector.CamelConfiguration
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import mu.KLogging
import org.apache.camel.builder.RouteBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


fun main() = runApplication<CamelConnectorExampleApplication>().let { }

@OpenAPIDefinition(
  info = Info(title = "camel connector example", description = "hackdays 2022", version = "1")
)
@SpringBootApplication
@Import(value = [CamelConfiguration::class, DynamicCamelTaskWorker::class])
class CamelConnectorExampleApplication {

}

@RestController
class SimulatingConnectorController(
  val sendToCamelTaskWorker: DynamicCamelTaskWorker
) {
  companion object : KLogging() {

  }

  @PostMapping
  fun connect(@RequestParam endPoint: String, @RequestBody body: Map<String, Any>) {
    sendToCamelTaskWorker(endPoint, body)
  }
}


