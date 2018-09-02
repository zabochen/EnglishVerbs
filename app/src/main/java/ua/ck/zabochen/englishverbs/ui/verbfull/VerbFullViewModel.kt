package ua.ck.zabochen.englishverbs.ui.verbfull

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.helper.database.DatabaseHelper
import javax.inject.Inject

class VerbFullViewModel : ViewModel() {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    init {
        MainApp.mainAppInstance().getActivityComponent().inject(this)
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val verbState: MutableLiveData<Verb> = MutableLiveData()

    fun selectedVerbItem(position: Int) {
        getVerb(position)
    }

    private fun getVerb(id: Int) {
        databaseHelper.getVerb(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Verb> {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onSuccess(t: Verb) {
                        verbState.postValue(t)
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}