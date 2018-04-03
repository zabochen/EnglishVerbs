package ua.ck.zabochen.englishverbs.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ActivityContextModule(context: Context) {

    private val mContext: Context = context

    @Provides
    fun provideActivityContext() = mContext

}