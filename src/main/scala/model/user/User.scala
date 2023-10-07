package model.user

import java.time.LocalDateTime

/**
 * @author Chenyu Liu
 * @since 10/7/23 Saturday
**/
    
case class User(
    userId:String,
    userName:String,
    age:Int,
    createDate:LocalDateTime
               )
