package ua.ck.zabochen.englishverbs.ui.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ua.ck.zabochen.englishverbs.database.entity.Notification
import ua.ck.zabochen.englishverbs.helper.database.DatabaseHelper
import javax.inject.Inject

class NotificationViewModel : ViewModel() {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val notificationState: MutableLiveData<Notification> = MutableLiveData()

    fun viewIsReady(notificationId: Int) {
        getNotification(notificationId)
    }

    private fun getNotification(notificationId: Int) {
        databaseHelper.getNotification(notificationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        object : SingleObserver<Notification> {
                            override fun onSubscribe(d: Disposable) {
                                compositeDisposable.add(d)
                            }

                            override fun onSuccess(t: Notification) {
                                notificationState.value = t
                            }

                            override fun onError(e: Throwable) {
                            }
                        }
                )
    }

    fun onClickNotificationState(notificationId: Int) {
        databaseHelper.setNotificationState(notificationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Boolean>{
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