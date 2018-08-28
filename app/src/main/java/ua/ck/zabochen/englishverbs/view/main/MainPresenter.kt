package ua.ck.zabochen.englishverbs.view.main

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.callback.CallbackEvent
import ua.ck.zabochen.englishverbs.helper.database.RealmHelper
import ua.ck.zabochen.englishverbs.ui.verblist.VerbListFragment
import javax.inject.Inject

class MainPresenter : MvpBasePresenter<MainView>() {

    init {
        MainApp.mainAppInstance().getActivityComponent().inject(this)
    }

    @Inject
    lateinit var mRealmHelper: RealmHelper

    fun inflateOrUpdateDatabase() {
        mRealmHelper.inflateDatabase(object : CallbackEvent.DatabaseCallback {
            override fun onComplete() {
                ifViewAttached {
                    it.setFragment(VerbListFragment())
                }
            }

            override fun onError(error: Throwable) {
            }
        })
    }

}