package ua.ck.zabochen.englishverbs

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import ua.ck.zabochen.englishverbs.dagger.component.ActivityComponent
import ua.ck.zabochen.englishverbs.dagger.component.AppComponent
import ua.ck.zabochen.englishverbs.dagger.component.DaggerAppComponent
import ua.ck.zabochen.englishverbs.dagger.component.FragmentComponent
import ua.ck.zabochen.englishverbs.dagger.module.ApplicationContextModule
import ua.ck.zabochen.englishverbs.dagger.module.NotificationModule
import ua.ck.zabochen.englishverbs.dagger.module.RealmModule
import ua.ck.zabochen.englishverbs.dagger.module.SpeechModule
import ua.ck.zabochen.englishverbs.helper.database.DatabaseHelper
import ua.ck.zabochen.englishverbs.utils.Constants

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

        setDatabase()
        setRealm()
        setDagger()
    }

    private fun setDagger() {
        mAppComponent = DaggerAppComponent.builder()
                .applicationContextModule(ApplicationContextModule(this))
                .build()
    }

    fun getActivityComponent(): ActivityComponent {
        return mAppComponent.activityComponentBuilder()
                .realmModule(RealmModule())
                .speechModule(SpeechModule())
                .notificationModule(NotificationModule())
                .build()
    }

    fun getFragmentComponent(): FragmentComponent {
        return mAppComponent.fragmentComponentBuilder()
                .realmModule(RealmModule())
                .notificationModule(NotificationModule())
                .speechModule(SpeechModule())
                .build()
    }

    private fun setRealm() {
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
                .name(Constants.REALM_DATABASE_NAME)
                .schemaVersion(Constants.REALM_DATABASE_VERSION)
                .build()
        )
    }

    private fun setDatabase() {
        DatabaseHelper(applicationContext).jsonToObject()
    }

}