package io.holunda.zeebe.camel.connector

import org.springframework.context.annotation.Import

@Import(value = [CamelWorker::class])
class CamelConfiguration

