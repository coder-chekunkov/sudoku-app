package com.cdr.sudoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.cdr.sudoku.contract.HasCustomIcon
import com.cdr.sudoku.contract.HasCustomTitle
import com.cdr.sudoku.contract.IsGameButtonClickable
import com.cdr.sudoku.contract.Navigator
import com.cdr.sudoku.databinding.ActivityMainBinding
import com.cdr.sudoku.game.GameFragment
import com.cdr.sudoku.game.LaunchGameFragment
import com.cdr.sudoku.game.ResultFragment
import com.cdr.sudoku.history.HistoryFragment
import com.cdr.sudoku.statistic.StatisticFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding // Main-Binding
    private lateinit var bottomNavigationView: BottomNavigationView // Bottom Navigation
    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!
    private val fragmentLifecycleCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            renderToolbar()
            setClickableGameButton()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        setSupportActionBar(binding.toolbar) // Инициализация Toolbar
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, false)
        bottomNavigationView = binding.bottomNavigation.also {
            it.selectedItemId = R.id.gameButton
            it.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.historyButton -> {
                        showHistoryFragment()
                        true
                    }
                    R.id.gameButton -> {
                        showLaunchGameFragment()
                        true
                    }
                    R.id.statisticButton -> {
                        showStatisticFragment()
                        true
                    }
                    else -> false
                }
            }
        } // Инициализация BottomNavigation

        // Запуск GameFragment при первом запуске:
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, LaunchGameFragment()).commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
    }

    override fun showHistoryFragment() = launchFragment(HistoryFragment())
    override fun showLaunchGameFragment() = launchFragment(LaunchGameFragment())
    override fun showStatisticFragment() = launchFragment(StatisticFragment())
    override fun showGameFragment(diff: Int) = launchFragment(GameFragment.newInstance(diff))
    override fun showResultFragment(diff: Int, res: Boolean) =
        launchFragment(ResultFragment.newInstance(diff, res))

    // Запуск нужного фрагмента:
    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }

    // Отрисовка Toolbar:
    private fun renderToolbar() {
        val fragment = currentFragment

        supportActionBar?.title =
            if (fragment is HasCustomTitle) getString(fragment.getResTitle()) else getString(R.string.app_name)

        if (fragment is HasCustomIcon) supportActionBar?.setIcon(fragment.getResIcon())
    }

    // "Разрешение" нажатия на кнопку игры (запрещенно, если уже запущена игра):
    private fun setClickableGameButton() {
        val fragment = currentFragment

        binding.bottomNavigation.menu.findItem(R.id.gameButton).isEnabled =
            if (fragment is IsGameButtonClickable) fragment.isGameButtonClickable() else true
    }
}