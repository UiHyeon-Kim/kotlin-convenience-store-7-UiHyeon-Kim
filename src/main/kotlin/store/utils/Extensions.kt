package store.utils

import java.text.DecimalFormat

fun Int.toWonFormat(): String {
    val wonFormat = DecimalFormat("#,###")
    return wonFormat.format(this)
}
