package io.holunda.zeebe.camel.component

import org.apache.camel.*
import org.apache.camel.support.DefaultEndpoint
import org.apache.camel.support.DefaultProducer

class DynamicRouteEndpoint(
  camelContext: CamelContext,
  private val endpoint: String,
  private val body: Map<String,Any>
) : DefaultEndpoint() {

  init {
    setCamelContext(camelContext)
  }

  override fun createProducer(): Producer = object : DefaultProducer(this) {
    override fun process(exchange: Exchange) {
      exchange.`in`.body = body
      exchange.`in`.setHeader("endpoint", endpoint)
    }
  }

  override fun createConsumer(processor: Processor): Consumer? {
    return null
  }

  override fun createEndpointUri(): String = "dynamicZeebe"
}
