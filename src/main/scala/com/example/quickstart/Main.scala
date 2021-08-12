package com.example.quickstart

import cats.effect.{ExitCode, IOApp}

object Main extends IOApp {
  def run(args: List[String]) =
    QuickstartServer.stream.compile.drain.as(ExitCode.Success)
}
