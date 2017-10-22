package ua.ck.zabochen.englishverbs.view.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.database.RealmHelper
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>(),
        AnkoLogger {

    init {
        MainApp.appComponent().inject(this)
    }

    @Inject lateinit var mRealmHelper: RealmHelper

    fun inflateOrUpdateDatabase() {
        mRealmHelper.inflateDatabase()
    }

}