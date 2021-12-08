package com.training.simplefinancetracker.cardList

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.training.simplefinancetracker.R
import com.training.simplefinancetracker.databinding.FragmentCardListBinding
import com.training.simplefinancetracker.persistence.Expenditure
import com.training.simplefinancetracker.util.viewBinding


class CardListFragment : Fragment(R.layout.fragment_card_list), MavericksView {

    private val viewModel: CardListViewModel by fragmentViewModel()
    private val binding: FragmentCardListBinding by viewBinding()
    lateinit var adapter: CardListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val layoutManager: LinearLayoutManager =
            object : GridLayoutManager(context, 2){}.apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int = adapter.getSpanSize(position, 1)
                }
            }

        adapter = CardListAdapter {
            longClickListener(it)
        }

        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter

    }

    private fun longClickListener(expenditure: Expenditure) = viewModel.deleteCard(expenditure)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.addItemMI){
            findNavController().navigate(CardListFragmentDirections.actionCardListFragmentToCardAdditionFragment())
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun invalidate() = withState(viewModel){ state ->
        if(state.cardList is Success){
            adapter.update(state.cardList.invoke())
        }

    }
}