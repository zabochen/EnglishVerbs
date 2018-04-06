package ua.ck.zabochen.englishverbs.dagger.component

import dagger.Subcomponent
import ua.ck.zabochen.englishverbs.dagger.module.NotificationModule
import ua.ck.zabochen.englishverbs.dagger.module.RealmModule
import ua.ck.zabochen.englishverbs.dagger.module.SpeechModule
import ua.ck.zabochen.englishverbs.dagger.scope.ActivityScope
import ua.ck.zabochen.englishverbs.view.main.MainPresenter

@Subcomponent(modules = [
    RealmModule::class,
    SpeechModule::class,
    NotificationModule::class
])
@ActivityScope
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun realmModule(realmModule: RealmModule): ActivityComponent.Builder
        fun speechModule(speechModule: SpeechModule): ActivityComponent.Builder
        fun notificationModule(notificationModule: NotificationModule): ActivityComponent.Builder
        fun build(): ActivityComponent
    }

    fun inject(mainPresenter: MainPresenter)
}