package io.holunda.zeebe.camel.connector

import io.camunda.zeebe.client.ZeebeClient
import io.camunda.zeebe.spring.client.EnableZeebeClient
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment
import mu.KLogging
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import java.time.Instant
import java.util.*

@Import(value = [CamelWorker::class])
@EnableZeebeClient
@EnableScheduling
// @ZeebeDeployment(resources = ["classpath:demoProcess.bpmn"])
class CamelConfiguration(
  private val zeebeClient: ZeebeClient
) {

  companion object: KLogging()

  @Scheduled(initialDelayString = "PT5S", fixedDelay = Long.MAX_VALUE)
  fun on() {

    logger.warn { "Starting a process!" }

    zeebeClient
      .newCreateInstanceCommand()
      .bpmnProcessId("Process_CamelConnectorDemo")
      .latestVersion()
      .variables(
        mapOf(
          "order" to UUID.randomUUID().toString(),
          "created" to Instant.now().toString(),
        )
      ).send()
  }
}

