package ua.ck.zabochen.englishverbs.ui.verbfull

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.helper.database.DatabaseHelper
import ua.ck.zabochen.englishverbs.helper.speech.SpeechHelper

@InjectViewState
class VerbFullPresenter : MvpPresenter<VerbFullView>(), KoinComponent {

    private val databaseHelper: DatabaseHelper by inject()
    private val speechHelper: SpeechHelper by inject()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

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
                        //verbBookmarkState.value = t
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
                        viewState.setUi(t)
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    fun speak(text: String) {
        speechHelper.speak(text)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        speechHelper.clear()
    }
}