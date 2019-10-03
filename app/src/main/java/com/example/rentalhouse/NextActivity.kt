package com.example.rentalhouse

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.lang.StringBuilder

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
        var dataManagement = StringBuilder()
        if (thisMonthData.size == 6 && previousMonthData.size == 6) {
            for (i in 0 until thisMonthData.size) {
                dataManagement.append(showPaper(calculateRoomFees(previousMonthData[i], thisMonthData[i])))
            }
            Log.d("XXX", dataManagement.toString())
            tv!!.text = dataManagement.toString()
        }
    }

    fun calculateRoomFees(previousData: RoomModel, data: RoomModel): ManageModel {
        var usedElec = data.elec!! - previousData.elec!!
        var usedWater = data.water!! - previousData.water!!
        var extraFee = if (data.id == "p2_2") kWifiFee else 0

        var summary = kRoomFees[data.id]!!.toInt() + usedElec * kElecFee + usedWater * kWaterFee + kCleaningFee + extraFee

        return ManageModel(
            data.id,
            usedElec,
            usedWater,
            kRoomFees[data.id],
            kCleaningFee,
            extraFee,
            summary,
            previousData,
            data
        )
    }

    fun showPaper(manageData: ManageModel): String {
        val converter = RoomConverter()

        var builder = """
                      Phiếu thu tiền ${converter.roomIdToName(manageData.id!!)}
                            ---------- @@ ----------
            
                        - Tiền phòng: ${manageData.roomFee}
                        - Điện:
                            + Tháng này: ${manageData.presentata!!.elec}
                            + Tháng trước: ${manageData.previousData!!.elec}
                            + Tiêu thụ: ${manageData.usedElec} x ${kElecFee} = ${manageData.usedElec!! * kElecFee}
            
                        - Nước:
                            + Tháng này: ${manageData.presentata!!.water}
                            + Tháng trước: ${manageData.previousData!!.water}
                            + Tiêu thụ: ${manageData.usedWater} x ${kWaterFee} = ${manageData.usedWater!! * kWaterFee}
                        ${if(manageData.extraFee != 0) "\n                        - Tiền Wifi = ${kWifiFee}" else ""}
                        - Tiền rác: ${kCleaningFee}
                        - TỔNG CỘNG: ${manageData.summary}
                    (Ghi chú: điện: ${kElecFee}/kW, nước: ${kWaterFee}/khối)
            """
        return builder
    }
}

