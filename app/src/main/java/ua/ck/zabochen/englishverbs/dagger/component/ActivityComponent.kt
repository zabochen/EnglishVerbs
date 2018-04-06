package ua.ck.zabochen.englishverbs.dagger.component

import dagger.Subcomponent
import ua.ck.zabochen.englishverbs.dagger.module.NotificationModule
import ua.ck.zabochen.englishverbs.dagger.module.RealmModule
import ua.ck.zabochen.englishverbs.dagger.module.SpeechModule
import ua.ck.zabochen.englishverbs.dagger.scope.ActivityScope
import ua.ck.zabochen.englishverbs.view.main.MainPresenter

@ActivityScope
@Subcomponent(modules = [
    RealmModule::class,
    SpeechModule::class,
    NotificationModule::class
])
interface ActivityComponent {
    fun inject(mainPresenter: MainPresenter)
}