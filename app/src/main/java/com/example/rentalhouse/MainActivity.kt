package com.example.rentalhouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties

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

    private var saveBtn: Button? =null
    private var recyclerView: RecyclerView? = null
    private var customAdapter: RoomCellAdapter? = null
    lateinit var roomModelArrayList: ArrayList<RoomModel>

    private var roomIDs = arrayOf<String>("p1_1", "p3_1", "p1_2", "p2_2", "p3_2", "p4_2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler) as RecyclerView
        saveBtn = findViewById(R.id.saveBtn) as Button

        roomModelArrayList = populateList()
        customAdapter = RoomCellAdapter(this, roomModelArrayList)
        recyclerView!!.adapter = customAdapter
        recyclerView!!.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        saveBtn!!.setOnClickListener {

            val helper = Helper()
            val currentDate = helper.getCurrentDate()
            val previousMonth = helper.getPreviousMonth()

            Toast.makeText(this, currentDate, Toast.LENGTH_SHORT).show()

            for (editModel in roomModelArrayList) {
                writeRoomData(editModel.getID()!!, currentDate, editModel.getElecValue()!!.toInt(), editModel.getWaterValue()!!.toInt(), false)
            }
        }
    }

    private fun populateList(): ArrayList<RoomModel> {
        var list = ArrayList<RoomModel>()

        roomIDs.forEach {
            val editModel = RoomModel()
            editModel.setID(it)
            editModel.setElecValue(0.toString())
            editModel.setWaterValue(0.toString())
            list.add(editModel)
        }

        return list
    }

    private fun writeRoomData(id: String, date: String, elec: Int, water:Int, state:Boolean) {
        database = FirebaseDatabase.getInstance().reference
        val room = Room(id, date, elec, water, state)
        database.child("UserData").child(date).child(id).setValue(room)

    }
}
