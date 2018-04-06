package ua.ck.zabochen.englishverbs.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ua.ck.zabochen.englishverbs.dagger.scope.ActivityScope
import ua.ck.zabochen.englishverbs.helper.database.RealmHelper

@Module(includes = [ApplicationContextModule::class])
class RealmModule {

    @Provides
    @ActivityScope
    fun provideRealmHelper(context: Context) = RealmHelper(context)

}