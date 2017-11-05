package ua.ck.zabochen.englishverbs.callback

interface CallbackEvent {

    interface DatabaseCallback {
        fun onComplete()
        fun onError(error: Throwable)
    }

}