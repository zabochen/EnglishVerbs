package ua.ck.zabochen.englishverbs.view.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.MainApp
import ua.ck.zabochen.englishverbs.callback.CallbackEvent
import ua.ck.zabochen.englishverbs.helper.database.RealmHelper
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>(),
        AnkoLogger {

    init {
        MainApp.mainAppInstance().addActivityComponent()?.inject(this)
    }

    @Inject
    lateinit var mRealmHelper: RealmHelper

    fun inflateOrUpdateDatabase() {
        // Show progressBar
        viewState.showProgressBar()

        // Inflate data
        mRealmHelper.inflateDatabase(object : CallbackEvent.DatabaseCallback {

            override fun onComplete() {
                // Show verb fragment
                viewState.setVerbList()
                // Hide progressBar
                viewState.hideProgressBar()
            }

            override fun onError(error: Throwable) {
                // Hide progressBar
                viewState.hideProgressBar()
            }
        })
    }

}