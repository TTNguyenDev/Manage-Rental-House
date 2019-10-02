package com.example.rentalhouse

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class RoomModel (
    var id: String? = "",
    var date: String? = "",
    var water: Int? = 0,
    var elec: Int? = 0,
    var state: Boolean? = false
)