package ua.ck.zabochen.englishverbs.view.main

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {

    fun setVerbList()
    fun showProgressBar()
    fun hideProgressBar()

}