package ez.wind.wheatmusic.ui.act

import android.content.Intent
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import ez.wind.wheatmusic.R
import ez.wind.wheatmusic.ui.base.BaseLifeCycleActivity
import ez.wind.wheatmusic.ui.frag.MainFragment
import ez.wind.wheatmusic.utils.LogUtil
import ez.wind.wheatmusic.utils.NavigationManager
import ez.wind.wheatmusic.utils.inflate
import ez.wind.wheatmusic.vm.MainActViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseLifeCycleActivity<MainActViewModel>(),
    NavigationView.OnNavigationItemSelectedListener {
    private var navigationManager: NavigationManager? = null
    override fun layoutResId() = R.layout.activity_main
    override fun initView() {
        super.initView()
        initNavigationView()
    }

    private fun initNavigationView() {
        val navHeader = R.layout.view_nav_header.inflate(navigationView)
        navigationView.addHeaderView(navHeader)
        navigationView.setNavigationItemSelectedListener(this)
        navigationManager = NavigationManager(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawers()
        return navigationManager?.onNavigationItemSelected(item) ?: false
    }

    override fun onBackPressed() {
        when {
            drawerLayout.isDrawerOpen(GravityCompat.END) -> {
                drawerLayout.closeDrawers()
            }
            isToDeskTop() -> {
                val homeIntent = Intent(Intent.ACTION_MAIN)
                homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                homeIntent.addCategory(Intent.CATEGORY_HOME)
                startActivity(homeIntent)
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    fun openDrawer() {
        if (!drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.openDrawer(GravityCompat.END)
        }
    }

    private fun isToDeskTop(): Boolean {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.main_Fragment)
        LogUtil.e("currentFragment$currentFragment")
        return currentFragment is MainFragment
    }

}