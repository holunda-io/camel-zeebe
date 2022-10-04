package io.holunda.zeebe.camel._example

import io.holunda.zeebe.camel.component.DynamicRouteEndpoint
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import mu.KLogging
import org.apache.camel.CamelContext
import org.apache.camel.FluentProducerTemplate
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.engine.DefaultFluentProducerTemplate
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
class SimulatingConnectorController(
  val camel: CamelContext
) {
  companion object : KLogging() {

  }

  @PostMapping
  fun connect(@RequestParam endPoint: String, @RequestBody body: Any) {
    camel.createFluentProducerTemplate()
      .withBody(body)
      .to("direct:dynamic")
      .request()
  }
}

@Component
class DynamicRoute : RouteBuilder() {
  @Throws(Exception::class)
  override fun configure() {
    from("direct:dynamic")
      .log("inside")
      .to("log:info")

  }
}

