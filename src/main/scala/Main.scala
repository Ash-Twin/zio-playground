import model.user.BankUser
import services.{DataService, UserService}
import zio._
import zio.Console.printLine

import java.time.LocalDateTime
import java.util.UUID

object Main extends ZIOAppDefault {
  implicit val trace = Trace.empty
  override def run: ZIO[Environment with ZIOAppArgs with Scope, Any, Any] = {
    printLine("Welcome to your first ZIO app!")
    val dataSourceLive = DataService.dataSourceLive
    val pgLive = DataService.postgresLive
    val userServiceLive = UserService.live
    val user1 = {
      for {
        c1 <-
          Random.nextPrintableChar zip Random.nextPrintableChar zip Random.nextPrintableChar zip Random.nextPrintableChar
        num <- Random.nextInt
      } yield BankUser(
        UUID.randomUUID().toString,
        c1._1.toString + c1._2.toString + c1._3.toString + c1._4.toString,
        num,
        "Male",
        LocalDateTime.now(),
        LocalDateTime.now()
      )
    }
    val layers =
      ZLayer.make[UserService](userServiceLive, dataSourceLive, pgLive, ZLayer.Debug.mermaid)

    (for {
      user <- user1
      _ <- UserService.createUser(user)
      users <- UserService.getUsers
      _ <- Console.printLine(users.mkString("\n"))
    } yield ()).provide(layers)

  }
}
