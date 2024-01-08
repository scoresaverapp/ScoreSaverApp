package com.scoresaver.app.wear.model

internal sealed class ServiceOder(val number: String){
    data object First: ServiceOder("1")
    data object Second: ServiceOder("2")
    data object Third: ServiceOder("3")
    data object Fourth: ServiceOder("4")
}
