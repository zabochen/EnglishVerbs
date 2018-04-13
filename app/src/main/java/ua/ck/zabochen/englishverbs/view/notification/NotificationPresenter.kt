package ua.ck.zabochen.englishverbs.view.notification

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.helper.notification.NotificationHelper
import javax.inject.Inject

class NotificationPresenter : MvpBasePresenter<NotificationView>() {

    init {
        MainApp.mainAppInstance().getFragmentComponent().inject(this)
    }

    @Inject
    lateinit var mNotificationHelper: NotificationHelper

}