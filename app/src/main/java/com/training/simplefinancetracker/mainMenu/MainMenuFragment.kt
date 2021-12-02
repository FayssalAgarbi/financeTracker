package com.training.simplefinancetracker.mainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.training.simplefinancetracker.R
import com.training.simplefinancetracker.databinding.FragmentMainMenuBinding
import com.training.simplefinancetracker.util.viewBinding
import timber.log.Timber


class MainMenuFragment : Fragment(R.layout.fragment_main_menu), MavericksView {

    private val viewModel: MainMenuViewModel by fragmentViewModel()
    private val binding: FragmentMainMenuBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navToCardlistBt.setOnClickListener {
            Timber.d("main menu bt clicked")
            findNavController().navigate(MainMenuFragmentDirections.actionMainMenuFragmentToCardListFragment())
        }
    }

    override fun invalidate() = withState(viewModel) {

    }
}
