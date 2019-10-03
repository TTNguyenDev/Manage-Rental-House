package com.example.rentalhouse

import java.math.BigInteger

data class ManageModel(
    var id: String? = null,
    var usedElec: Int? = null,
    var usedWater: Int? = null,
    var roomFee: Int? = null,
    var cleaningFee: Int? = null,
    var extraFee:Int? = null,
    var summary: Int? = null,
    var previousData: RoomModel? = null,
    var presentata: RoomModel? = null
)