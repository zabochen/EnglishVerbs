package ua.ck.zabochen.englishverbs.ui.verbfull

import ua.ck.zabochen.englishverbs.ui.base.BaseView

interface VerbFullView : BaseView<VerbFullViewModel> {
    fun verbStateObserver()
    fun bookmarkStateObserver()
}