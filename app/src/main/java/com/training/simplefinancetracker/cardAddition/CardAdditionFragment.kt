package com.training.simplefinancetracker.cardAddition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.training.simplefinancetracker.R
import com.training.simplefinancetracker.databinding.FragmentCardAdditionBinding
import com.training.simplefinancetracker.persistence.CostType
import timber.log.Timber


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

        val items = listOf(OPTION_FIX, OPTION_VARIABLE)
        val adapter = ArrayAdapter(requireContext(), R.layout.base_drop_down_item, items)
        val costTypeET = (binding.costTypeTIL.editText as? AutoCompleteTextView)
        costTypeET?.setAdapter(adapter)
        costTypeET?.setText(OPTION_FIX, false)


        binding.saveBt.setOnClickListener {

            Timber.d("listSelection is: " + costTypeET?.text?.toString())
            viewModel.insertCard(
                label = binding.labelTIL.editText?.text.toString(),
                costType = when (costTypeET?.text?.toString()) {
                    OPTION_FIX-> CostType.FIX
                    OPTION_VARIABLE -> CostType.VARIABLE
                    else -> throw Exception("value is likely null")
                },
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
            findNavController().navigate(
                CardAdditionFragmentDirections.actionCardAdditionFragmentPopIncludingCardListFragment(
                    state.parentId.toString()
                )
            )
        }
    }

    companion object{
        private const val OPTION_FIX = "Fix"
        private const val OPTION_VARIABLE = "Variabel"
    }
}