package ua.ck.zabochen.englishverbs.view.verbfull

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ua.ck.zabochen.englishverbs.model.realm.Verb

@StateStrategyType(AddToEndSingleStrategy::class)
interface VerbFullView : MvpView {

    fun setUi(verb: Verb?)

}