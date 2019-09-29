package com.example.rentalhouse

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class EditAdapter(context: Context, editModelArrayLists: ArrayList<EditModel>): RecyclerView.Adapter<EditAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        editModelArrayList = editModelArrayLists
    }

    companion object {
        lateinit var editModelArrayList: ArrayList<EditModel>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.room_cell, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.elecEditText.setText(editModelArrayList[position].getElecValue().toString())
        holder.waterEditText.setText(editModelArrayList[position].getWaterValue().toString())
    }

    override fun getItemCount(): Int {
        return editModelArrayList.size
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var elecEditText: EditText
        var waterEditText: EditText

        init {
            elecEditText = itemView.findViewById(R.id.elecId) as EditText
            elecEditText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    editModelArrayList[adapterPosition].setElecValue(elecEditText.text.toString())
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

            waterEditText = itemView.findViewById(R.id.waterId) as EditText
            waterEditText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    editModelArrayList[adapterPosition].setWaterValue(waterEditText.text.toString())
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
        }
    }
}