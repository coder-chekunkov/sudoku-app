package com.cdr.app

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cdr.app.screens.game.GameFragment
import com.cdr.app.screens.history.HistoryFragment
import com.cdr.app.screens.home.HomeFragment
import com.cdr.app.screens.statistic.StatisticFragment
import com.cdr.core.ActivityScopeViewModel
import com.cdr.core.navigator.IntermediateNavigator
import com.cdr.core.navigator.StackFragmentNavigator
import com.cdr.core.uiactions.AndroidUiActions
import com.cdr.core.utils.viewModelCreator
import com.cdr.core.views.BaseScreen
import com.cdr.core.views.FragmentHolder
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentHolder {

    private lateinit var navigator: StackFragmentNavigator
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModelCreator<ActivityScopeViewModel> {
        ActivityScopeViewModel(
            navigator = IntermediateNavigator(), uiActions = AndroidUiActions(applicationContext)
        )
    }
    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        navigator = StackFragmentNavigator(activity = this,
            containerId = R.id.fragmentContainer,
            toolbar = binding.toolbar,
            defaultTitle = resources.getString(R.string.app_name),
            animations = StackFragmentNavigator.Animations(
                enterAnim = R.anim.enter,
                exitAnim = R.anim.exit,
                popEnterAnim = R.anim.pop_enter,
                popExitAnim = R.anim.pop_exit
            ),
            initialScreenCreator = { HomeFragment.Screen() })

        binding.bottomNavigation.selectedItemId = R.id.gameButton
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.historyButton -> {
                    clickNavigationButton(HistoryFragment.Screen())
                    return@setOnItemSelectedListener true
                }
                R.id.gameButton -> {
                    clickNavigationButton(HomeFragment.Screen())
                    return@setOnItemSelectedListener true
                }
                R.id.statisticButton -> {
                    clickNavigationButton(StatisticFragment.Screen())
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }

        navigator.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        viewModel.navigator.setTarget(navigator)
    }

    override fun onPause() {
        super.onPause()
        viewModel.navigator.setTarget(null)
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        notifyScreenUpdates()
        return true
    }

    override fun notifyScreenUpdates() = navigator.notifyScreenUpdates()
    override fun getActivityScopeViewModel(): ActivityScopeViewModel = viewModel
    override fun checkInternetConnection(): Boolean = navigator.checkInternetConnection()

    private fun clickNavigationButton(screenToLaunch: BaseScreen) {
        if (currentFragment is GameFragment) showInfoDialog(screenToLaunch)
        else navigator.launch(screenToLaunch, false)
    }

    private fun showInfoDialog(screenToLaunch: BaseScreen) {
        val listener = DialogInterface.OnClickListener { _, clickedButton ->
            when (clickedButton) {
                AlertDialog.BUTTON_POSITIVE -> navigator.launch(screenToLaunch, false)
                AlertDialog.BUTTON_NEGATIVE -> closeOptionsMenu()
            }
        }

        val dialog = AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_game_small)
            .setMessage(getString(R.string.dialogMessage))
            .setTitle(getString(R.string.dialogTitle))
            .setPositiveButton(getString(R.string.dialogButtonYes), listener)
            .setNegativeButton(getString(R.string.dialogButtonNo), listener)

        dialog.show()
    }
}