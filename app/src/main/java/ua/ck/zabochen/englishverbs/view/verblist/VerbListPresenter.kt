package ua.ck.zabochen.englishverbs.view.verblist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.database.RealmHelper
import javax.inject.Inject

@InjectViewState
class VerbListPresenter : MvpPresenter<VerbListView>() {

    init {
        MainApp.appComponent().inject(this)
    }

    @Inject lateinit var mRealmHelper: RealmHelper

    fun viewIsReady() {
        viewState.setUi(mRealmHelper.verbList())
    }

}