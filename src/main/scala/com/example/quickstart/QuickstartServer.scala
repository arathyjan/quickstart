package com.example.quickstart

import cats.effect.{ConcurrentEffect, IO, Timer}
import cats.implicits._
import fs2.Stream
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.Logger

import scala.concurrent.ExecutionContext.global

object QuickstartServer {

  implicit def ashfdkjsh: String = {
    if(true) "Z" else "B"
  }

  def helloWorld(implicit name: String) = {
    "hello " + name
  }
//  All 4 are equivalent
//  def stream[F[_]: ConcurrentEffect : Timer]: Stream[F, Nothing] = {
//  def stream[F[_]: ConcurrentEffect](implicit T: Timer[F]): Stream[F, Nothing] = {
//  def stream[F[_]](implicit T: Timer[F], ce: ConcurrentEffect[F]): Stream[F, Nothing] = {
//  def stream[F[_]: ConcurrentEffect](implicit T: Timer[F]): Stream[F, Nothing] = {
//  Replacing F with IO
  def stream(implicit T: Timer[IO], ce: ConcurrentEffect[IO]): Stream[IO, Nothing] = {

  for {
      client <- BlazeClientBuilder[IO](global).stream
      helloWorldAlg = HelloWorld.impl
      jokeAlg = Jokes.impl(client)

      // Combine Service Routes into an HttpApp.
      // Can also be done via a Router if you
      // want to extract a segments not checked
      // in the underlying routes.
      httpApp = (
        QuickstartRoutes.helloWorldRoutes(helloWorldAlg) <+>
        QuickstartRoutes.jokeRoutes(jokeAlg)
      ).orNotFound

      // With Middlewares in place
      finalHttpApp = Logger.httpApp(true, true)(httpApp)

      exitCode <- BlazeServerBuilder[IO](global)
        .bindHttp(8080, "0.0.0.0")
        .withHttpApp(finalHttpApp)
        .serve
    } yield exitCode
  }.drain
}
