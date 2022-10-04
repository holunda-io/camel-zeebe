package io.holunda.zeebe.camel.connector

import io.camunda.connector.api.annotation.OutboundConnector
import io.camunda.connector.api.outbound.OutboundConnectorContext
import io.camunda.connector.api.outbound.OutboundConnectorFunction
import mu.KLogging


@OutboundConnector(
  name = "Camel Connector",
  inputVariables = ["endpoint"],
  type = "io.holunda:camel:1"
)
class CamelConnector : OutboundConnectorFunction {

  companion object: KLogging()

  override fun execute(context: OutboundConnectorContext): Any? {

    logger.info { "Connector is called." }
    val connectorRequest = context.getVariablesAsType(CamelRequest::class.java)

    context.validate(connectorRequest)
    context.replaceSecrets(connectorRequest)


    return null
  }
}
