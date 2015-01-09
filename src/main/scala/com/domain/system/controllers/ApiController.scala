package com.domain.system.controllers

import com.twitter.finatra.Controller

class ApiController extends Controller {

  get("/api") { request =>

    val content = Map(
      "id" -> "123",
      "title" -> "Charlie Sheen continues his epic rant against Kim Kardashian... then apologizes",
      "copy" -> "Dear Kim, my extreme bad. really embarrassed by my actions. I was already pissed about some other crap that had nothing to do with you. I heard a story that bothered me. wrote some trash you didn't deserve. Ever. I'm an idiot as often as I'm a genius. that day, clearly I was the former. xox #ShutUpSheen",
      "byline" -> "TMZ"
    )

    render.json(content).toFuture

  }

}