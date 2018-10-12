package ua.ck.zabochen.englishverbs.ui.setting

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
import ua.ck.zabochen.englishverbs.database.entity.Setting
import ua.ck.zabochen.englishverbs.helper.database.DatabaseHelper
import javax.inject.Inject

class SettingViewModel : ViewModel(), AnkoLogger {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val settingsState: MutableLiveData<Setting> = MutableLiveData()

    fun viewIsReady() {
        //getSettings()
    }
//    private fun getSettings() {
//        databaseHelper.getSettings()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : SingleObserver<Setting> {
//                    override fun onSubscribe(d: Disposable) {
//                        compositeDisposable.add(d)
//                    }
//
//                    override fun onSuccess(t: Setting) {
//                        info { "Notification state => ${t.notificationState}" }
//                    }
//
//                    override fun onError(e: Throwable) {
//                        info { "Error => $e" }
//                    }
//                })
//    }
//

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}