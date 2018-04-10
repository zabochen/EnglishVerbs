package ua.ck.zabochen.englishverbs.view.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.utils.behavior.BottomNavigationViewBehavior
import ua.ck.zabochen.englishverbs.view.base.BaseActivity
import ua.ck.zabochen.englishverbs.view.verblist.VerbListFragment

class MainActivity : BaseActivity(),
        MainView {

    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter

    @BindView(R.id.activityMain_bottomNavigationView)
    lateinit var mBottomNavigationView: BottomNavigationView

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
        ButterKnife.bind(this)

        // Toolbar
        val toolbar: Toolbar = activityMain_toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Bottom Navigation View
        val layoutParams: CoordinatorLayout.LayoutParams = mBottomNavigationView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationViewBehavior()

    }

    override fun setVerbList() {
        supportFragmentManager
                .beginTransaction()
                .replace(activityMain_frameLayout.id, VerbListFragment())
                .commit()
    }

    override fun showProgressBar() {
    }

    override fun hideProgressBar() {
    }

}