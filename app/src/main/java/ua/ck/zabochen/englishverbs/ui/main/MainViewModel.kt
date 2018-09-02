package ua.ck.zabochen.englishverbs.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.helper.database.DatabaseHelper
import javax.inject.Inject

class MainViewModel : ViewModel(), AnkoLogger {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    init {
        MainApp.mainAppInstance().getActivityComponent().inject(this)
    }

    val databaseState: MutableLiveData<Boolean> = MutableLiveData()

    fun viewIsReady() {
        databaseInflate()
    }

    private fun databaseInflate() {
        // Set default state
        if (databaseState.value == null) {
            databaseState.value = false
        }

        if (databaseState.value == false) {
            databaseHelper.inflateDatabase()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : CompletableObserver {
                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onComplete() {
                            databaseState.value = true
                        }

                        override fun onError(e: Throwable) {
                            databaseState.value = true
                        }
                    })
        }

//        // Realm
//        if (databaseState.value == false) {
//            realmHelper.inflateDatabase(object : CallbackEvent.DatabaseCallback {
//                override fun onComplete() {
//                    databaseState.postValue(true)
//                }
//
//                override fun onError(error: Throwable) {
//                    databaseState.postValue(false)
//                }
//            })
//        }

    }


}