package ua.ck.zabochen.englishverbs.view.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.view.base.BaseActivity
import ua.ck.zabochen.englishverbs.view.verblist.VerbListFragment

class MainActivity : BaseActivity(),
        MainView {

    @InjectPresenter lateinit var mMainPresenter: MainPresenter

    @BindView(R.id.activityMain_progressBarCenter) lateinit var mProgressBarCenter: ProgressBar

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
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbarTitle))
    }

    override fun setVerbList() {
        supportFragmentManager
                .beginTransaction()
                .replace(activityMain_frameLayout.id, VerbListFragment())
                .commit()
    }

    override fun showProgressBar() {
        mProgressBarCenter.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        mProgressBarCenter.visibility = View.GONE
    }

}