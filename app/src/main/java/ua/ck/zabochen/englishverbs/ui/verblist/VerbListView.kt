package ua.ck.zabochen.englishverbs.ui.verblist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ua.ck.zabochen.englishverbs.database.entity.Verb

@StateStrategyType(AddToEndSingleStrategy::class)
interface VerbListView : MvpView {
    fun setUi(verbList: ArrayList<Verb>)
    fun showProgressBar()
    fun hideProgressBar()
}