package com.maetzedev.shop_kotlin.utils

import java.text.NumberFormat
import java.util.*


class Formatters {

    class CurrencyFormatter {

        fun formatFloatToString(double: Double): String {
            val formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY)
            formatter.maximumFractionDigits = 2
            formatter. minimumFractionDigits = 2
            return formatter.format(double)
        }

    }

}