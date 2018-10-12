package ua.ck.zabochen.englishverbs.ui.verblist

import android.os.Parcelable
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.helper.database.DatabaseHelper

@InjectViewState
class VerbListPresenter : MvpPresenter<VerbListView>(), KoinComponent {

    private val databaseHelper: DatabaseHelper by inject()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    // TODO: Save recyclerView State
    var recyclerViewState: Parcelable? = null

    fun viewIsReady() {
        databaseInflate()
    }

    private fun databaseInflate() {
        databaseHelper.inflateDatabase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onComplete() {
                        // Get verbList
                        loadData()
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    private fun loadData() {
        databaseHelper.getVerbList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<ArrayList<Verb>> {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onSuccess(t: ArrayList<Verb>) {
                        viewState.setUi(t)
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}