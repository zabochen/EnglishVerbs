package ua.ck.zabochen.englishverbs.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ua.ck.zabochen.englishverbs.dagger.scope.ActivityScope
import ua.ck.zabochen.englishverbs.helper.speech.SpeechHelper

@Module(includes = [ApplicationContextModule::class])
class SpeechModule {

    @Provides
    @ActivityScope
    fun provideSpeechHelper(context: Context) = SpeechHelper(context)

}