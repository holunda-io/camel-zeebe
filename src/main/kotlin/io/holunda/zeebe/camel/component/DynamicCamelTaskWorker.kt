package io.holunda.zeebe.camel.component

import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

@Component
class DynamicCamelTaskWorker(
  camelContext: CamelContext
): (String, Map<String,Any>) -> Unit, RouteBuilder(camelContext) {
  companion object {
    const val START = "direct:dynamicCamelTaskWorker"
    const val KEY_ENDPOINT = "zeebeEndpoint"
  }

  override fun invoke(endPoint: String, body: Map<String, Any>) {
    super.getCamelContext().createFluentProducerTemplate()
      .withHeader(KEY_ENDPOINT, endPoint)
      .withBody(body)
      .to(START)
      .request()
  }

  override fun configure() {
    from(START)
      .toD("\${header.$KEY_ENDPOINT}")
  }
}
