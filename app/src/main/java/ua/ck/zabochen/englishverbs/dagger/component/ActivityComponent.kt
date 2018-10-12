package ua.ck.zabochen.englishverbs.dagger.component

import dagger.Subcomponent
import ua.ck.zabochen.englishverbs.dagger.module.DatabaseModule
import ua.ck.zabochen.englishverbs.dagger.module.NotificationModule
import ua.ck.zabochen.englishverbs.dagger.module.SpeechModule
import ua.ck.zabochen.englishverbs.dagger.scope.ActivityScope

@Subcomponent(modules = [
    DatabaseModule::class,
    NotificationModule::class,
    SpeechModule::class
])
@ActivityScope
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun databaseModule(databaseModule: DatabaseModule): ActivityComponent.Builder
        fun notificationModule(notificationModule: NotificationModule): ActivityComponent.Builder
        fun speechModule(speechModule: SpeechModule): ActivityComponent.Builder
        fun build(): ActivityComponent
    }
}