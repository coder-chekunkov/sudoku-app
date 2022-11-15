package com.cdr.sudoku.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cdr.sudoku.R
import com.cdr.sudoku.contract.HasCustomIcon
import com.cdr.sudoku.contract.HasCustomTitle
import com.cdr.sudoku.contract.IsGameButtonClickable
import com.cdr.sudoku.contract.navigator
import com.cdr.sudoku.databinding.FragmentResultBinding
import kotlin.properties.Delegates

class ResultFragment : Fragment(), HasCustomTitle, HasCustomIcon, IsGameButtonClickable {

    private lateinit var binding: FragmentResultBinding
    private var result by Delegates.notNull<Boolean>()
    private var difficult by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        result = arguments?.getBoolean(KEY_ARG_RESULT) ?: GameFragment.RESULT_LOST
        difficult = arguments?.getInt(KEY_ARG_DIFFICULT) ?: LaunchGameFragment.DIFFICULTY_EASY
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)

        renderUI() // Отображение данных о результе
        binding.acceptButton.setOnClickListener { clickAcceptButton() } // Нажатие на кнопку "ОК"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startEmojiRain()
    }

    private fun renderUI() {
        with(binding) {
            if (result) {
                resultTextView.text = getString(R.string.titleWinResult)
                resultImageView.setImageResource(R.drawable.ic_win)
            } else {
                resultTextView.text = getString(R.string.titleLostResult)
                resultImageView.setImageResource(R.drawable.ic_lost)
            }

            difficultTextView.text = createDifficultString()
        }
    }

    // Запуск emoji-дождя:
    private fun startEmojiRain() {
        val mContainer = binding.container

        if (result == GameFragment.RESULT_WIN) {
            mContainer.addEmoji(R.drawable.emoji_win_1) // Добавление нового эмоджи
            mContainer.addEmoji(R.drawable.emoji_win_2) // Добавление нового эмоджи
            mContainer.addEmoji(R.drawable.emoji_win_3) // Добавление нового эмоджи
        } else {
            mContainer.addEmoji(R.drawable.emoji_lost_1) // Добавление нового эмоджи
            mContainer.addEmoji(R.drawable.emoji_lost_2) // Добавление нового эмоджи
            mContainer.addEmoji(R.drawable.emoji_lost_3) // Добавление нового эмоджи
        }

        mContainer.setPer(5) // Количество (по умолчанию 6)
        mContainer.setDuration(6000) // Длительность в милисекундах (по умолчанию 8000)
        mContainer.setDropDuration(3400) // Длительность падения в милисекундах (по умолчанию 2400)
        mContainer.setDropFrequency(500) // Частота падения (по умолчанию 500)

        mContainer.startDropping()
    }

    private fun clickAcceptButton() = navigator().showLaunchGameFragment()

    private fun createDifficultString(): String = when (difficult) {
        LaunchGameFragment.DIFFICULTY_EASY -> getString(R.string.difficultEasy)
        LaunchGameFragment.DIFFICULTY_MIDDLE -> getString(R.string.difficultMiddle)
        LaunchGameFragment.DIFFICULTY_HARD -> getString(R.string.difficultHard)
        LaunchGameFragment.DIFFICULTY_EXPERT -> getString(R.string.difficultExpert)
        else -> "Error"
    }

    companion object {
        @JvmStatic
        private val KEY_ARG_RESULT = "ARG_RESULT"

        @JvmStatic
        private val KEY_ARG_DIFFICULT = "ARG_DIFFICULT"

        @JvmStatic
        fun newInstance(newDifficult: Int, newResult: Boolean): Fragment {
            val bundle = Bundle()
            bundle.putBoolean(KEY_ARG_RESULT, newResult)
            bundle.putInt(KEY_ARG_DIFFICULT, newDifficult)

            val fragment = ResultFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getResTitle(): Int = R.string.titleToolbarResult
    override fun getResIcon(): Int = R.drawable.ic_result
    override fun isGameButtonClickable(): Boolean = false
}
