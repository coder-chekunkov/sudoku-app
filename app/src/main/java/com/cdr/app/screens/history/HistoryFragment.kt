package com.cdr.app.screens.history

import android.os.Bundle
import android.view.View
import com.cdr.core.views.*
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.FragmentHistoryBinding

class HistoryFragment : BaseFragment(R.layout.fragment_history), HasCustomTitle, HasCustomAction {

    override val viewModel: HistoryViewModel by screenViewModel()
    private lateinit var binding: FragmentHistoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHistoryBinding.bind(view)
    }

    override fun getScreenTitle(): String = getString(R.string.titleToolbarHistory)
    override fun getCustomAction(): CustomAction = CustomAction(
        iconRes = R.drawable.ic_remove,
        textAction = getString(R.string.textActionRemove),
        onCustomAction = { viewModel.clearHistoryAction() }
    )
}