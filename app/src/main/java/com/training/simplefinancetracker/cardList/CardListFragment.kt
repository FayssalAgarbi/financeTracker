package com.training.simplefinancetracker.cardList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.training.simplefinancetracker.R
import com.training.simplefinancetracker.databinding.FragmentCardListBinding
import com.training.simplefinancetracker.util.viewBinding
import timber.log.Timber


class CardListFragment : Fragment(R.layout.fragment_card_list) {

    private val binding: FragmentCardListBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navBackBt.setOnClickListener {
            Timber.d("card list bt clicked")
            findNavController().navigate(CardListFragmentDirections.actionCardListFragmentToMainMenuFragment())
        }
        binding.recycler.adapter = CardListAdapter()

    }


}