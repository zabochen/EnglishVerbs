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
import ua.ck.zabochen.englishverbs.helper.speech.SpeechHelper
import javax.inject.Inject

class VerbFullViewModel : ViewModel() {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    @Inject
    lateinit var speechHelper: SpeechHelper

    init {
        MainApp.mainAppInstance().getActivityComponent().inject(this)
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val verbState: MutableLiveData<Verb> = MutableLiveData()
    val verbBookmarkState: MutableLiveData<Boolean> = MutableLiveData()

    fun selectedVerbItem(position: Int) {
        getVerb(position)
    }

    fun setVerbBookmarkState(id: Int) {
        databaseHelper.setVerbBookmarkState(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Boolean> {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onSuccess(t: Boolean) {
                        verbBookmarkState.value = t
                    }

                    override fun onError(e: Throwable) {
                    }
                })
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

    fun speak(text: String) {
        speechHelper.speak(text)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}