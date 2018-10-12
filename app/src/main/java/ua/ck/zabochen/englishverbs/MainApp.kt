package ua.ck.zabochen.englishverbs

import android.app.Application
import org.koin.android.ext.android.startKoin
import ua.ck.zabochen.englishverbs.koin.appModule

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setKoin()
    }

    private fun setKoin() {
        startKoin(this, listOf(appModule))
    }
}