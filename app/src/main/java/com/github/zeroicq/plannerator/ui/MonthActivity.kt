package com.github.zeroicq.plannerator.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.databinding.DataBindingUtil
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.PagerSnapHelper
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.ActivityMonthBinding
import com.github.zeroicq.plannerator.mvp.presenters.MonthPresenter
import com.github.zeroicq.plannerator.mvp.views.MonthView
import com.github.zeroicq.plannerator.ui.adapters.MonthsAdapter
import com.github.zeroicq.plannerator.ui.layoutManagers.MonthByDayLayoutManager
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_month.view.*

class MonthActivity : MvpAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MonthView {
    private lateinit var binding: ActivityMonthBinding

    @InjectPresenter
    lateinit var presenter: MonthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_month)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            presenter.onClick()
        }

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        //recycler
        binding.monthRecycler.apply {
            adapter = MonthsAdapter()
            layoutManager = MonthByDayLayoutManager(this.context)
        }
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.monthRecycler)

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
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun test() {
        Snackbar.make(binding.root, "testFunction", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}
