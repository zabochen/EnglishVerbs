package ua.ck.zabochen.englishverbs.ui.main

interface MainView<VM> {

    fun getViewModel(): VM
    fun addObservers()

    // Observers
    fun databaseStateObserver()
}