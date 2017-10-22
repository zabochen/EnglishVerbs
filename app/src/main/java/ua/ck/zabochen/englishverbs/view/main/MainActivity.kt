package ua.ck.zabochen.englishverbs.view.main

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.view.verblist.VerbListFragment

class MainActivity : MvpAppCompatActivity(),
        MainView {

    @InjectPresenter lateinit var mMainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate or Update Database
        inflateOrUpdateDatabase()

        // Layout
        setContentView(R.layout.activity_main)

        // Set VerbList Fragment
        setVerbList()
    }

    private fun setVerbList() {
        supportFragmentManager
                .beginTransaction()
                .replace(activityMain_frameLayout.id, VerbListFragment())
                .commit()
    }

    private fun inflateOrUpdateDatabase() {
        mMainPresenter.inflateOrUpdateDatabase()
    }

}