package ua.ck.zabochen.englishverbs

import android.app.Application
import org.koin.android.ext.android.startKoin
import ua.ck.zabochen.englishverbs.dagger.component.ActivityComponent
import ua.ck.zabochen.englishverbs.dagger.component.AppComponent
import ua.ck.zabochen.englishverbs.dagger.component.DaggerAppComponent
import ua.ck.zabochen.englishverbs.dagger.component.FragmentComponent
import ua.ck.zabochen.englishverbs.dagger.module.ApplicationContextModule
import ua.ck.zabochen.englishverbs.dagger.module.DatabaseModule
import ua.ck.zabochen.englishverbs.dagger.module.NotificationModule
import ua.ck.zabochen.englishverbs.dagger.module.SpeechModule
import ua.ck.zabochen.englishverbs.koin.appModule

class MainApp : Application() {

    companion object {

        lateinit var mAppInstance: MainApp
        fun mainAppInstance() = mAppInstance

        lateinit var mAppComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        // Set App Instance
        mAppInstance = this

        setDagger()
    }

    private fun setDagger() {
        mAppComponent = DaggerAppComponent.builder()
                .applicationContextModule(ApplicationContextModule(this))
                .build()
    }

    private fun setKoin() {
        startKoin(this, listOf(appModule))
    }

    fun getActivityComponent(): ActivityComponent {
        return mAppComponent.activityComponentBuilder()
                .databaseModule(DatabaseModule())
                .notificationModule(NotificationModule())
                .speechModule(SpeechModule())
                .build()
    }

    fun getFragmentComponent(): FragmentComponent {
        return mAppComponent.fragmentComponentBuilder()
                .databaseModule(DatabaseModule())
                .notificationModule(NotificationModule())
                .speechModule(SpeechModule())
                .build()
    }
}