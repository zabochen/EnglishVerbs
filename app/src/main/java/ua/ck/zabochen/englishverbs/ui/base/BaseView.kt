package ua.ck.zabochen.englishverbs.ui.base

interface BaseView<VM> {
    fun getViewModel(): VM
    fun addObservers()
}