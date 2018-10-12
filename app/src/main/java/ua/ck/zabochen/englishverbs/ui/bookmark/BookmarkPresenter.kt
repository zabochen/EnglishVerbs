package ua.ck.zabochen.englishverbs.ui.bookmark

import android.os.Parcelable
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

@InjectViewState
class BookmarkPresenter : MvpPresenter<BookmarkView>(), KoinComponent {

    private val databaseHelper: DatabaseHelper by inject()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var recyclerViewState: Parcelable? = null

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
                        //bookmarkVerbListState.postValue(t)
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