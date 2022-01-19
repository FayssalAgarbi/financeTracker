package com.training.simplefinancetracker.cardList


import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.training.simplefinancetracker.R
import com.training.simplefinancetracker.databinding.BaseCardItemBinding
import com.training.simplefinancetracker.persistence.CostType
import com.training.simplefinancetracker.persistence.Expenditure
import com.training.simplefinancetracker.util.BindingViewHolder

class CardListAdapter(
    private val longClickListener: (Expenditure) -> Unit,
    private val clickListener: (Expenditure) -> Unit
) : ListAdapter<CardListAdapter.Item, BindingViewHolder<BaseCardItemBinding>>(
    object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = when {
            oldItem is Item.ExpenditureItem && newItem is Item.ExpenditureItem
            -> oldItem.expenditure.id == newItem.expenditure.id
            else -> false
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem
    }) {

    fun update(expenditures: List<Expenditure>){
        val items = mutableListOf<Item>()
        items += expenditures.map { Item.ExpenditureItem(it) }
        submitList(items)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<BaseCardItemBinding> =
        BindingViewHolder(parent, BaseCardItemBinding::inflate)

    override fun onBindViewHolder(holder: BindingViewHolder<BaseCardItemBinding>, position: Int) {
        val card = getItem(position)
        with(holder.binding) {
            rootCard.setCardBackgroundColor(
                when ((card as Item.ExpenditureItem).expenditure.costType) {
                    CostType.FIX -> ContextCompat.getColorStateList(root.context, R.color.japonica)
                    CostType.VARIABLE -> ContextCompat.getColorStateList(
                        root.context,
                        R.color.design_default_color_secondary_variant
                    )
                }
            )
            valueTypeTV.text = card.expenditure.label
            valueSummaryTV.text = card.expenditure.cost.toString()
            root.setOnClickListener {
                clickListener(card.expenditure)
            }

            //TODO change this such that the card can be edited rather than deleted
            root.setOnLongClickListener {
                longClickListener(card.expenditure)
                true
            }
        }
    }

    fun getSpanSize(position: Int, spanCount: Int) = spanCount


    sealed class Item {
        data class ExpenditureItem(val expenditure: Expenditure) : Item()
    }
}