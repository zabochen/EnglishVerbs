package ua.ck.zabochen.englishverbs

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import ua.ck.zabochen.englishverbs.dagger.AppModule
import ua.ck.zabochen.englishverbs.dagger.DaggerMainAppComponent
import ua.ck.zabochen.englishverbs.dagger.MainAppComponent
import ua.ck.zabochen.englishverbs.dagger.component.ActivityComponent
import ua.ck.zabochen.englishverbs.dagger.component.AppComponent
import ua.ck.zabochen.englishverbs.dagger.component.DaggerAppComponent
import ua.ck.zabochen.englishverbs.dagger.module.ApplicationContextModule
import ua.ck.zabochen.englishverbs.dagger.module.NotificationModule
import ua.ck.zabochen.englishverbs.dagger.module.RealmModule
import ua.ck.zabochen.englishverbs.dagger.module.SpeechModule
import ua.ck.zabochen.englishverbs.utils.Constants

class MainApp : Application() {

    companion object {

        lateinit var mAppInstance: MainApp
        fun mainAppInstance() = mAppInstance

        lateinit var mMainAppComponent: MainAppComponent
        fun mainAppComponent() = mMainAppComponent

        lateinit var mAppComponent: AppComponent
        fun getAppComponent() = mAppComponent
    }

    private var mActivityComponent: ActivityComponent? = null

    override fun onCreate() {
        super.onCreate()

        // Set App Instance
        mAppInstance = this

        setRealm()
        setDagger()
    }

    private fun setDagger() {
        // Main AppComponent
        mMainAppComponent = DaggerMainAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        // AppComponent
        mAppComponent = DaggerAppComponent.builder()
                .applicationContextModule(ApplicationContextModule(this))
                .build()
    }

    fun addActivityComponent(): ActivityComponent? {
        mActivityComponent = mAppComponent.activityComponentBuilder()
                .realmModule(RealmModule())
                .speechModule(SpeechModule())
                .notificationModule(NotificationModule())
                .build()
        return mActivityComponent
    }

    fun clearActiivtyComponent() {
        mActivityComponent = null
    }

    private fun setRealm() {
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
                .name(Constants.REALM_DATABASE_NAME)
                .schemaVersion(Constants.REALM_DATABASE_VERSION)
                .build()
        )
    }

}