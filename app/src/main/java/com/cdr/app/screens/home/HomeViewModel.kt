package com.cdr.app.screens.home

import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.cdr.app.model.facts.FactsService
import com.cdr.app.screens.root.FragmentRootDirections
import com.cdr.core.navigator.Navigator
import com.cdr.core.uiactions.UiActions
import com.cdr.core.views.BaseViewModel
import com.cdr.sudoku.R

class HomeViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val factsService: FactsService
) : BaseViewModel() {

    private val _fact = MutableLiveData<String>()
    val fact: LiveData<String> = _fact

    private var currentDifficulty = DIFFICULTY_EASY

    init {
        _fact.value = factsService.getRandomFact()
    }

    fun updateFact() {
        _fact.value = factsService.getRandomFact()
    }

    fun showInfoScreen(navController: NavController) {
        val directions = HomeFragmentDirections.actionHomeFragmentToInformationFragment()
        navigator.launchByNavController(navController, directions)
    }

    fun showGameScreen(fragment: Fragment) {
        val directions = FragmentRootDirections.actionFragmentRootToGameFragment(currentDifficulty)
        navigator.launchByTopNavController(fragment, directions)
    }

    val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            currentDifficulty = difficultyCases[p2][VALUE_DIFFICULTY] as Int
            if (p1 != null) {
                uiActions.showSnackbar(
                    view = p1,
                    message = messageWithDifficulty(),
                    backgroundColor = R.color.darkBlue,
                    mainColor = R.color.white
                )
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

    private fun messageWithDifficulty(): String =
        when (currentDifficulty) {
            DIFFICULTY_EASY -> uiActions.getString(R.string.messageDifficultEasy)
            DIFFICULTY_MIDDLE -> uiActions.getString(R.string.messageDifficultMiddle)
            DIFFICULTY_HARD -> uiActions.getString(R.string.messageDifficultHard)
            DIFFICULTY_EXPERT -> uiActions.getString(R.string.messageDifficultExpert)
            else -> uiActions.getString(R.string.messageDifficultEasy)
        }

    companion object {
        const val TITLE_DIFFICULTY = "TITLE_DIFFICULTY"
        private const val VALUE_DIFFICULTY = "VALUE_DIFFICULTY"
        const val DIFFICULTY_EASY: Int = 1 // Уровень сложности: Лёгкая
        const val DIFFICULTY_MIDDLE: Int = 2 // Уровень сложности: Средняя
        const val DIFFICULTY_HARD: Int = 3 // Уровень сложности: Сложная
        const val DIFFICULTY_EXPERT: Int = 4 // Уровень сложности: Экспертная
        val difficultyCases = mutableListOf( // Коллекция со всеми значениями для Spinner
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