package ua.ck.zabochen.englishverbs.dagger.module

import dagger.Module
import dagger.Provides
import ua.ck.zabochen.englishverbs.dagger.scope.ActivityScope
import ua.ck.zabochen.englishverbs.helper.notification.NotificationHelper

@Module
class NotificationModule {

    @Provides
    @ActivityScope
    fun provideNotificationHelper() = NotificationHelper()

}