package ua.ck.zabochen.englishverbs.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationContextModule(context: Context) {
    private val mContext: Context = context.applicationContext

    @Provides
    @Singleton
    fun provideApplicationContext() = mContext
}