package com.cdr.app.screens.game

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.cdr.app.screens.home.HomeViewModel
import com.cdr.core.views.BaseFragment
import com.cdr.core.views.CustomAction
import com.cdr.core.views.HasCustomAction
import com.cdr.core.views.screenViewModel
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.FragmentGameBinding

class GameFragment : BaseFragment(R.layout.fragment_game), HasCustomAction {

    override val viewModel: GameViewModel by screenViewModel()
    private lateinit var binding: FragmentGameBinding
    private val args by navArgs<GameFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameBinding.bind(view)

        with(binding) {
            difficultTextView.text = createDifficultLabel()

            helpButton.setOnClickListener { viewModel.launchResultScreen(this@GameFragment) }
        }
    }

    private fun createDifficultLabel(): String = when (args.difficultArg) {
        HomeViewModel.DIFFICULTY_EASY -> getString(R.string.difficultEasy)
        HomeViewModel.DIFFICULTY_MIDDLE -> getString(R.string.difficultMiddle)
        HomeViewModel.DIFFICULTY_HARD -> getString(R.string.difficultHard)
        HomeViewModel.DIFFICULTY_EXPERT -> getString(R.string.difficultExpert)
        else -> getString(R.string.error)
    }

    override fun getCustomAction(): CustomAction = CustomAction(
        iconRes = R.drawable.ic_stop,
        textAction = getString(R.string.textStopGameButton),
        onCustomAction = { viewModel.exitGame(this) }
    )
}