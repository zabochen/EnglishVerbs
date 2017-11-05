package ua.ck.zabochen.englishverbs

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import ua.ck.zabochen.englishverbs.dagger.AppComponent
import ua.ck.zabochen.englishverbs.dagger.AppModule
import ua.ck.zabochen.englishverbs.dagger.DaggerAppComponent
import ua.ck.zabochen.englishverbs.utils.Constants

class MainApp : Application() {

    companion object {
        @JvmStatic lateinit var mAppComponent: AppComponent
        fun appComponent() = mAppComponent
    }

    override fun onCreate() {
        super.onCreate()
        setRealm()
        setDagger()
    }

    private fun setDagger() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
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

}