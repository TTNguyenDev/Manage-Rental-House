package com.example.rentalhouse

public class RoomModel {
    private var id: String? = null
    private var elecEditTextValue: String? = null
    private var watarEditTextValue: String? = null

    fun getID(): String? {
        return id
    }

    fun setID(value: String) {
        this.id = value
    }

    fun getElecValue(): String? {
       return elecEditTextValue
    }

    fun setElecValue(value: String) {
        this.elecEditTextValue = value
    }

    fun getWaterValue(): String? {
        return watarEditTextValue
    }

    fun setWaterValue(value: String) {
        this.watarEditTextValue = value
    }


}