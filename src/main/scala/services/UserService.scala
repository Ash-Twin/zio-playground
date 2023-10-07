package services

import io.getquill.SnakeCase
import io.getquill.jdbczio.Quill
import model.user.{BankUser}
import zio.{ZIO, ZLayer}

import java.sql.SQLException

/**
 * @author
 *   Chenyu Liu
 * @since 10/7/23
 *   Saturday
 */

class UserService(quill: Quill.Postgres[SnakeCase]) {
  import quill._

  private val userSchema = quote{querySchema[BankUser]("bank_user")}

  private def getUsers: ZIO[Any, SQLException, List[BankUser]] = run(userSchema)

  private def getUser(userId: Option[String], userName: Option[String]) = {
    (userId, userName) match {
      case (Some(id), _) =>
        run(userSchema.filter(_.userId == lift(id)))
      case (_, Some(name)) =>
        run(userSchema.filter(_.userName == lift(name)))
      case _ =>
        ZIO.fail(new SQLException("userId and userName both empty"))
    }
  }

  private def createUser(user: BankUser) =
    run(userSchema.insertValue(lift(user)))

}

object UserService {
  def getUsers: ZIO[UserService, SQLException, List[BankUser]] =
    ZIO.serviceWithZIO[UserService](_.getUsers)

  def getUser(userId: Option[String] = None, userName: Option[String] = None) =
    ZIO.serviceWithZIO[UserService](_.getUser(userId, userName))

  def createUser(user: BankUser) = ZIO.serviceWithZIO[UserService](_.createUser(user))

  val live = ZLayer.derive[UserService]
}
