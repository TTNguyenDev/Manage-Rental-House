package com.example.rentalhouse

public class EditModel {
    private var elecEditTextValue: String? = null
    private var watarEditTextValue: String? = null

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