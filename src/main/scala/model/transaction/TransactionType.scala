package model.transaction

/**
 * @author
 *   Chenyu Liu
 * @since 10/7/23
 *   Saturday
 */

trait TransactionType {
  def name: String
}
case object Deposit extends TransactionType {
  def name = "Deposit"
}
case object Withdrawal extends TransactionType {
  def name = "Withdrawal"
}
