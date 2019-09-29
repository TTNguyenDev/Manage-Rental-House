package com.example.rentalhouse

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NextActivity: AppCompatActivity() {
    private var tv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.next_activity)

        tv = findViewById(R.id.tv) as TextView

        for (i in 0 until EditAdapter.editModelArrayList.size) {
            tv!!.text =
                tv!!.text.toString() + " " + EditAdapter.editModelArrayList.get(i).getElecValue() + System.getProperty(
                    "line.separator")
        }
    }
}