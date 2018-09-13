package ua.ck.zabochen.englishverbs.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.utils.Constants
import ua.ck.zabochen.englishverbs.utils.showToast

class NotificationFragment : Fragment(), NotificationView, AnkoLogger {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Layout & ButterKnife
        val view: View = inflater.inflate(R.layout.fragment_notification, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().viewIsReady(notificationId = Constants.DATABASE_NOTIFICATION_ID)
    }

    override fun getViewModel(): NotificationViewModel {
        return ViewModelProviders.of(activity!!).get(NotificationViewModel::class.java)
    }

    override fun addObservers() {
        notificationStateObserver()
    }

    override fun notificationStateObserver() {
        getViewModel().notificationState.observe(this, Observer {

        })
    }

    @OnClick(R.id.fragmentNotification_switch_notificationState)
    fun onClickNotificationState(view: View) {
        info { "onClickNotificationState - ${view.id}" }
        activity?.showToast("onClickNotificationState - ${view.id}")
    }
}