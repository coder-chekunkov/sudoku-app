package com.cdr.sudoku.statistic

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import com.cdr.sudoku.R
import com.cdr.sudoku.contract.HasCustomIcon
import com.cdr.sudoku.contract.HasCustomTitle
import com.cdr.sudoku.databinding.FragmentStatisticBinding

class StatisticFragment : Fragment(), View.OnClickListener, HasCustomTitle, HasCustomIcon {

    private lateinit var binding: FragmentStatisticBinding
    private var currentDifficult: Int = DIFFICULTY_EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticBinding.inflate(inflater, container, false)

        with(binding) {
            titleDifficultEasy.setOnClickListener(this@StatisticFragment)
            titleDifficultMiddle.setOnClickListener(this@StatisticFragment)
            titleDifficultHard.setOnClickListener(this@StatisticFragment)
            titleDifficultExpert.setOnClickListener(this@StatisticFragment)
        }

        renderContent()
        return binding.root
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.titleDifficultEasy -> currentDifficult = DIFFICULTY_EASY
            R.id.titleDifficultMiddle -> currentDifficult = DIFFICULTY_MIDDLE
            R.id.titleDifficultHard -> currentDifficult = DIFFICULTY_HARD
            R.id.titleDifficultExpert -> currentDifficult = DIFFICULTY_EXPERT
        }
        renderDifficultButtons(view.id)
    }

    private fun renderDifficultButtons(id: Int) {
        for (view in binding.buttonsDifficult) {
            if (view is TextView) {
                val color = if (view.id != id) R.color.darkGrey else R.color.blue
                view.setTextColor(ContextCompat.getColor(requireContext(), color))
            }
        }
        renderContent()
    }

    private fun renderContent() {
        binding.test.text = currentDifficult.toString()
    }

    override fun getResTitle(): Int = R.string.titleStatisticButton
    override fun getResIcon(): Int = R.drawable.ic_statistic

    companion object {
        private const val DIFFICULTY_EASY: Int = 1 // Уровень сложности: Лёгкая
        private const val DIFFICULTY_MIDDLE: Int = 2 // Уровень сложности: Средняя
        private const val DIFFICULTY_HARD: Int = 3 // Уровень сложности: Сложная
        private const val DIFFICULTY_EXPERT: Int = 4 // Уровень сложности: Экспертная
    }
}