package com.example.rentalhouse

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoomCellAdapter(context: Context, roomModelArrayLists: ArrayList<RoomModel>): RecyclerView.Adapter<RoomCellAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        roomModelArrayList = roomModelArrayLists
    }

    companion object {
        lateinit var roomModelArrayList: ArrayList<RoomModel>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.room_cell, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.elecEditText.setText(roomModelArrayList[position].elec.toString())
        holder.waterEditText.setText(roomModelArrayList[position].water.toString())
        holder.roomID.setText(roomModelArrayList[position].id.toString())
    }

    override fun getItemCount(): Int {
        return roomModelArrayList.size
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var elecEditText: EditText
        var waterEditText: EditText
        var roomID: TextView

        init {
            roomID = itemView.findViewById(R.id.roomID) as TextView
            roomID.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    roomModelArrayList[adapterPosition].id = roomID.text.toString()
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

            elecEditText = itemView.findViewById(R.id.elecId) as EditText
            elecEditText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    roomModelArrayList[adapterPosition].elec =  elecEditText.text.toString().toIntOrNull()
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

            waterEditText = itemView.findViewById(R.id.waterId) as EditText
            waterEditText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    roomModelArrayList[adapterPosition].water = waterEditText.text.toString().toIntOrNull()
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
        }
    }
}