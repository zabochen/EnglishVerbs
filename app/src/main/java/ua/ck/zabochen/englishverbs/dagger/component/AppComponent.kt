package ua.ck.zabochen.englishverbs.dagger.component

import android.content.Context
import dagger.Component
import ua.ck.zabochen.englishverbs.dagger.module.ApplicationContextModule
import ua.ck.zabochen.englishverbs.dagger.module.NotificationModule
import ua.ck.zabochen.englishverbs.dagger.module.RealmModule
import ua.ck.zabochen.englishverbs.dagger.module.SpeechModule

@Component(modules = [ApplicationContextModule::class])
interface AppComponent {

    // Dependent components
    fun addActivityComponent(realmModule: RealmModule, speechModule: SpeechModule,
                             notificationModule: NotificationModule): ActivityComponent

    // Available dependencies for child components
    fun applicationContext(): Context

}