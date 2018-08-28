package ua.ck.zabochen.englishverbs.dagger.component

import dagger.Subcomponent
import ua.ck.zabochen.englishverbs.dagger.module.NotificationModule
import ua.ck.zabochen.englishverbs.dagger.module.RealmModule
import ua.ck.zabochen.englishverbs.dagger.module.SpeechModule
import ua.ck.zabochen.englishverbs.dagger.scope.ActivityScope
import ua.ck.zabochen.englishverbs.ui.main.MainActivity
import ua.ck.zabochen.englishverbs.view.verbfull.VerbFullPresenter

@Subcomponent(modules = [
    RealmModule::class,
    NotificationModule::class,
    SpeechModule::class
])
@ActivityScope
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun realmModule(realmModule: RealmModule): ActivityComponent.Builder
        fun notificationModule(notificationModule: NotificationModule): ActivityComponent.Builder
        fun speechModule(speechModule: SpeechModule): ActivityComponent.Builder
        fun build(): ActivityComponent
    }

    // Dependent views
    fun inject(mainActivity: MainActivity)

    fun inject(verbFullPresenter: VerbFullPresenter)

}