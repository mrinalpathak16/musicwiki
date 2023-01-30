package com.pathakbau.musicwiki.util

import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

fun formatCount(number: Number): String {
    val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P');
    val numValue = number.toLong()
    val value = floor(log10(numValue.toDouble())).toInt()
    val base = value/3
    return if (value >= 3 && base < suffix.size) {
        DecimalFormat("#0.0").format(numValue/(10).toDouble().pow(base*3)) +
                suffix[base]
    } else {
        DecimalFormat("#,##0").format(numValue)
    }
}