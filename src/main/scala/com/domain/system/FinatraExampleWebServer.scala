package com.domain.system

import com.domain.system.controllers._
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.twitter.finatra._

object FinatraExampleWebServer extends FinatraServer {
  System.setProperty("com.twitter.finatra.config.port", ":8080")

  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  register(new ApiController())
  register(new ContentController(mapper))
}
