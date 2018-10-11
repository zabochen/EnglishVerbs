package ua.ck.zabochen.englishverbs.koin

import org.koin.dsl.module.module
import ua.ck.zabochen.englishverbs.helper.database.DatabaseHelper
import ua.ck.zabochen.englishverbs.helper.notification.NotificationHelper
import ua.ck.zabochen.englishverbs.helper.speech.SpeechHelper

val appModule = module {
    single { DatabaseHelper(get()) }
    single { NotificationHelper() }
    single { SpeechHelper(get()) }
}