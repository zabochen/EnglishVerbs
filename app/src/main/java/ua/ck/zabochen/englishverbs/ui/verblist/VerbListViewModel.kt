package ua.ck.zabochen.englishverbs.ui.verblist

import android.os.Parcelable
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

class VerbListViewModel : ViewModel() {

    init {
        MainApp.mainAppInstance().getFragmentComponent().inject(this)
    }

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val verbListState: MutableLiveData<ArrayList<Verb>> = MutableLiveData()
    var recyclerViewState: Parcelable? = null

    fun viewIsReady() {
        loadData()
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
                        verbListState.postValue(t)
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