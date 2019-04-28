package ua.ck.zabochen.englishverbs.ui.base

// TODO: Remove after it won't use
interface BaseView<VM> {
    fun getViewModel(): VM
    fun addObservers()
}