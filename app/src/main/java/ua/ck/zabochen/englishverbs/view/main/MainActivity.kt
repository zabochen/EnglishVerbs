package ua.ck.zabochen.englishverbs.view.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.utils.behavior.BottomNavigationViewBehavior
import ua.ck.zabochen.englishverbs.view.bookmark.BookmarkFragment
import ua.ck.zabochen.englishverbs.view.notification.NotificationFragment
import ua.ck.zabochen.englishverbs.view.verblist.VerbListFragment

class MainActivity : MvpActivity<MainView, MainPresenter>(),
        MainView, AnkoLogger {

    private val mToolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.snippet_toolbar) }
    private val mFrameLayout: FrameLayout by lazy { findViewById<FrameLayout>(R.id.activityMain_frameLayout) }
    private val mBottomNavigationView: BottomNavigationView by lazy { findViewById<BottomNavigationView>(R.id.activityMain_bottomNavigationView) }

    override fun createPresenter() = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
        inflateOrUpdateDatabase()
    }

    private fun inflateOrUpdateDatabase() {
        presenter.inflateOrUpdateDatabase()
    }

    private fun setUi() {
        // Layout
        setContentView(R.layout.activity_main)

        // Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Bottom Navigation View - Behavior (Hide/Show)
        val layoutParams: CoordinatorLayout.LayoutParams = mBottomNavigationView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationViewBehavior()

        // Bottom Navigation View - Listener (Item selected)
        mBottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuBottomNavigationView_item_home -> setFragment(VerbListFragment())
                R.id.menuBottomNavigationView_item_bookmarks -> setFragment(BookmarkFragment())
                R.id.menuBottomNavigationView_item_notifications -> setFragment(NotificationFragment())
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(mFrameLayout.id, fragment)
                .commit()
    }

}