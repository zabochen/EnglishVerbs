package ua.ck.zabochen.englishverbs.ui.verbfull

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.helper.database.RealmHelper
import ua.ck.zabochen.englishverbs.model.realm.Verb
import javax.inject.Inject

class VerbFullViewModel : ViewModel() {

    @Inject
    lateinit var realmHelper: RealmHelper

    init {
        MainApp.mainAppInstance().getActivityComponent().inject(this)
    }

    val verbState: MutableLiveData<Verb> = MutableLiveData()

    fun selectedVerbItem(position: Int) {
        getVerb(position)
    }

    private fun getVerb(position: Int) {
        verbState.postValue(realmHelper.getVerb(position))
    }
}