package ua.ck.zabochen.englishverbs.helper.notification

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class NotificationWorker(context: Context, workerParameters: WorkerParameters) :
        Worker(context, workerParameters), AnkoLogger {

    override fun doWork(): Result {
        info { "doWork() - Worker.Result.SUCCESS" }
        return Worker.Result.SUCCESS
    }
}