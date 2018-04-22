package ua.ck.zabochen.englishverbs.view.verblist

import com.hannesdorfmann.mosby3.mvp.MvpView
import ua.ck.zabochen.englishverbs.model.realm.Verb

interface VerbListView : MvpView {

    fun setUi(verbList: ArrayList<Verb>)

}