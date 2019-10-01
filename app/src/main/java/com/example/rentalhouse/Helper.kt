package com.example.rentalhouse

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Helper {
     fun getCurrentDate(): String {
        val currentDate = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("MM_yyyy")
        val formattedDate = currentDate.format(formatter)

         return formattedDate.toString()
    }

    fun getPreviousMonth(): String {
        val currentDate = LocalDateTime.now()
        val previousMonth = currentDate.minusMonths(1)

        val formatter = DateTimeFormatter.ofPattern("MM_yyyy")
        val formattedDate = previousMonth.format(formatter)

        return formattedDate.toString()
    }
}