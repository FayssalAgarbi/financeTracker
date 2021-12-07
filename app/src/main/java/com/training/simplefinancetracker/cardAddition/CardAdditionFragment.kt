package com.training.simplefinancetracker.cardAddition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.MavericksView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.training.simplefinancetracker.databinding.FragmentCardAdditionBinding


class CardAdditionFragment : BottomSheetDialogFragment(), MavericksView {

   // private val binding: FragmentCardAdditionBinding by viewBinding()
    private var _binding: FragmentCardAdditionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCardAdditionBinding.inflate(inflater,  container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }



    override fun invalidate() {
        TODO("Not yet implemented")
    }
}