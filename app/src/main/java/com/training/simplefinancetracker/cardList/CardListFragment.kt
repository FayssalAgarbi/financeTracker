package com.training.simplefinancetracker.cardList

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
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
import java.util.*


class CardListFragment : Fragment(R.layout.fragment_card_list), MavericksView {

    private val viewModel: CardListViewModel by fragmentViewModel()
    private val binding: FragmentCardListBinding by viewBinding()

    private var showRefreshButton = false
    lateinit var adapter: CardListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val layoutManager: LinearLayoutManager =
            object : GridLayoutManager(context, 2) {}.apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int = adapter.getSpanSize(position, 1)
                }
            }

        adapter = CardListAdapter(
            { longClickListener(it) },
            { clickListener(it) }
        )

        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter
    }

    private fun clickListener(expenditure: Expenditure) =
        findNavController().navigate(
            CardListFragmentDirections.actionCardListFragmentSelf(
                expenditure.id.toString()
            )
        )

    private fun longClickListener(expenditure: Expenditure) = viewModel.deleteCard(expenditure)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.refreshParentMI).isVisible = showRefreshButton
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.addItemMI) {
            findNavController().navigate(
                CardListFragmentDirections.actionCardListFragmentToCardAdditionFragment(
                    viewModel.id.toString()
                )
            )
            true
        } else if (item.itemId == R.id.refreshParentMI) {
            viewModel.updateParentCost()
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun invalidate() = withState(viewModel) { state ->
        if (state.cardList is Success) {
            state.cardList.invoke().apply {
                adapter.update(this)
                val currentVisibility = showRefreshButton
                showRefreshButton = isNotEmpty() && viewModel.id != UUID(0, 0)
                if(currentVisibility != showRefreshButton) activity?.invalidateOptionsMenu()
            }
        }
        if (state.totalCost is Success) {
            binding.totalCostTV.text = getString(
                R.string.total_cost,
                state.totalCost.invoke().first.toString(),
                state.totalCost.invoke().second.toString()
            )

            if (state.cardList is Success) {
                //if the last item in a list gets deleted, combineLatest() doesn't emit anything
                if (state.cardList.invoke().isEmpty()) binding.totalCostTV.text =
                    getString(R.string.total_cost, "0", "0")
            }
        }
    }
}