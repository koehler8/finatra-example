package com.domain.system.views

import com.twitter.finatra._

class ContentView(values: Map[String, String]) extends View {
  val template = "templates/content.mustache"

  def content: Map[String, String] = {
    values
  }

}
