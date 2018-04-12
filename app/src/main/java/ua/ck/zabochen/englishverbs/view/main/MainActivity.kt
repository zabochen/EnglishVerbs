package ua.ck.zabochen.englishverbs.view.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.utils.behavior.BottomNavigationViewBehavior
import ua.ck.zabochen.englishverbs.view.base.BaseActivity
import ua.ck.zabochen.englishverbs.view.bookmark.BookmarkFragment
import ua.ck.zabochen.englishverbs.view.notification.NotificationFragment
import ua.ck.zabochen.englishverbs.view.verblist.VerbListFragment

class MainActivity : BaseActivity(),
        MainView, AnkoLogger {

    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter

    private val mFrameLayout: FrameLayout by lazy { findViewById<FrameLayout>(R.id.activityMain_frameLayout) }
    private val mBottomNavigationView: BottomNavigationView by lazy { findViewById<BottomNavigationView>(R.id.activityMain_bottomNavigationView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set Ui
        setUi()

        // Inflate or Update Database
        inflateOrUpdateDatabase()
    }

    private fun inflateOrUpdateDatabase() {
        mMainPresenter.inflateOrUpdateDatabase()
    }

    private fun setUi() {
        // Layout
        setContentView(R.layout.activity_main)

        // Toolbar
        val toolbar: Toolbar = activityMain_toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Bottom Navigation View Behavior (Hide/Show)
        val layoutParams: CoordinatorLayout.LayoutParams = mBottomNavigationView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationViewBehavior()

        mBottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuBottomNavigationView_item_home -> setFragment(VerbListFragment())
                R.id.menuBottomNavigationView_item_bookmarks -> setFragment(BookmarkFragment())
                R.id.menuBottomNavigationView_item_notifications -> setFragment(NotificationFragment())
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(mFrameLayout.id, fragment)
                .commit()
    }

    override fun setVerbList() {
        supportFragmentManager.beginTransaction()
                .replace(activityMain_frameLayout.id, VerbListFragment())
                .commit()
    }

    override fun showProgressBar() {
    }

    override fun hideProgressBar() {
    }

}