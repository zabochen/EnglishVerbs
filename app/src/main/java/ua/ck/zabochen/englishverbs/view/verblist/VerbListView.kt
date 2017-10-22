package ua.ck.zabochen.englishverbs.view.verblist

import com.arellomobile.mvp.MvpView
import ua.ck.zabochen.englishverbs.model.realm.Verb

interface VerbListView : MvpView {

    fun setUi(verbList: ArrayList<Verb>)

}