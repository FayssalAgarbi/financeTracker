package com.training.simplefinancetracker.cardList


import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.training.simplefinancetracker.databinding.BaseCardItemBinding
import com.training.simplefinancetracker.util.BindingViewHolder

class CardListAdapter() : ListAdapter<CardListAdapter.Item, BindingViewHolder<BaseCardItemBinding>>(
    object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem
    }


) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<BaseCardItemBinding> = BindingViewHolder(parent, BaseCardItemBinding::inflate)

    override fun onBindViewHolder(holder: BindingViewHolder<BaseCardItemBinding>, position: Int) {
       // val card = getItem(position)
        with(holder.binding){
            valueTypeTV.text = "Fixkosten"
        }
    }

    data class Item(
        val id: Int
    )
}