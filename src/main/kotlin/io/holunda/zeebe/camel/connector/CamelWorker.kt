package io.holunda.zeebe.camel.connector

import io.camunda.zeebe.client.api.response.ActivatedJob
import io.camunda.zeebe.client.api.worker.JobClient
import io.camunda.zeebe.spring.client.EnableZeebeClient
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker
import mu.KLogging

@EnableZeebeClient
class CamelWorker {

  companion object: KLogging()

  @ZeebeWorker(type = "io.holunda:camel:1")
  fun camel(client: JobClient, job: ActivatedJob) {
    logger.info { "Calling camel client" }

    val variables = job.variablesAsMap
    logger.trace { "Variables: $variables" }
    client.newCompleteCommand(job.key)
      .send()
      .exceptionally { throwable -> throw RuntimeException("Could not complete job $job", throwable) }
  }
}
