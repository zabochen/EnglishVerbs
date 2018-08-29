package ua.ck.zabochen.englishverbs.ui.verblist

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.helper.database.RealmHelper
import ua.ck.zabochen.englishverbs.model.realm.Verb
import ua.ck.zabochen.englishverbs.utils.Constants
import ua.ck.zabochen.englishverbs.ui.verbfull.VerbFullActivity
import javax.inject.Inject

class VerbListViewModel : ViewModel() {

    init {
        MainApp.mainAppInstance().getFragmentComponent().inject(this)
    }

    @Inject
    lateinit var realmHelper: RealmHelper

    val progress: MutableLiveData<Boolean> = MutableLiveData()

    private var verbList: MutableLiveData<ArrayList<Verb>>? = null

    fun getVerbList(): MutableLiveData<ArrayList<Verb>>? {
        // Show progress
        progress.postValue(true)

        if (verbList == null) {
            verbList = MutableLiveData()
            loadData()
        }

        Thread.sleep(2000)

        // Hide progress
        progress.postValue(false)

        return verbList
    }

    private fun loadData() {
        verbList = realmHelper.getVerbList()
    }

    fun onClickVerbItem(activityContext: Context, position: Int) {
        val intentVerbFullActivity = Intent(activityContext, VerbFullActivity::class.java)
        intentVerbFullActivity.putExtra(Constants.INTENT_VERB_SELECTED_POSITION, position)
        activityContext.startActivity(intentVerbFullActivity)
    }
}