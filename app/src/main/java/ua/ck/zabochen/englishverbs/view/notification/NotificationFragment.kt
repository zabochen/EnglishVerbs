package ua.ck.zabochen.englishverbs.view.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import ua.ck.zabochen.englishverbs.R

class NotificationFragment : MvpFragment<NotificationView, NotificationPresenter>(),
        NotificationView {

    override fun createPresenter() = NotificationPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

}