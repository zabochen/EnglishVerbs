package ua.ck.zabochen.englishverbs.ui.main

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.callback.CallbackEvent
import ua.ck.zabochen.englishverbs.helper.database.RealmHelper
import ua.ck.zabochen.englishverbs.ui.verblist.VerbListFragment
import ua.ck.zabochen.englishverbs.utils.BottomNavigationViewItem
import ua.ck.zabochen.englishverbs.utils.behavior.BottomNavigationViewBehavior
import ua.ck.zabochen.englishverbs.view.bookmark.BookmarkFragment
import ua.ck.zabochen.englishverbs.view.notification.NotificationFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AnkoLogger {

    @Inject
    lateinit var mRealmHelper: RealmHelper

    init {
        MainApp.mainAppInstance().getActivityComponent().inject(this)
    }

    @BindView(R.id.snippet_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.activityMain_frameLayout)
    lateinit var frameLayout: FrameLayout

    @BindView(R.id.activityMain_progressBar)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.activityMain_bottomNavigationView)
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
        inflateOrUpdateDatabase()
    }

    private fun setUi() {
        // Layout & ButterKnife
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        // Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // ProgressBar
        progressBar.bringToFront()

        // Bottom Navigation View - Behavior (Show/Hide)
        val layoutParams: CoordinatorLayout.LayoutParams = bottomNavigationView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationViewBehavior()

        // Bottom Navigation View - Listener (Item selected)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                BottomNavigationViewItem.HOME.id -> setFragment(VerbListFragment())
                BottomNavigationViewItem.BOOKMARKS.id -> setFragment(BookmarkFragment())
                BottomNavigationViewItem.NOTIFICATION.id -> setFragment(NotificationFragment())
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun inflateOrUpdateDatabase() {
        mRealmHelper.inflateDatabase(object : CallbackEvent.DatabaseCallback {
            override fun onComplete() {
                setFragment(VerbListFragment())
            }

            override fun onError(error: Throwable) {
            }
        })
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(frameLayout.id, fragment)
                .commit()
    }
}