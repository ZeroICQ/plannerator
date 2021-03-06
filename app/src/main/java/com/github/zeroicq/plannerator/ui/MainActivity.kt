package com.github.zeroicq.plannerator.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.ActivityMainBinding
import com.github.zeroicq.plannerator.mvp.presenters.MainPresenter
import com.github.zeroicq.plannerator.mvp.views.MainView
import kotlinx.android.synthetic.main.app_bar_main.*
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppNavigator


class MainActivity : MvpAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainView {
    private lateinit var binding: ActivityMainBinding

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val navigator = SupportAppNavigator(this, R.id.mainContainer).apply {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        presenter.app.router.newRootScreen(MonthScreen())
//        presenter.app.router.newRootScreen(WeekScreen())

        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.navDay -> presenter.onDayCommandClick()
            R.id.navWeek -> presenter.onWeekCommandClick()
            R.id.navMonth -> presenter.onMonthCommandClick()
        }
//        when (item.itemId) {
//            R.id.nav_camera -> {
//                // Handle the camera action
//            }
//            R.id.nav_gallery -> {
//
//            }
//            R.id.nav_slideshow -> {
//
//            }
//            R.id.nav_manage -> {
//
//            }
//            R.id.nav_share -> {
//
//            }
//            R.id.nav_send -> {
//
//            }
//        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun test() {
        Snackbar.make(binding.root, "testFunction", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    override fun setFabOnclickListener(listener: (View) -> Unit) {
        binding.createEventButton.setOnClickListener(listener)
    }

    override fun setShowCreateEventButton(show: Boolean) {
        if (show) binding.createEventButton.show() else binding.createEventButton.hide()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        presenter.app.navigatorHolder.setNavigator(navigator)
    }

//    override fun onResume() {
//        super.onResume()
//        presenter.app.navigatorHolder.setNavigator(navigator)
//    }



    override fun onPause() {
        super.onPause()
        presenter.app.navigatorHolder.removeNavigator()
    }

    override fun setToolBarText(text: String) {
        binding.toolbarText.text = text
    }

    override fun navigateTo(screen: Screen) {
        presenter.app.router.navigateTo(screen)
    }

    override fun newRootScreen(screen: Screen) {
        presenter.app.router.newRootScreen(screen)
    }

}
