package ua.ck.zabochen.englishverbs.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ua.ck.zabochen.englishverbs.dagger.scope.ActivityScope
import ua.ck.zabochen.englishverbs.helper.database.DatabaseHelper

@Module
class DatabaseModule {
    @Provides
    @ActivityScope
    fun provideDatabaseHelper(context: Context) = DatabaseHelper(context)
}