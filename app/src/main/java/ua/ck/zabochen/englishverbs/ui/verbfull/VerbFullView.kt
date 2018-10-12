package ua.ck.zabochen.englishverbs.ui.verbfull

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ua.ck.zabochen.englishverbs.database.entity.Verb

@StateStrategyType(AddToEndSingleStrategy::class)
interface VerbFullView : MvpView {
    fun setUi(verb: Verb)
}