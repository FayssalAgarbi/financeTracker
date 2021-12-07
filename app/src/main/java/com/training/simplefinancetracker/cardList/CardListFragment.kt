package com.training.simplefinancetracker.cardList

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.training.simplefinancetracker.R
import com.training.simplefinancetracker.databinding.FragmentCardListBinding
import com.training.simplefinancetracker.util.viewBinding


class CardListFragment : Fragment(R.layout.fragment_card_list), MavericksView {

    private val viewModel: CardListViewModel by fragmentViewModel()
    private val binding: FragmentCardListBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.navBackBt.setOnClickListener {
            findNavController().navigate(CardListFragmentDirections.actionCardListFragmentToMainMenuFragment())
        }

        binding.recycler.adapter = CardListAdapter()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(CardListFragmentDirections.actionCardListFragmentToCardAdditionFragment())
        return true
    }

    override fun invalidate() = withState(viewModel){

    }
}