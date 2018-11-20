package ca.sheridancollege.simplenewsapp.ui.mainActivity

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import ca.sheridancollege.simplenewsapp.R
import ca.sheridancollege.simplenewsapp.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainActivityViewModel
//    private lateinit var mainActivityBinding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        setSupportActionBar(findViewById(R.id.toolbar))
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment?
                ?: return

        val navController = host.navController
        setupActionBar(navController)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.main_nav_host_fragment)) || super.onOptionsItemSelected(item)
    }


    private fun setupActionBar(navController: NavController) {
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.main_nav_host_fragment).navigateUp()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
