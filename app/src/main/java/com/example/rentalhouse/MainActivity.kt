package com.example.rentalhouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    private var saveBtn: Button? = null
    private var manageBtn: Button? = null
    private var recyclerView: RecyclerView? = null
    private var roomCellAdapter: RoomCellAdapter? = null
    lateinit var roomModelArrayList: ArrayList<RoomModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler) as RecyclerView
        saveBtn = findViewById(R.id.saveBtn) as Button
        manageBtn = findViewById(R.id.manageBtn) as Button

        roomModelArrayList = populateList()
        roomCellAdapter = RoomCellAdapter(this, roomModelArrayList)
        recyclerView!!.adapter = roomCellAdapter
        recyclerView!!.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        saveBtn!!.setOnClickListener {

            val helper = DateTimeHelper()
            val currentDate = helper.getCurrentDate()
            //val previousMonth = helper.getPreviousMonth()

            Toast.makeText(this, currentDate, Toast.LENGTH_SHORT).show()

            for (roomModel in roomModelArrayList) {
                writeRoomData(roomModel.id!!, currentDate, roomModel.elec!!.toInt(), roomModel.water!!.toInt(), false)
            }
        }

        manageBtn!!.setOnClickListener {
            val nextActivityIntent = Intent(this, NextActivity::class.java)
            startActivity(nextActivityIntent)

        }
    }

    private fun populateList(): ArrayList<RoomModel> {
        var list = ArrayList<RoomModel>()

        kRoomIDs.forEach {
            val roomModel = RoomModel()
            roomModel.id = it
            roomModel.elec = 0
            roomModel.water = 0
            list.add(roomModel)
        }

        return list
    }

    private fun writeRoomData(id: String, date: String, elec: Int, water:Int, state:Boolean) {
        database = FirebaseDatabase.getInstance().reference.child("UserData").child(date).child(id)
        val room = RoomModel(id, date, elec, water, state)
        database.setValue(room)

    }
}
