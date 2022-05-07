package com.beroozresaan.android.util.parser

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 *
 * @author Abbas Asadi
 */
class StringParser {
    companion object {
        /**
         * this method separate price
         * example: 42050 --> 42,050
         */
        @JvmStatic
        fun numberSeparator(s: String): String {
            try {
                var originalString = s
                val value: Double
                originalString = convertToJustNumber(originalString)
                value = originalString.toDouble()
                val formatter: DecimalFormat = NumberFormat.getInstance(Locale.US) as DecimalFormat
                formatter.applyPattern("#,###,###,###")
                //setting text after format to EditText

                return formatter.format(value)
            } catch (nfe: NumberFormatException) {
                nfe.printStackTrace()
            }
            return s
        }

        /**
         * this method clear price from comma
         * example: 42,050 --> 42050
         */
        @JvmStatic
        fun convertToJustNumber(originalString: String): String {
            var originalString1 = originalString
            if (originalString1.contains(",")) {
                originalString1 = originalString1.replace(",".toRegex(), "")
            }
            return originalString1
        }

        @JvmStatic
        fun removeZeros(d: Double): String {
            return if (d == d.toLong().toDouble()) String.format(
                "%d",
                d.toLong()
            ) else java.lang.String.format("%s", d)
        }

        /**
         * this method separate bank card number
         * example: 6037997214206543 --> 6037-9972-1420-6543
         */
        @JvmStatic
        fun bankCardSeparator(s: String): String {
            try {
                var originalString = s
                val value: Double
                originalString = bankCardConvertToJustNumber(originalString)
                value = originalString.toDouble()
                val formatter: DecimalFormat = NumberFormat.getInstance() as DecimalFormat
                formatter.applyPattern("####,####")
                //setting text after format to EditText

                return formatter.format(value).replace(",", "-")
            } catch (nfe: NumberFormatException) {
                nfe.printStackTrace()
            }
            return s
        }

        /**
         * this method clear bank card number from dash
         * example: 42,050 --> 42050
         */
        @JvmStatic
        fun bankCardConvertToJustNumber(originalString: String): String {
            var originalString1 = originalString
            if (originalString1.contains("-")) {
                originalString1 = originalString1.replace("-".toRegex(), "")
            }
            return originalString1
        }


    }
}