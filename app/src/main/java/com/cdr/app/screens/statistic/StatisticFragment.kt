package com.cdr.app.screens.statistic

import android.os.Bundle
import android.view.View
import com.cdr.core.views.BaseFragment
import com.cdr.core.views.BaseScreen
import com.cdr.core.views.BaseViewModel
import com.cdr.core.views.HasCustomTitle
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.FragmentStatisticBinding

class StatisticFragment : BaseFragment(R.layout.fragment_statistic), HasCustomTitle {

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")
    private lateinit var binding: FragmentStatisticBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStatisticBinding.bind(view)
    }

    override fun getScreenTitle(): String = getString(R.string.titleToolbarStatistic)
    class Screen : BaseScreen
}