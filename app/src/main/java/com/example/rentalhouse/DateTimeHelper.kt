package com.example.rentalhouse

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeHelper {
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

class RoomConverter {
    fun roomIdToName(id: String): String {
        when (id) {
            "p1_1" -> return "Phòng 1 tầng 1"
            "p3_1" -> return "Phòng 3 tầng 1"
            "p1_2" -> return "Phòng 1 tầng 2"
            "p2_2" -> return "Phòng 2 tầng 2"
            "p3_2" -> return "Phòng 3 tầng 2"
            "p4_2" -> return "Phòng 4 tầng 2"
        }
        return "Room's not exists"
    }
}