package com.example.rentalhouse

import java.math.BigInteger

data class ManageModel(
    var id: String? = null,
    var elecFee: Int? = null,
    var waterFee: Int? = null,
    var roomFee: Int? = null,
    var cleaningFee: Int? = null,
    var extraFee:Int? = null,
    var summary: BigInteger? = null
)