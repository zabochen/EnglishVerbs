package ua.ck.zabochen.englishverbs.ui.setting

import ua.ck.zabochen.englishverbs.ui.base.BaseView

interface SettingView : BaseView<SettingViewModel> {
    fun settingStateObserver()
}