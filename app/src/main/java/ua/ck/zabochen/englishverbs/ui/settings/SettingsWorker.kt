package ua.ck.zabochen.englishverbs.ui.settings

import androidx.work.Worker
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class SettingsWorker : Worker(), AnkoLogger {

    override fun doWork(): Result {
        info { "doWork() - Worker.Result.SUCCESS" }
        return Worker.Result.SUCCESS
    }
}