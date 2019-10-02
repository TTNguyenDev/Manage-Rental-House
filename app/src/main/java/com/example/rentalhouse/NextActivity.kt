package com.example.rentalhouse

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class NextActivity: AppCompatActivity() {
    private var tv: TextView? = null
    private lateinit var database: DatabaseReference

    private var thisMonthData = ArrayList<RoomModel>()
    private var previousMonthData = ArrayList<RoomModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.next_activity)
        setTitle("Manage Screen")
        tv = findViewById(R.id.tv) as TextView

        val dateTimeHelper = DateTimeHelper()
        retrievedDataWithMonth(dateTimeHelper.getCurrentDate(), thisMonthData)
        retrievedDataWithMonth(dateTimeHelper.getPreviousMonth(), previousMonthData)
    }

    fun retrievedDataWithMonth(date: String, roomModels: ArrayList<RoomModel>) {
        database = FirebaseDatabase.getInstance().reference.child("UserData").child(date)

        val postListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    var room = it.getValue(RoomModel::class.java)
                    roomModels.add(room!!)
                    Log.d("ROOMS", room.id)
                }
                fetchDataDidEnd()
            }

            override fun onCancelled(p0: DatabaseError) {}
        }

        database.addValueEventListener(postListener)
    }

    fun fetchDataDidEnd() {
        if (thisMonthData.size == 6 && previousMonthData.size == 6) {
            for (i in 0 until thisMonthData.size) {
                Log.d("FEES" , calculateRoomFees(previousMonthData[i], thisMonthData[i]).id)
                Log.d("FEES" , calculateRoomFees(previousMonthData[i], thisMonthData[i]).roomFee.toString())
            }
        }
    }

    fun calculateRoomFees(previousData: RoomModel, data: RoomModel): ManageModel {
        var usedElec = data.elec!! - previousData.elec!!
        var usedWater = data.water!! - previousData.water!!
        var extraFee = if (data.id == "p2_2") kWifiFee else 0

        return ManageModel(data.id, usedElec * kElecFee, usedWater * kWaterFee, kRoomFees[data.id], kCleaningFee, extraFee)
    }

    fun showPaper(manageData: ManageModel) {

    }
}

