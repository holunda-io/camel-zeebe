package io.holunda.zeebe.camel.connector

import io.camunda.zeebe.client.api.response.ActivatedJob
import io.camunda.zeebe.client.api.worker.JobClient
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker
import io.holunda.zeebe.camel.component.DynamicCamelTaskWorker
import mu.KLogging

class CamelWorker(
  val sendToCamel: DynamicCamelTaskWorker
) {
  companion object : KLogging()

  init {
    logger.info { "[CAMEL WORKER]: Initialized." }
  }

  @ZeebeWorker(type = "io.holunda:camel:1", autoComplete = true, forceFetchAllVariables = true)
  fun camel(client: JobClient, job: ActivatedJob, @ZeebeVariable endpoint: String) {

    val variables = job.variablesAsMap
    val payload = variables.minus("endpoint")

    logger.info { "Calling camel client for endpoint '$endpoint'" }
    logger.trace { "Endpoint: $endpoint, Payload: $payload" }

    sendToCamel(endpoint, payload)
  }
}
