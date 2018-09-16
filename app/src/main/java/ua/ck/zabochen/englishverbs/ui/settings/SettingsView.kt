package ua.ck.zabochen.englishverbs.ui.settings

import ua.ck.zabochen.englishverbs.ui.base.BaseView

interface SettingsView : BaseView<SettingsViewModel> {
    fun notificationStateObserver()
}