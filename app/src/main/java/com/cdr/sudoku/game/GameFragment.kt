package com.cdr.sudoku.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cdr.sudoku.R
import com.cdr.sudoku.contract.HasCustomIcon
import com.cdr.sudoku.contract.HasCustomTitle
import com.cdr.sudoku.contract.IsGameButtonClickable
import com.cdr.sudoku.databinding.FragmentGameBinding
import java.util.*
import kotlin.properties.Delegates

class GameFragment : Fragment(), HasCustomTitle, HasCustomIcon, IsGameButtonClickable {

    // TODO: Переход к fragment с результатом игры:
//    handler.removeCallbacksAndMessages(token)
//    navigator().showResultFragment(difficult, mistakes, points, sec, false)

    private lateinit var binding: FragmentGameBinding
    private var difficult by Delegates.notNull<Int>() // Перменная сложности игры
    private var mistakes = 0 // Переменная количества ошибок
    private var points = 0 // Переменная количества заработанных очков
    private var sec = 0 // Переменная количества секунд
    private val handler = Handler(Looper.getMainLooper()) // Handler для работы секундомера
    private val token = Any() // Токен для отмены секундомера

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        difficult = arguments?.getInt(KEY_ARG_DIFFICULT) ?: LaunchGameFragment.DIFFICULTY_EASY

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)

        renderDifficult() // Отрисовка уровня сложности
        renderMistakesAndPoints() // Отрисовка количества ошибок и очков
        renderTime(String.format(Locale.getDefault(), "%02d:%02d", 0, 0)) // Отрисовка
        runTimer() // Запуск секундомера

        return binding.root
    }

    // Отрисовка уровня сложности:
    private fun renderDifficult() {
        binding.difficultTextView.text = when (difficult) {
            LaunchGameFragment.DIFFICULTY_EASY -> getString(R.string.difficultEasy)
            LaunchGameFragment.DIFFICULTY_MIDDLE -> getString(R.string.difficultMiddle)
            LaunchGameFragment.DIFFICULTY_HARD -> getString(R.string.difficultHard)
            LaunchGameFragment.DIFFICULTY_EXPERT -> getString(R.string.difficultExpert)
            else -> "Error"
        }
    }

    // Отрисовка колиества ошибок и очков:
    @SuppressLint("SetTextI18n")
    private fun renderMistakesAndPoints() {
        binding.mistakesTextView.text = "Ошибки: $mistakes/3"
        binding.pointsTextView.text = "Очки: $points"
    }

    // Отрисовка времени:
    @SuppressLint("SetTextI18n")
    private fun renderTime(time: String) {
        binding.timeTextView.text = "Время: $time"
    }

    // Запуск таймера:
    @SuppressLint("NewApi")
    private fun runTimer() {
        handler.postDelayed({
            sec += 1
            val minutes: Int = (sec % 3600) / 60
            val secs: Int = sec % 60
            val time = String.format(Locale.getDefault(), "%02d:%02d", minutes, secs)
            renderTime(time)
            runTimer()
        }, token, 1000)
    }

    override fun getResTitle(): Int = R.string.titleToolbarGame
    override fun getResIcon(): Int = R.drawable.ic_game
    override fun isGameButtonClickable(): Boolean = false

    companion object {
        @JvmStatic
        private val KEY_ARG_DIFFICULT = "ARG_DIFFICULT"

        @JvmStatic
        val RESULT_WIN = true

        @JvmStatic
        val RESULT_LOST = false

        @JvmStatic
        fun newInstance(newDifficult: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt(KEY_ARG_DIFFICULT, newDifficult)

            val fragment = GameFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}