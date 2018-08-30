package ua.ck.zabochen.englishverbs.ui.verblist

import ua.ck.zabochen.englishverbs.ui.base.BaseView

interface VerbListView : BaseView<VerbListViewModel> {
    fun verbListState()
}