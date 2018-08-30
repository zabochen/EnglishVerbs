package ua.ck.zabochen.englishverbs.ui.verblist

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.helper.database.RealmHelper
import ua.ck.zabochen.englishverbs.model.realm.Verb
import ua.ck.zabochen.englishverbs.ui.verbfull.VerbFullActivity
import ua.ck.zabochen.englishverbs.utils.Constants
import javax.inject.Inject

class VerbListViewModel : ViewModel() {

    init {
        MainApp.mainAppInstance().getFragmentComponent().inject(this)
    }

    @Inject
    lateinit var realmHelper: RealmHelper

    val verbListState: MutableLiveData<ArrayList<Verb>> = MutableLiveData()

    fun viewIsReady() {
        loadData()
    }

    private fun loadData() {
        verbListState.postValue(realmHelper.getVerbList())
    }


}