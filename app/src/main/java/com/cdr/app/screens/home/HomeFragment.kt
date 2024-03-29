package com.cdr.app.screens.home

import android.os.Bundle
import android.view.View
import android.widget.SimpleAdapter
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.cdr.app.screens.home.HomeViewModel.Companion.TITLE_DIFFICULTY
import com.cdr.app.screens.home.HomeViewModel.Companion.difficultyCases
import com.cdr.core.views.BaseFragment
import com.cdr.core.views.HasCustomTitle
import com.cdr.core.views.screenViewModel
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_home), HasCustomTitle {

    override val viewModel: HomeViewModel by screenViewModel()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        navController = findNavController()

        with(binding) {
            infoLaunchGameButton.setOnClickListener { viewModel.showInfoScreen(navController) }
            startNewGameButton.setOnClickListener { viewModel.showGameScreen(this@HomeFragment) }
            factTextView.setOnClickListener { viewModel.updateFact() }

            viewModel.fact.observe(viewLifecycleOwner) { factTextView.text = it }

            difficultySpinner.adapter = SimpleAdapter(
                requireContext(),
                difficultyCases,
                android.R.layout.simple_list_item_1,
                arrayOf(TITLE_DIFFICULTY),
                intArrayOf(android.R.id.text1)
            )
            difficultySpinner.onItemSelectedListener = viewModel.onItemSelectedListener
        }
    }

    override fun getScreenTitle(): String = getString(R.string.titleToolbarStartGame)
}