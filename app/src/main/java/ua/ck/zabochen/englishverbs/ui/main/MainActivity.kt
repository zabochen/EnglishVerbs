package ua.ck.zabochen.englishverbs.ui.main

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.ui.bookmark.BookmarkFragment
import ua.ck.zabochen.englishverbs.ui.setting.SettingFragment
import ua.ck.zabochen.englishverbs.ui.verblist.VerbListFragment
import ua.ck.zabochen.englishverbs.utils.behavior.BottomNavigationViewBehavior

class MainActivity : AppCompatActivity(), MainView, AnkoLogger {

    @BindView(R.id.snippet_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.activityMain_frameLayout_fragmentHolder)
    lateinit var fragmentHolder: FrameLayout

    @BindView(R.id.activityMain_bottomNavigationView)
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
        addObservers()
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun addObservers() {
    }

    private fun setUi() {
        // Layout & ButterKnife
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        // Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Set Default Fragment
        setFragment(VerbListFragment())

        // Bottom Navigation View - Behavior (Show/Hide)
        val layoutParams: CoordinatorLayout.LayoutParams = bottomNavigationView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationViewBehavior()

        // Bottom Navigation View - Listener (Item selected)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuBottomNavigationView_item_home -> setFragment(VerbListFragment())
                R.id.menuBottomNavigationView_item_bookmarks -> setFragment(BookmarkFragment.newInstance())
                R.id.menuBottomNavigationView_item_settings -> setFragment(SettingFragment())
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(fragmentHolder.id, fragment)
                .commit()
    }
}