package com.example.quickstart

import weaver._

object JokesSpec extends SimpleIOSuite {
  pureTest("Simple expectations (success)") {

    expect((2) == 2)
  }
//
//  test("Jokes returns status code 200") {
//    for {
//      response <- retJokes
//    } yield expect(response.status == Status.Ok)
//  }
//
//  test("Jokes returns a joke") {
//    for {
//      response <- retJokes
//      stringResponse <- response.as[String]
//    } yield expect(stringResponse == "{\"message\":\"Hello, world\"}")
//  }
//
//  private[this] val retJokes: IO[Response[IO]] = {
//    def routes(makeResponse: IO[Response[IO]]) = {
//      HttpRoutes.of[IO] {
//        case GET -> Root / "joke" =>
//          makeResponse
//      }.orNotFound
//    }
//
//
//    val client = Client.fromHttpApp(routes(Ok("This is a joke")));
//    val getHW = Request[IO](Method.GET, uri"/joke")
//    val joke = Jokes.impl(client)
//    QuickstartRoutes.jokeRoutes(joke).orNotFound(getHW)
//  }
}
