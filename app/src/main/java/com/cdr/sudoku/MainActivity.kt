package com.cdr.sudoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.cdr.sudoku.contract.HasCustomTitle
import com.cdr.sudoku.contract.Navigator
import com.cdr.sudoku.databinding.ActivityMainBinding
import com.cdr.sudoku.game.GameFragment
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
                        showGameFragment()
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
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, GameFragment())
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
    }

    override fun showHistoryFragment() = launchFragment(HistoryFragment())
    override fun showGameFragment() = launchFragment(GameFragment())
    override fun showStatisticFragment() = launchFragment(StatisticFragment())

    // Запуск нужного фрагмента:
    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }

    // Отрисовка Toolbar:
    private fun renderToolbar() {
        val fragment = currentFragment

        if (fragment is HasCustomTitle) supportActionBar?.title = getString(fragment.getResTitle())
        else supportActionBar?.title = getString(R.string.app_name)
    }
}