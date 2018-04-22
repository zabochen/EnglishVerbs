package ua.ck.zabochen.englishverbs.view.verbfull

import android.content.Context
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.helper.database.RealmHelper
import javax.inject.Inject

class VerbFullPresenter : MvpBasePresenter<VerbFullView>() {

    @Inject
    lateinit var mRealmHelper: RealmHelper

    init {
        MainApp.mAppInstance.getActivityComponent().inject(this)
    }

    fun loadVerb(context: Context, verbPosition: Int) {
    }

    fun speakVerb(verb: String) {
    }

}