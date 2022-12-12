package com.example.plannerproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.plannerproject.database.CardEntity
import androidx.recyclerview.widget.ListAdapter
import com.example.plannerproject.R
import com.example.plannerproject.databinding.ListItemBinding
import kotlinx.android.synthetic.main.list_item.view.*

class ItemAdapter:ListAdapter<CardEntity,ItemAdapter.ViewHolder>(CardDiffCallback()){
    var onItemClick : ((CardEntity) -> Unit)? = null

    class ViewHolder(private val binding:ListItemBinding):RecyclerView.ViewHolder(binding.root){
         fun bind(card:CardEntity){
             binding.mTitle.text = card.task
            binding.mSubTitle.text = card.description
         }

        companion object{
                fun create (parent:ViewGroup):ViewHolder{
                    return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = getItem(position)
        holder.bind(card)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(card)
        }
    }
}

class CardDiffCallback : DiffUtil.ItemCallback<CardEntity>() {
    override fun areItemsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
        return oldItem.id ==newItem.id
    }

    override fun areContentsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
        return oldItem==newItem
    }
}

class CardItemListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(card: CardEntity) = clickListener(card.id)
}