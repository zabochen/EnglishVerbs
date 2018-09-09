package ua.ck.zabochen.englishverbs.ui.bookmark

import android.os.Parcelable
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
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.helper.database.DatabaseHelper
import javax.inject.Inject

class BookmarkViewModel : ViewModel(), AnkoLogger {

    init {
        MainApp.mainAppInstance().getFragmentComponent().inject(this)
    }

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val bookmarkVerbList: MutableLiveData<ArrayList<Verb>> = MutableLiveData()

    var recyclerViewState: Parcelable? = null

    fun viewIsReady() {
        getBookmarkVerbList()
    }

    fun refreshView() {
        getBookmarkVerbList()
    }

    private fun getBookmarkVerbList() {
        databaseHelper.getBookmarkVerbList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<ArrayList<Verb>> {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onSuccess(t: ArrayList<Verb>) {

                        info { "onSuccess ${t.size}" }

                        bookmarkVerbList.postValue(t)
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        info { "BOOKMARK => fun onCleared()" }
        super.onCleared()
    }
}