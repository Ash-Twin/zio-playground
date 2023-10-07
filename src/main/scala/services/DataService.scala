package services

import io.getquill.{Literal, SnakeCase}
import io.getquill.jdbczio.Quill

/**
 * @author
 *   Chenyu Liu
 * @since 10/7/23
 *   Saturday
 */

object DataService {
  lazy val dataSourceLive = Quill.DataSource.fromPrefix("pg")
  lazy val postgresLive = Quill.Postgres.fromNamingStrategy(SnakeCase)
}
