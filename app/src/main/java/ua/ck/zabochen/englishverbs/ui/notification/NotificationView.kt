package ua.ck.zabochen.englishverbs.ui.notification

import ua.ck.zabochen.englishverbs.ui.base.BaseView

interface NotificationView : BaseView<NotificationViewModel> {
    fun notificationStateObserver()
}