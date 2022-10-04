package io.holunda.zeebe.camel.connector

import io.camunda.zeebe.client.api.response.ActivatedJob
import io.camunda.zeebe.client.api.worker.JobClient
import io.camunda.zeebe.spring.client.EnableZeebeClient
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker
import mu.KLogging
import org.springframework.stereotype.Component

@EnableZeebeClient
class CamelWorker {
  companion object : KLogging()

  init {
    logger.warn { "initialized worker" }
  }

  @ZeebeWorker(type = "io.holunda:camel:1", autoComplete = true)
  fun camel(client: JobClient, job: ActivatedJob, @ZeebeVariable endpoint: String) {
    logger.info { "Calling camel client for endpoint '$endpoint'" }

    val variables = job.variablesAsMap
    logger.info { "Endpoint: $endpoint Variables: $variables" }


  }
}
