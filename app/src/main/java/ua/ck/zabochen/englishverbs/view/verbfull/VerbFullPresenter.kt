package ua.ck.zabochen.englishverbs.view.verbfull

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.helper.database.RealmHelper
import ua.ck.zabochen.englishverbs.helper.notification.NotificationHelper
import ua.ck.zabochen.englishverbs.helper.speech.SpeechHelper
import javax.inject.Inject

@InjectViewState
class VerbFullPresenter : MvpPresenter<VerbFullView>() {

    init {
        MainApp.appComponent().inject(this)
    }

    @Inject lateinit var mRealmHelper: RealmHelper
    @Inject lateinit var mNotificationHelper: NotificationHelper
    @Inject lateinit var mSpeechHelper: SpeechHelper

    fun loadVerb(context: Context, verbPosition: Int) {
        mNotificationHelper.createNotification(context)
        viewState.setUi(mRealmHelper.getVerb(verbPosition))
    }

    fun speakVerb(verb: String) {
        mSpeechHelper.speech(verb)
    }

}