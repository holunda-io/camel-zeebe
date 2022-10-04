package io.holunda.zeebe.camel.connector

import io.camunda.connector.api.annotation.OutboundConnector
import io.camunda.connector.api.outbound.OutboundConnectorContext
import io.camunda.connector.api.outbound.OutboundConnectorFunction
import mu.KLogging
import org.springframework.stereotype.Component


// @OutboundConnector(
//  name = "Camel Connector",
//  inputVariables = ["endpoint"],
//  type = "io.holunda:camel:1"
// )
// @Component
class CamelConnector : OutboundConnectorFunction {

  companion object: KLogging()

  init {
    logger.warn { "initialized connector" }
  }

  override fun execute(context: OutboundConnectorContext): Any? {

    logger.warn { "Connector is called." }
    val connectorRequest = context.getVariablesAsType(CamelRequest::class.java)

    context.validate(connectorRequest)
    context.replaceSecrets(connectorRequest)


    return null
  }
}
