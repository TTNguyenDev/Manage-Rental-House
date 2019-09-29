package com.example.rentalhouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

//    private lateinit var database: DatabaseReference
//
//    @IgnoreExtraProperties
//    data class Room (
//        var id: String? = "",
//        var date: String? = "",
//        var elec: Int? = 0,
//        var water: Int? = 0,
//        var state: Boolean? = false
//    )

    private var btn: Button? =null
    private var recyclerView: RecyclerView? = null
    private var customAdapter: EditAdapter? = null
    lateinit var editModelArrayList: ArrayList<EditModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler) as RecyclerView
        btn = findViewById(R.id.btn) as Button

        editModelArrayList = populateList()
        customAdapter = EditAdapter(this, editModelArrayList)
        recyclerView!!.adapter = customAdapter
        recyclerView!!.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        btn!!.setOnClickListener {
            val intent = Intent(this@MainActivity, NextActivity::class.java)
            startActivity(intent)
        }
    }

    private fun populateList(): ArrayList<EditModel> {
        var list = ArrayList<EditModel>()

        for (i in 0..7) {
            val editModel = EditModel()
            editModel.setElecValue(i.toString())
            editModel.setWaterValue(i.toString())
            list.add(editModel)
        }
        return list
    }

    private fun readInputFromUser() {

    }

//    private fun writeRoomData(id: String, date: String, elec: Int, water:Int, state:Boolean) {
//        database = FirebaseDatabase.getInstance().reference
//        val room = Room(id, date, elec, water, state)
//        database.child("UserData").child(date).setValue(room)
//
//    }
}
