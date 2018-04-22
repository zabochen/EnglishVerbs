package ua.ck.zabochen.englishverbs.view.verbfull

import com.hannesdorfmann.mosby3.mvp.MvpView
import ua.ck.zabochen.englishverbs.model.realm.Verb

interface VerbFullView : MvpView {

    fun setUi(verb: Verb?)

}