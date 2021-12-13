package com.training.simplefinancetracker.cardAddition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.training.simplefinancetracker.databinding.FragmentCardAdditionBinding
import com.training.simplefinancetracker.persistence.CostType
import com.training.simplefinancetracker.persistence.Expenditure
import java.util.*


class CardAdditionFragment : BottomSheetDialogFragment(), MavericksView {

    private val viewModel: CardAdditionViewModel by fragmentViewModel()
    private var _binding: FragmentCardAdditionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCardAdditionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveBt.setOnClickListener {

            viewModel.insertCard(
                    label = binding.labelTIL.editText?.text.toString(),
                    costType = CostType.FIX,
                    cost = binding.costTIL.editText?.text.toString().toDouble(),
                    isPaid = binding.paidSwitch.isChecked
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun invalidate() = withState(viewModel) { state ->
        if (state.isDone is Success) {
            findNavController().navigate(CardAdditionFragmentDirections.actionCardAdditionFragmentPopIncludingCardListFragment(state.parentId.toString()))
        }
    }
}