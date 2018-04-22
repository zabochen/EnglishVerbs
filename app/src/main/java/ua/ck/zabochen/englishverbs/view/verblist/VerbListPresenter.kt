package ua.ck.zabochen.englishverbs.view.verblist

import android.content.Context
import android.content.Intent
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.helper.database.RealmHelper
import ua.ck.zabochen.englishverbs.utils.Constants
import ua.ck.zabochen.englishverbs.view.verbfull.VerbFullActivity
import javax.inject.Inject

class VerbListPresenter : MvpBasePresenter<VerbListView>() {

    init {
        MainApp.mainAppInstance().getFragmentComponent().inject(this)
    }

    @Inject
    lateinit var mRealmHelper: RealmHelper

    fun viewIsReady() {
        ifViewAttached {
            it.setUi(mRealmHelper.getVerbList())
        }
    }

    fun onClickVerbItem(activityContext: Context, position: Int) {
        val intentVerbFullActivity = Intent(activityContext, VerbFullActivity::class.java)
        intentVerbFullActivity.putExtra(Constants.INTENT_VERB_SELECTED_POSITION, position)
        activityContext.startActivity(intentVerbFullActivity)
    }

}