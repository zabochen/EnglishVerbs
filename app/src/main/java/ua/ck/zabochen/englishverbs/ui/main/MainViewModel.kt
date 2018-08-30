package ua.ck.zabochen.englishverbs.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.callback.CallbackEvent
import ua.ck.zabochen.englishverbs.helper.database.RealmHelper
import javax.inject.Inject

class MainViewModel : ViewModel(), AnkoLogger {

    @Inject
    lateinit var realmHelper: RealmHelper

    init {
        MainApp.mainAppInstance().getActivityComponent().inject(this)
    }

    val databaseState: MutableLiveData<Boolean> = MutableLiveData()

    fun viewIsReady() {
        databaseInflateOrUpdate()
    }

    private fun databaseInflateOrUpdate() {
        // Set default state
        if (databaseState.value == null) {
            databaseState.postValue(false)
        }

        if (databaseState.value == false) {
            realmHelper.inflateDatabase(object : CallbackEvent.DatabaseCallback {
                override fun onComplete() {
                    databaseState.postValue(true)
                }

                override fun onError(error: Throwable) {
                    databaseState.postValue(false)
                }
            })
        }
    }

}