package com.cdr.app.screens.history

import android.os.Bundle
import android.view.View
import com.cdr.core.views.BaseFragment
import com.cdr.core.views.BaseScreen
import com.cdr.core.views.BaseViewModel
import com.cdr.core.views.HasCustomTitle
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.FragmentHistoryBinding

class HistoryFragment : BaseFragment(R.layout.fragment_history), HasCustomTitle {

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")
    private lateinit var binding: FragmentHistoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHistoryBinding.bind(view)
    }

    override fun getScreenTitle(): String = getString(R.string.titleToolbarHistory)
    class Screen : BaseScreen
}