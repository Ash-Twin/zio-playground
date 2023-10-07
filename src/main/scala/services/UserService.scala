package services

import io.getquill.SnakeCase
import io.getquill.jdbczio.Quill
import model.user.User
import zio.{ZIO, ZLayer}

import java.sql.SQLException

/**
 * @author Chenyu Liu
 * @since 10/7/23 Saturday
**/

class UserService(quill: Quill.Postgres[SnakeCase]) {
  import quill._

  private def getUsers: ZIO[Any, SQLException, List[User]] = run(query[User])

}

object UserService {

  def getUsers: ZIO[UserService, SQLException, List[User]] =
    ZIO.serviceWithZIO[UserService](_.getUsers)



  val live = ZLayer.derive[UserService]
}
