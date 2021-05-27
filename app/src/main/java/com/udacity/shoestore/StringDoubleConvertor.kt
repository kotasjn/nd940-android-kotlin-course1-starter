package com.udacity.shoestore

import androidx.databinding.InverseMethod

object StringDoubleConvertor {
    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(
        value: Double?
    ): String {
        // Converts Double to String.
        if (value == null) return ""
        return value.toString()
    }

    @JvmStatic
    fun stringToDouble(
        value: String
    ): Double? {
        // Converts String to Double.
        if (value.isEmpty()) return null
        return value.toDouble()
    }
}