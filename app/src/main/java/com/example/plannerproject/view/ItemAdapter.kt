package com.example.plannerproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.plannerproject.database.CardEntity
import androidx.recyclerview.widget.ListAdapter
import com.example.plannerproject.R
import com.example.plannerproject.databinding.ListItemBinding

class ItemAdapter:ListAdapter<CardEntity,ItemAdapter.ViewHolder>(CardDiffCallback()){
     var data =  listOf<CardEntity>()
         set(value) {
             field = value
             notifyDataSetChanged()
         }

     class ViewHolder(private val binding:ListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(card:CardEntity)=with(binding){
            mTitle.text = card.task
            mSubTitle.text = card.aboutTask

            }
        companion object{
            fun create (parent:ViewGroup):ViewHolder{
                return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item,parent,false)
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = data.size

}
class CardDiffCallback: DiffUtil.ItemCallback<CardEntity>() {
    override fun areItemsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
        return oldItem.id ==newItem.id
    }

    override fun areContentsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
        return oldItem==newItem
    }
}