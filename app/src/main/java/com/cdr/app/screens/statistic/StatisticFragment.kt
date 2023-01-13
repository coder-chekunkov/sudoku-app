package com.cdr.app.screens.statistic

import android.os.Bundle
import android.view.View
import com.cdr.core.views.*
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.FragmentStatisticBinding

class StatisticFragment : BaseFragment(R.layout.fragment_statistic), HasCustomTitle,
    HasCustomAction {

    override val viewModel: StatisticViewModel by screenViewModel()
    private lateinit var binding: FragmentStatisticBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStatisticBinding.bind(view)
    }

    override fun getScreenTitle(): String = getString(R.string.titleToolbarStatistic)
    override fun getCustomAction(): CustomAction = CustomAction(
        iconRes = R.drawable.ic_remove,
        textAction = getString(R.string.textActionRemove),
        onCustomAction = { viewModel.clearHistoryAction() }
    )
}