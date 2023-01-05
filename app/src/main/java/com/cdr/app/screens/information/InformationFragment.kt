package com.cdr.app.screens.information

import android.os.Bundle
import android.view.View
import com.cdr.core.views.BaseFragment
import com.cdr.core.views.BaseScreen
import com.cdr.core.views.HasCustomTitle
import com.cdr.core.views.screenViewModel
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.FragmentInformationBinding

class InformationFragment : BaseFragment(R.layout.fragment_information), HasCustomTitle {

    override val viewModel: InformationViewModel by screenViewModel()
    private lateinit var binding: FragmentInformationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInformationBinding.bind(view)

        binding.okButton.setOnClickListener { viewModel.showHomeScreen() }
    }

    override fun getScreenTitle(): String = getString(R.string.titleToolbarInformation)
    class Screen : BaseScreen
}