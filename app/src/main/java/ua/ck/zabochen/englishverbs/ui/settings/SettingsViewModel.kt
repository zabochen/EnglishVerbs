package ua.ck.zabochen.englishverbs.ui.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.database.entity.Settings
import ua.ck.zabochen.englishverbs.helper.database.DatabaseHelper
import javax.inject.Inject

class SettingsViewModel : ViewModel(), AnkoLogger {

    init {
        MainApp.mAppInstance.getFragmentComponent().inject(this)
    }

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val settingsState: MutableLiveData<Settings> = MutableLiveData()

    fun viewIsReady(notificationId: Int) {
        getNotification(notificationId)
    }

    private fun getNotification(notificationId: Int) {
        databaseHelper.getNotification(notificationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        object : SingleObserver<Settings> {
                            override fun onSubscribe(d: Disposable) {
                                compositeDisposable.add(d)
                            }

                            override fun onSuccess(t: Settings) {
                                settingsState.value = t
                            }

                            override fun onError(e: Throwable) {
                                info { "ERROR => ${e.printStackTrace()}" }
                            }
                        }
                )
    }

    fun onClickNotificationState(notificationId: Int) {
        databaseHelper.setNotificationState(notificationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Boolean> {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onSuccess(t: Boolean) {

                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}