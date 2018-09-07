package ua.ck.zabochen.englishverbs.ui.bookmark

import ua.ck.zabochen.englishverbs.ui.base.BaseView

interface BookmarkView : BaseView<BookmarkViewModel> {
    fun bookmarkVerbListObserver()
    fun removeVerbObserver()
}