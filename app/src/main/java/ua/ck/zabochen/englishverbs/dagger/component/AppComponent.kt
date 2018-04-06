package ua.ck.zabochen.englishverbs.dagger.component

import android.content.Context
import dagger.Component
import ua.ck.zabochen.englishverbs.dagger.module.ApplicationContextModule
import javax.inject.Singleton

@Component(modules = [ApplicationContextModule::class])
@Singleton
interface AppComponent {

    // Dependent components
    fun activityComponentBuilder(): ActivityComponent.Builder

    // Available dependencies for child components
    fun applicationContext(): Context

}