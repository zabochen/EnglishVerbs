package ua.ck.zabochen.englishverbs.dagger.component

import dagger.Subcomponent
import ua.ck.zabochen.englishverbs.dagger.module.DatabaseModule
import ua.ck.zabochen.englishverbs.dagger.module.NotificationModule
import ua.ck.zabochen.englishverbs.dagger.module.SpeechModule
import ua.ck.zabochen.englishverbs.dagger.scope.ActivityScope
import ua.ck.zabochen.englishverbs.ui.bookmark.BookmarkViewModel
import ua.ck.zabochen.englishverbs.ui.setting.SettingViewModel

@Subcomponent(modules = [
    DatabaseModule::class,
    NotificationModule::class,
    SpeechModule::class
])
@ActivityScope
interface FragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun databaseModule(databaseModule: DatabaseModule): FragmentComponent.Builder
        fun notificationModule(notificationModule: NotificationModule): FragmentComponent.Builder
        fun speechModule(speechModule: SpeechModule): FragmentComponent.Builder
        fun build(): FragmentComponent
    }

    // Dependent views
    fun inject(bookmarkViewModel: BookmarkViewModel)
    fun inject(settingViewModel: SettingViewModel)
}