package com.scoresaver.app.util.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun getCurrentDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    return LocalDate.now().format(formatter)
}
