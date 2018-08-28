package ua.ck.zabochen.englishverbs.dagger.component

import dagger.Subcomponent
import ua.ck.zabochen.englishverbs.dagger.module.NotificationModule
import ua.ck.zabochen.englishverbs.dagger.module.RealmModule
import ua.ck.zabochen.englishverbs.dagger.module.SpeechModule
import ua.ck.zabochen.englishverbs.dagger.scope.ActivityScope
import ua.ck.zabochen.englishverbs.ui.verblist.VerbListViewModel
import ua.ck.zabochen.englishverbs.view.bookmark.BookmarkPresenter
import ua.ck.zabochen.englishverbs.view.notification.NotificationPresenter

@Subcomponent(modules = [
    RealmModule::class,
    NotificationModule::class,
    SpeechModule::class
])
@ActivityScope
interface FragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun realmModule(realmModule: RealmModule): FragmentComponent.Builder
        fun notificationModule(notificationModule: NotificationModule): FragmentComponent.Builder
        fun speechModule(speechModule: SpeechModule): FragmentComponent.Builder
        fun build(): FragmentComponent
    }

    // Dependent views
    fun inject(verbListViewModel: VerbListViewModel)
    fun inject(bookmarkPresenter: BookmarkPresenter)
    fun inject(notificationPresenter: NotificationPresenter)
}