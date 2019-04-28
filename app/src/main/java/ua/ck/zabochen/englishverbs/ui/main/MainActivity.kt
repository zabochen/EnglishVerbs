package ua.ck.zabochen.englishverbs.ui.main

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.mvp.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(), AnkoLogger {

    lateinit var unbinder: Unbinder

    @BindView(R.id.snippet_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.activityMain_bottomNavigationView)
    lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
    }

    private fun setUi() {
        // Layout & ButterKnife
        setContentView(R.layout.activity_main)
        this.unbinder = ButterKnife.bind(this)

        // Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Bottom Navigation View - Behavior (Show/Hide)
        // val layoutParams: CoordinatorLayout.LayoutParams = bottomNavigationView.layoutParams as CoordinatorLayout.LayoutParams
        // layoutParams.behavior = BottomNavigationViewBehavior()

        // Navigation
        this.navigationController = Navigation.findNavController(this, R.id.activityMain_fragment_navigationHost)
        NavigationUI.setupWithNavController(bottomNavigationView, navigationController)
    }
}