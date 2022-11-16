package com.cdr.sudoku.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import com.cdr.sudoku.R
import com.cdr.sudoku.contract.HasCustomIcon
import com.cdr.sudoku.contract.HasCustomTitle
import com.cdr.sudoku.contract.IsGameButtonClickable
import com.cdr.sudoku.contract.navigator
import com.cdr.sudoku.databinding.FragmentLaunchGameBinding
import com.cdr.sudoku.model.FactCreator

class LaunchGameFragment : Fragment(), HasCustomTitle, HasCustomIcon, IsGameButtonClickable {

    private lateinit var binding: FragmentLaunchGameBinding
    private var difficult = DIFFICULTY_EASY

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLaunchGameBinding.inflate(inflater, container, false)

        createDifficultySpinner()
        with(binding) {
            binding.factTextView.text = FactCreator().createFact(requireContext()) // Факт о судоку
            startNewGameButton.setOnClickListener { clickStartNewGameButton() } // Новая игра
            infoLaunchGameButton.setOnClickListener { clickInfoButton() } // Информация
        }

        return binding.root
    }

    // Создание Spinner с выбором сложности:
    private fun createDifficultySpinner() {
        binding.difficultySpinner.adapter = SimpleAdapter(
            requireContext(),
            difficultyCases,
            android.R.layout.simple_list_item_1,
            arrayOf(TITLE_DIFFICULTY),
            intArrayOf(android.R.id.text1)
        )
        binding.difficultySpinner.onItemSelectedListener = // Нажатие на выбранную сложность
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    difficult = difficultyCases[p2][VALUE_DIFFICULTY] as Int
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
    }

    private fun clickStartNewGameButton() = navigator().showGameFragment(difficult) // Новая игра
    private fun clickInfoButton() = navigator().showInfoSettings() // Информация

    override fun getResTitle(): Int = R.string.titleToolbarGame
    override fun getResIcon(): Int = R.drawable.ic_game
    override fun isGameButtonClickable(): Boolean = false

    companion object {
        @JvmStatic
        private val TITLE_DIFFICULTY = "TITLE_DIFFICULTY"

        @JvmStatic
        private val VALUE_DIFFICULTY = "VALUE_DIFFICULTY"

        @JvmStatic
        val DIFFICULTY_EASY: Int = 1 // Уровень сложности: Лёгкая

        @JvmStatic
        val DIFFICULTY_MIDDLE: Int = 2 // Уровень сложности: Средняя

        @JvmStatic
        val DIFFICULTY_HARD: Int = 3 // Уровень сложности: Сложная

        @JvmStatic
        val DIFFICULTY_EXPERT: Int = 4 // Уровень сложности: Экспертная

        @JvmStatic
        private val difficultyCases = mutableListOf( // Коллекция со всеми значениями для Spinner
            mapOf(TITLE_DIFFICULTY to "Сложность: Лёгкая", VALUE_DIFFICULTY to DIFFICULTY_EASY),
            mapOf(TITLE_DIFFICULTY to "Сложность: Средняя", VALUE_DIFFICULTY to DIFFICULTY_MIDDLE),
            mapOf(TITLE_DIFFICULTY to "Сложность: Сложная", VALUE_DIFFICULTY to DIFFICULTY_HARD),
            mapOf(
                TITLE_DIFFICULTY to "Сложность: Экспертная",
                VALUE_DIFFICULTY to DIFFICULTY_EXPERT
            )
        )
    }
}