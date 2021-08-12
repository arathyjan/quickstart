package com.example.quickstart

import cats.effect.IO
import org.http4s._
import org.http4s.implicits._
import weaver._

object HelloWorldSpec extends SimpleIOSuite {

  test("HelloWorld returns status code 200") {
    for {
      response <- retHelloWorld
    } yield expect(response.status == Status.Ok)
  }

  test("HelloWorld returns hello world message") {
    for {
      response <- retHelloWorld
      stringResponse <- response.as[String]
    } yield expect(stringResponse == "{\"message\":\"Hello, world\"}")
  }

  private[this] val retHelloWorld: IO[Response[IO]] = {
    val getHW = Request[IO](Method.GET, uri"/hello/world")
    val helloWorld = HelloWorld.impl[IO]
    QuickstartRoutes.helloWorldRoutes(helloWorld).orNotFound(getHW)
  }
}
