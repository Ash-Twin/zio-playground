package model.user

import java.time.LocalDateTime

/**
 * @author
 *   Chenyu Liu
 * @since 10/7/23
 *   Saturday
 */

case class BankUser(
  userId: String,
  userName: String,
  age: Int,
  gender: String,
  createTime: LocalDateTime,
  lastLoginTime: LocalDateTime
)
