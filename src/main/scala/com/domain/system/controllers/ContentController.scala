package com.domain.system.controllers

import com.domain.system.mappers.ContentMapper
import com.domain.system.views.ContentView
import com.fasterxml.jackson.databind.ObjectMapper
import com.twitter.finagle.{Http, Service}
import com.twitter.finatra.{Controller, ResponseBuilder}
import com.twitter.io.Charsets
import com.twitter.util.{Future, Promise}
import org.jboss.netty.handler.codec.http._

class ContentController(mapper: ObjectMapper) extends Controller {

  get("/") { request =>

    // INITIALIZE PROMISE
    val renderPromise = new Promise[ResponseBuilder]

    // MAKE CALL TO CONTENT API
    val client: Service[HttpRequest, HttpResponse] = Http.newService("localhost:8080")
    val request = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/api")
    val response: Future[HttpResponse] = client(request)

    // HANDLE RESPONSE CALLBACKS
    response onSuccess { resp: HttpResponse =>

      // PARSE JSON FROM CONTENT SERVICE
      val contentJson = resp.getContent.toString(Charsets.Utf8)

      val jsonContent = mapper.readValue(contentJson, classOf[ContentMapper])
      val content = Map(
        "id" -> jsonContent.id,
        "title" -> jsonContent.title,
        "copy" -> jsonContent.copy,
        "byline" -> jsonContent.byline
      )

      // SEND BACK VIEW AS PROMISED
      renderPromise.setValue(render.view(new ContentView(content)))
    }

    // RETURN FUTURE RENDERED VIEW
    renderPromise
  }

}