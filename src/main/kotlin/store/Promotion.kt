package store

import java.util.Date

data class Promotion(
    val name:String,
    val buy:Int,
    val get:Int,
    val start_date: Date,
    val end_date: Date
)
