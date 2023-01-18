package com.cdr.app

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cdr.core.ActivityScopeViewModel
import com.cdr.core.navigator.IntermediateNavigator
import com.cdr.core.navigator.NavigatorExecutor
import com.cdr.core.uiactions.AndroidUiActions
import com.cdr.core.utils.viewModelCreator
import com.cdr.core.views.FragmentHolder
import com.cdr.sudoku.R
import com.cdr.sudoku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentHolder {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navigator: NavigatorExecutor
    private val viewModel by viewModelCreator<ActivityScopeViewModel> {
        ActivityScopeViewModel(
            navigator = IntermediateNavigator(),
            uiActions = AndroidUiActions(
                activity = this,
                appContext = applicationContext
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        navigator = NavigatorExecutor(
            activity = this,
            navController = createNavController(),
            fragmentContainer = R.id.fragmentContainer,
            toolbar = binding.toolbar,
            defaultTitle = getString(R.string.app_name),
            topLevelDestinations = createTopLevelDestinations()
        )

        navigator.onCreate()
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

    override fun onSupportNavigateUp(): Boolean =
        navigator.onSupportNavigateUp() || super.onSupportNavigateUp()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        notifyScreenUpdates()
        return super.onCreateOptionsMenu(menu)
    }

    override fun notifyScreenUpdates() = navigator.notifyScreenUpdates()
    override fun getActivityScopeViewModel(): ActivityScopeViewModel = viewModel

    private fun createNavController(): NavController {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        return navHost.navController
    }

    private fun createTopLevelDestinations(): List<Int> =
        listOf(R.id.fragmentRoot, R.id.gameFragment, R.id.resultFragment)
}