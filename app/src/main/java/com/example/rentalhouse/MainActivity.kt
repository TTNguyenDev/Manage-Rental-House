package com.example.rentalhouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    @IgnoreExtraProperties
    data class Room (
        var id: String? = "",
        var date: String? = "",
        var elec: Int? = 0,
        var water: Int? = 0,
        var state: Boolean? = false
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance().reference



    }

    private fun readInputFromUser() {

    }

    private fun writeRoomData(id: String, date: String, elec: Int, water:Int, state:Boolean) {
        val room = Room(id, date, elec, water, state)
        database.child("UserData").child(date).setValue(room)

    }
}
