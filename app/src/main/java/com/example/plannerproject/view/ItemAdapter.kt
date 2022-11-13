package com.example.plannerproject.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plannerproject.R
import com.example.plannerproject.data.CardData

class ItemAdapter(val c: Context?, val cardList:List<CardData>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()
{

    inner class ItemViewHolder(val v: View):RecyclerView.ViewHolder(v) {
        val name = v.findViewById<TextView>(R.id.mTitle)
        val mbNum = v.findViewById<TextView>(R.id.mSubTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(v)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val newList = cardList[position]
        val iconGreen ="\uD83D\uDFE9 "
        holder.name.text = iconGreen+" "+newList.task
        holder.mbNum.text = newList.aboutTask
    }

    override fun getItemCount(): Int {
        return cardList.size
    }


}