package com.cdr.app.screens.game

import android.os.Bundle
import android.view.View
import com.cdr.core.views.BaseFragment
import com.cdr.core.views.BaseScreen
import com.cdr.core.views.screenViewModel
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.FragmentGameBinding

class GameFragment : BaseFragment(R.layout.fragment_game) {

    override val viewModel: GameViewModel by screenViewModel()

    private lateinit var binding: FragmentGameBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameBinding.bind(view)

        with(binding) {
            viewModel.difficult.observe(viewLifecycleOwner) { difficultTextView.text = it }
        }
    }

    class Screen(val difficult: Int) : BaseScreen
}