package ua.ck.zabochen.englishverbs.view.main

import android.support.v4.app.Fragment
import com.hannesdorfmann.mosby3.mvp.MvpView

interface MainView : MvpView {
    fun setFragment(fragment: Fragment)
}