package ua.ck.zabochen.englishverbs.dagger.component

import dagger.Component
import ua.ck.zabochen.englishverbs.dagger.module.ActivityContextModule
import ua.ck.zabochen.englishverbs.dagger.module.NotificationModule
import ua.ck.zabochen.englishverbs.dagger.module.RealmModule
import ua.ck.zabochen.englishverbs.dagger.module.SpeechModule
import ua.ck.zabochen.englishverbs.view.main.MainPresenter

@Component(modules = [
    ActivityContextModule::class,
    RealmModule::class,
    SpeechModule::class,
    NotificationModule::class
])
interface ActivityComponent {

    fun inject(mainPresenter: MainPresenter)

}