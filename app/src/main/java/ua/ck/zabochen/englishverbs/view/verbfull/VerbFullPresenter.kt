package ua.ck.zabochen.englishverbs.view.verbfull

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.database.RealmHelper
import javax.inject.Inject

@InjectViewState
class VerbFullPresenter : MvpPresenter<VerbFullView>() {

    init {
        MainApp.appComponent().inject(this)
    }

    @Inject lateinit var mRealmHelper: RealmHelper

    fun loadVerb(verbPosition: Int) {
        viewState.setUi(mRealmHelper.getVerb(verbPosition))
    }

}