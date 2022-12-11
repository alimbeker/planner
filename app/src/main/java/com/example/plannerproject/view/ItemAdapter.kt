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

class  ItemAdapter : ListAdapter<CardEntity, ItemAdapter.ViewHolder>(CardDiffUtil) {
    var onItemClick : ((CardEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = getItem(position)
        holder.bind(card)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(card)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.mTitle
        /*  private val subTitle = itemView.mSubTitle*/

        fun bind(card: CardEntity) {
            title.text = card.task
            /* subTitle.text = card.aboutTask*/


        }
    }

}

object CardDiffUtil : DiffUtil.ItemCallback<CardEntity>() {
    override fun areItemsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
        return oldItem == newItem
    }
}

class CardItemListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(card: CardEntity) = clickListener(card.id)
}